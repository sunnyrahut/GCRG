package co.sunny.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import co.sunny.entities.CSVData;
import co.sunny.entities.GenerateCSVVO;

public class GenerateCSVFile {
	public static void generate(List<CSVData> list, GenerateCSVVO data) {
		FileWriter fileWriter = null;
		// Date now = new Date();
		// String format = "MM/dd/yyyy HH:mm";
		// SimpleDateFormat sdf = new SimpleDateFormat(format);
		// try {
		// now = sdf.parse(now.toString());
		// System.out.println(now);
		// } catch (ParseException e1) {
		// // TODO Auto-generated catch block
		// e1.printStackTrace();
		// }
		File fileName = new File("C:\\Users\\Sunny\\Desktop\\"
				+ data.getDataType() + data + ".csv");
		try {
			fileWriter = new FileWriter(fileName, true);
			fileWriter.append("Time" + "," + data);
			fileWriter.append("\n");
			for (int i = 0; i < list.size(); i++) {
				fileWriter.append(list.get(i).getTimeStamp() + ","
						+ list.get(i).getParameter());
				fileWriter.append("\n");
			}
			fileWriter.flush();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
