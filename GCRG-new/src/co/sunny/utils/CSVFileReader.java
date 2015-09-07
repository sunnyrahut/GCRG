package co.sunny.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.DataVO;
import co.sunny.exception.EateryException;

public class CSVFileReader {

	public void run(String location) throws EateryException {
		String csvFile = location;
		// location.contains("notGap");
		// location.contains("Meteorological");
		BufferedReader br = null;
		BufferedWriter bw = null;
		String line = "";
		String cvsSplitBy = ",";
		int lineNo = 0;
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		File file = new File("C:\\Users\\Sunny\\Downloads\\output.txt");
		// if file doesnt exists, then create it
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String oldFormat = "MM/dd/yyyy HH:mm";
		String newFormat = "yyyy/MM/dd HH:mm";
		SimpleDateFormat sdf;
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			br = new BufferedReader(new FileReader(csvFile));
			DataVO dataVo = new DataVO();
			while ((line = br.readLine()) != null) {

				// use comma as separator
				String[] data = line.split(cvsSplitBy);
				if (lineNo > 2) {
					try {
						sdf = new SimpleDateFormat(oldFormat);
						Date date = sdf.parse(data[0]);
						sdf.applyPattern(newFormat);
						System.out.println("date:" + sdf.format(date));
						bw.write(sdf.format(date) + "\t\t");
						dataVo.setTimeStamp(sdf.format(date));
					} catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					if (data[146].equals("")) {
						data[146] = "0";
					}
					System.out.println("Ch4:" + data[146]);
					bw.write(data[146]);
					dataVo.setCh4Ppm(Float.parseFloat(data[146]));
					bw.newLine();

					if (data[20].equals("")) {
						data[20] = "0";
					}
					System.out.println("CO2:" + data[20]);
					bw.write(data[20]);
					dataVo.setCo2Flux(Float.parseFloat(data[20]));
					bw.newLine();

					if (data[23].equals("")) {
						data[23] = "0";
					}
					System.out.println("H2O:" + data[23]);
					bw.write(data[23]);
					dataVo.setH2oFlux(Float.parseFloat(data[23]));
					bw.newLine();
					try {
						atq.addData(dataVo, conn, preStmt, rs);
						System.out.println("Uploading...");
					} catch (EateryException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				lineNo++;
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
			if (br != null) {
				try {
					br.close();
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println("Done" + lineNo);
	}
}