package co.sunny.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;

import co.sunny.exception.EateryException;

public class ScheduledTask extends TimerTask {
	public static String searchCSVFile(String parentDirectory) {
		File[] files = new File(parentDirectory).listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				searchCSVFile(f.getAbsolutePath());
			}
			String filePath = f.getAbsolutePath();
			String fileExtention = filePath.substring(
					filePath.lastIndexOf(".") + 1, filePath.length());
			if ("csv".equals(fileExtention)) {
				return filePath;
			}
		}
		return "No csv file found";
	}

	public static void renameFile(String path) {
		File[] files = new File(path).listFiles();
		for (File f : files) {
			String filePath = f.getAbsolutePath();
			String fileExtention = filePath.substring(
					filePath.lastIndexOf(".") + 1, filePath.length());
			if ("dat".equals(fileExtention)) {
				String fileName = filePath.substring(
						filePath.lastIndexOf("\\") + 1,
						filePath.lastIndexOf(".")).replaceAll("_", "");
				// TOB1_ATQ_ts_data_2015_05_05_1200
				fileName = fileName.replace("_ts_data_", "").replace(
						fileName.substring(0, 4), "");
				String newPath = filePath.substring(0,
						filePath.lastIndexOf("\\") + 1)
						+ fileName + ".dat";
				f.renameTo(new File(newPath));
			}
		}
	}

	public void run() {
		ArrayList<File> files = new ArrayList<File>();
		File sourceFile, destFile;
		files = LatestFiles
				.lastFilesModified("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\data");
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
			destFile = new File(
					"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\raw_data\\ATQ.dat");
			try {
				CopyFile.copyFile(sourceFile, destFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// run CardConvert and EddyPro in command line
			Runtime rt = Runtime.getRuntime();
			try {
				rt.exec("\"C:\\Program Files (x86)\\Campbellsci\\CardConvert\\CardConvert.exe\" runfile=C:\\Campbellsci\\CardConvert\\myfile.ccf");
				try {
					Thread.sleep(3000); // 3000 milliseconds is one second.
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
				renameFile("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\csv_data");
				rt.exec("\"C:\\Program Files (x86)\\LI-COR\\EddyPro-6.0.0\\bin\\eddypro_rp.exe\" -s win -c console -m desktop C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\ATQ-LGR-CSAT_improved_separation_NewCols.eddypro");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String data = "";

			// Copy and paste the resultant row to the master data csv file
			try {
				Thread.sleep(10000); // 10000 milliseconds is one second.
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			try {
				data = ExcelCopy
						.readDataFromCsvFile(searchCSVFile("C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\output"));
			} catch (EateryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ExcelCopy
					.writeDataToExcelFile(
							"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv",
							data);
		}
		// System.out.println(files);
	}
}