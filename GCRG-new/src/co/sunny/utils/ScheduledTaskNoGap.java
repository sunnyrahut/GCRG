package co.sunny.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ScheduledTaskNoGap extends TimerTask {
	private static int rowNumber = 0;

	public static String searchCSVFile(String parentDirectory) {
		File[] files = new File(parentDirectory).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				searchCSVFile(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1,
					filePath.length());
			if (fileName.contains("full_output")
					&& fileName.substring(fileName.length() - 4,
							fileName.length()).equals(".csv")) {
				System.out.println(filePath);
				return filePath;
			}
		}
		return "File Not Found";
	}

	public static void renameFile(String path) {
		File[] files = new File(path).listFiles();
		for (File f : files) {
			if (f.getName().contains(".dat")) {
				String newName = f.getName().replaceAll("_", "")
						.replace("TOA5", "");
				f.renameTo(new File(f.getAbsolutePath().substring(0,
						f.getAbsolutePath().lastIndexOf("\\") + 1)
						+ newName));
			}
		}
	}

	public void run() {
		ArrayList<FTPFile> files = new ArrayList<FTPFile>();
		FTPFile sourceFile;
		FileOutputStream destFile;

		FTPConnection ftp = new FTPConnection();
		FTPClient ftpClient = ftp.getConnection("mr-spoc.sdsu.edu", 21,
				"Sunny", "GCRGthesis", "/spoc/atqasuk/cr3000_barebone");
		files = ftp.getFTPFiles(ftpClient);
		// files = LatestFiles
		// .lastFilesModified("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\data");
		for (int i = 0; i < files.size(); i++) {
			try {
				FileUtils
						.cleanDirectory(new File(
								"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data"));
				FileUtils
						.cleanDirectory(new File(
								"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\csv_data"));
				FileUtils
						.cleanDirectory(new File(
								"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\output"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sourceFile = files.get(i);
			boolean copied = false;
			try {
				destFile = new FileOutputStream(
						"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\ATQ.dat");
				copied = CopyFile
						.copyNoGapFile(sourceFile, destFile, ftpClient);
				destFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// run CardConvert and EddyPro in command line
			Runtime rt = Runtime.getRuntime();
			if (copied) {
				try {
					File oldFile = new File(
							"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\ATQ.dat");
					File newFile = new File(
							"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\ATQ"
									+ sourceFile.getName().replaceAll("_", "")
											.substring(13));
					oldFile.renameTo(newFile);
					rt.exec("\"C:\\Program Files (x86)\\LI-COR\\EddyPro-6.0.0\\bin\\eddypro_rp.exe\" -s win -c console -m desktop C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\ATQ-LGR-CSAT_improved_separation_NewCols.eddypro");
					try {
						Thread.sleep(15000); // 3000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					rt.exec("\"C:\\Program Files (x86)\\LI-COR\\EddyPro-6.0.0\\bin\\eddypro_fcc.exe\" -s win -c console -m desktop C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\ATQ-LGR-CSAT_improved_separation_NewCols.eddypro");
					System.out.println("Eddy Pro Executed..");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(e.getMessage());
				}
				String data = "";

				// Copy and paste the resultant row to the master data csv file
				try {
					Thread.sleep(5000); // 10000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				String filePath = searchCSVFile("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\output");
				if (!filePath.equals("File Not Found")) {
					try {
						data = ExcelCopy.readDataFromCsvFile(filePath);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rowNumber++;
					System.out.println("Row number: " + rowNumber);
					ExcelCopy
							.writeDataToExcelFile(
									"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv",
									data);
					if (rowNumber >= 500) {
						try {
							rt.exec("\"C:\\Program Files\\MATLAB\\R2015a\\bin\\matlab.exe\" -nodisplay -nosplash -nodesktop -r \"run('C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Matlab\\Filter_EP_forWebsite.m')");
						} catch (IOException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
						try {
							Thread.sleep(15000); // 10000 milliseconds is one
													// second.
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						BufferedReader br = null;
						try {
							br = new BufferedReader(
									new FileReader(
											"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\cleaned_master_data.txt"));
							try {
								DatabaseUploader dbUploader = new DatabaseUploader();
								String[] parameters;
								int countRow = 0;
								while ((data = br.readLine()) != null) {
									countRow++;
									if (countRow > 2) {
										parameters = data.split("\t");
										try {
											dbUploader
													.uploadNoGapData(parameters);
										} catch (Exception e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
									}
								}
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						// delete csv file contents
						ArrayList<String> lines = new ArrayList<String>();
						try {
							br = new BufferedReader(
									new FileReader(
											"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv"));
							int rowCount = 0;
							while (rowCount < 3) {
								try {
									lines.add(br.readLine());
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								rowCount++;
							}
							try {
								br.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (FileNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						File file = new File(
								"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv");
						file.delete();
						rowNumber = 0;

						FileWriter fileWriter = null;
						try {
							fileWriter = new FileWriter(
									"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv",
									true);
							for (int j = 0; j < lines.size(); j++) {
								data = lines.get(j);
								fileWriter.append(data);
								fileWriter.append("\n");
							}
							fileWriter.flush();
							fileWriter.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} else {
					System.out.println("Bad .dat file..");
				}
			}
		}
	}
}