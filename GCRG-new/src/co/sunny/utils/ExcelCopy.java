package co.sunny.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import co.sunny.exception.GCRGException;

public class ExcelCopy {
	public static String readDataFromCsvFile(String location)
			throws GCRGException {
		String csvFile = location;
		BufferedReader br = null;
		String line = "";
		String latestLine = "";
		// String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
				latestLine = line;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return latestLine;
	}

	public static void writeDataToExcelFile(String fileName, String data) {
		FileWriter fileWriter = null;
		System.out.println(data);
		try {
			fileWriter = new FileWriter(fileName, true);
			fileWriter.append(data);
			fileWriter.append("\n");
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}