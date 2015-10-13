package co.sunny.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import co.sunny.exception.GCRGException;

public class ScheduledTaskMeteo extends TimerTask {

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
				"Sunny", "GCRGthesis", "/spoc/atqasuk/weather");
		files = ftp.getFTPFiles(ftpClient);
		// files = LatestFiles
		// .lastFilesModified("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\data");
		for (int i = 0; i < files.size(); i++) {
			try {
				FileUtils
						.cleanDirectory(new File(
								"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Meteo"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sourceFile = files.get(i);
			boolean copied = false;
			try {
				destFile = new FileOutputStream(
						"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\Met.dat");
				copied = CopyFile
						.copyMeteoFile(sourceFile, destFile, ftpClient);
				destFile.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (copied) {
				String data = "";

				// Copy and paste the resultant row to the master data csv file
				try {
					BufferedReader br = new BufferedReader(
							new FileReader(
									"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\Met.dat"));
					while ((data = br.readLine()) != null) {
						ExcelCopy
								.writeDataToExcelFile(
										"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_meteo.csv",
										data);
						DatabaseUploader dbUploader = new DatabaseUploader();
						String[] parameters = data.split(",");
						try {
							dbUploader.uploadMeteoData(parameters);
						} catch (GCRGException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					br.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				System.out.println("Bad .dat file..");
			}
		}
	}
}