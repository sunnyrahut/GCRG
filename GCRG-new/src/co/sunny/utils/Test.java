package co.sunny.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import co.sunny.exception.GCRGException;

public class Test {
	private static int rowNumber = 300;

	public static void main(String[] args) {
		// Runtime rt = Runtime.getRuntime();
		// try {
		// rt.exec("\"C:\\Program Files\\MATLAB\\R2015a\\bin\\matlab.exe\" -nodisplay -nosplash -nodesktop -r \"run('C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Matlab\\Filter_EP_forWebsite.m')");
		// } catch (IOException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }
		// try {
		// Thread.sleep(5000); // 10000 milliseconds is one
		// // second.
		// } catch (InterruptedException ex) {
		// Thread.currentThread().interrupt();
		// }
		// BufferedReader br = null;
		// String data = "";
		// try {
		// br = new BufferedReader(
		// new FileReader(
		// "C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\cleaned_master_data.txt"));
		// try {
		// DatabaseUploader dbUploader = new DatabaseUploader();
		// Connection conn = null;
		// try {
		// conn = DBConnector.getDBConnection();
		// } catch (GCRGException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// String[] parameters;
		// int countRow = 0;
		// while ((data = br.readLine()) != null) {
		// countRow++;
		// if (countRow > 2) {
		// parameters = data.split("\t");
		// try {
		// dbUploader.uploadNoGapData(parameters, conn);
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// }
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		// // delete csv file contents
		// ArrayList<String> lines = new ArrayList<String>();
		// try {
		// br = new BufferedReader(
		// new FileReader(
		// "C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv"));
		// int rowCount = 0;
		// while (rowCount < 3) {
		// try {
		// lines.add(br.readLine());
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// rowCount++;
		// }
		// try {
		// br.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// } catch (FileNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// File file = new File(
		// "C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv");
		// file.delete();
		// rowNumber = 0;
		//
		// FileWriter fileWriter = null;
		// try {
		// fileWriter = new FileWriter(
		// "C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Master data\\master_data.csv",
		// true);
		// for (int j = 0; j < lines.size(); j++) {
		// data = lines.get(j);
		// fileWriter.append(data);
		// fileWriter.append("\n");
		// }
		// fileWriter.flush();
		// fileWriter.close();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		BufferedReader br = null;
		String data = "";
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
					if (countRow > 3) {
						parameters = data.split("\t");
						try {
							dbUploader.uploadNoGapData(parameters);
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
	}
}
