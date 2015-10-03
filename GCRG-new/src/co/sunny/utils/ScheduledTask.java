package co.sunny.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import co.sunny.exception.GCRGException;

public class ScheduledTask extends TimerTask {
	public static String searchCSVFile(String parentDirectory) {
		File[] files = new File(parentDirectory).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				searchCSVFile(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1,
					filePath.length());
			if (fileName.contains("full_output")) {
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
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date;
		try {
			date = format.parse("2015/01/07 06:24:00");
			System.out.println(date);
			FTPConnection.lastMod = date.getTime();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
				copied = CopyFile.copyFile(sourceFile, destFile, ftpClient);
				destFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// run CardConvert and EddyPro in command line
			Runtime rt = Runtime.getRuntime();
			if (copied) {
				try {
					rt.exec("\"C:\\Program Files (x86)\\Campbellsci\\CardConvert\\CardConvert.exe\" runfile=C:\\Campbellsci\\CardConvert\\myfile.ccf");
					try {
						Thread.sleep(3000); // 3000 milliseconds is one second.
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}
					renameFile("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\csv_data");
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
					String[] parameters = data.split(",");
					ExcelCopy
							.writeDataToExcelFile(
									"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv",
									data);
					DatabaseUploader dbUploader = new DatabaseUploader();
					try {
						dbUploader.run(parameters);
					} catch (GCRGException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					System.out.println("Bad .dat file..");
				}
			}
		}
	}
}