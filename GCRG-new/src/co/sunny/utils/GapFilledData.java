package co.sunny.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import co.sunny.entities.GapFilledDataVO;
import co.sunny.exception.GCRGException;

public class GapFilledData {
	public void readGapFilledData() {
		String excelFilePath = "C:\\Users\\Sunny\\Documents\\automated_eddy_covariance\\Gap Filled\\ATQ_Gapfilled_Data_2015.xlsx";
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Workbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();
		GapFilledDataVO data = new GapFilledDataVO();
		int rowNumber = 0;
		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			rowNumber++;
			System.out.println("Row number: " + rowNumber);
			if (rowNumber > 2) {
				Iterator<Cell> cellIterator = nextRow.cellIterator();
				int cellNumber = 0;
				Calendar calendar = Calendar.getInstance();
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					cellNumber++;
					System.out.println("Cell number: " + cellNumber);
					switch (cellNumber) {
					case 1:
						calendar.set(Calendar.DAY_OF_MONTH,
								(int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 2:
						calendar.set(Calendar.MONTH,
								(int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 3:
						calendar.set(Calendar.YEAR,
								(int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 4:
						calendar.set(Calendar.HOUR_OF_DAY,
								(int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 5:
						calendar.set(Calendar.MINUTE,
								(int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 27:
						data.setNEE_f((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 33:
						data.setNEE_fqcOK((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 34:
						data.setLE_f((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 40:
						data.setLE_fqcOK((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 41:
						data.setH_f((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 47:
						data.setH_fqcOK((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 148:
						data.setReco((float) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					case 149:
						data.setGPP_f((int) cell.getNumericCellValue());
						System.out.println(cell.getNumericCellValue());
						break;
					}
					String newFormat = "yyyy/MM/dd HH:mm";
					SimpleDateFormat sdf;
					sdf = new SimpleDateFormat(newFormat);
					Date date = new Date(calendar.getTimeInMillis());
					System.out.println("date:" + sdf.format(date));
					data.setTimeStamp(sdf.format(date));
				}
				DatabaseUploader upload = new DatabaseUploader();
				try {
					upload.uploadGapFilledData(data);
					System.out.println("Done uploading..");
				} catch (GCRGException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			try {
				workbook.close();
				inputStream.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
