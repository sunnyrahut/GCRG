package co.sunny.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MetDataTest {
	public static void main(String[] args) {

		BufferedReader br = null;
		String data = "";
		try {
			br = new BufferedReader(
					new FileReader(
							"C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Met\\AK_Atqasuk.csv"));
			try {
				MetUploader dbUploader = new MetUploader();
				String[] parameters;
				int countRow = 0;
				while ((data = br.readLine()) != null) {
					countRow++;
					if (countRow > 2) {
						parameters = data.split(",");
						try {
							dbUploader.uploadMeteoData(parameters);
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
