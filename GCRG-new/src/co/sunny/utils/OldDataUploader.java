package co.sunny.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.NoGapFilledDataVO;
import co.sunny.exception.GCRGException;

public class OldDataUploader {

	public void uploadNoGapData(String[] parameters) throws GCRGException,
			ParseException {
		int lineNo = 0;
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				(int) Float.parseFloat(parameters[2]));
		calendar.set(Calendar.YEAR, (int) Float.parseFloat(parameters[0]));
		calendar.set(Calendar.MONTH, (int) Float.parseFloat(parameters[1]) - 1);
		calendar.set(Calendar.HOUR_OF_DAY,
				(int) Float.parseFloat(parameters[3]));
		System.out.println("Minute: "
				+ ((Float.parseFloat(parameters[4]) % 1) * 48) % 2);
		if (((Float.parseFloat(parameters[4]) % 1) * 48) % 2 < 1) {
			calendar.set(Calendar.MINUTE, 00);
		} else {
			calendar.set(Calendar.MINUTE, 30);
		}
		String newFormat = "yyyy/MM/dd HH:mm";
		SimpleDateFormat sdf;
		try {
			NoGapFilledDataVO dataVo = new NoGapFilledDataVO();
			sdf = new SimpleDateFormat(newFormat);
			Date date = new Date(calendar.getTimeInMillis());
			System.out.println("date:" + sdf.format(date));
			dataVo.setTimeStamp(sdf.format(date));

			if (!parameters[5].equals("") && parameters[5] != "0") {
				System.out.println("H:" + parameters[5]);
				dataVo.setH(Float.parseFloat(parameters[5]));
			} else {
				dataVo.setH(-9999);
			}

			if (!parameters[15].equals("") && parameters[15] != "0") {
				System.out.println("Air pressure:" + parameters[15]);
				dataVo.setAir_pressure(Float.parseFloat(parameters[15]));
			} else {
				dataVo.setAir_pressure(-9999);
			}

			if (!parameters[13].equals("") && parameters[13] != "0") {
				System.out.println("CO2 mixing ratio:" + parameters[13]);
				dataVo.setCo2_mixing_ratio(Float.parseFloat(parameters[13]));
			} else {
				dataVo.setCo2_mixing_ratio(-9999);
			}

			if (!parameters[7].equals("") && parameters[7] != "0") {
				System.out.println("CO2 flux:" + parameters[7]);
				dataVo.setCo2_flux(Float.parseFloat(parameters[7]));
			} else {
				dataVo.setCo2_flux(-9999);
			}

			if (!parameters[8].equals("") && parameters[8] != "0") {
				System.out.println("H2O flux:" + parameters[8]);
				dataVo.setH2o_flux(Float.parseFloat(parameters[8]));
			} else {
				dataVo.setH2o_flux(-9999);
			}

			if (!parameters[14].equals("") && parameters[14] != "0") {
				System.out.println("H2O mixing ratio:" + parameters[14]);
				dataVo.setH2o_mixing_ratio(Float.parseFloat(parameters[14]));
			} else {
				dataVo.setH2o_mixing_ratio(-9999);
			}

			if (!parameters[6].equals("") && parameters[6] != "0") {
				System.out.println("LE:" + parameters[6]);
				dataVo.setLE(Float.parseFloat(parameters[6]));
			} else {
				dataVo.setLE(-9999);
			}

			if (!parameters[9].equals("")) {
				System.out.println("Qc H:" + parameters[9]);
				dataVo.setQc_H(Float.parseFloat(parameters[9]));
			} else {
				dataVo.setQc_H(-9999);
			}

			if (!parameters[10].equals("")) {
				System.out.println("Qc LE:" + parameters[10]);
				dataVo.setQc_LE(Float.parseFloat(parameters[10]));
			} else {
				dataVo.setQc_LE(-9999);
			}

			if (!parameters[16].equals("") && parameters[16] != "0") {
				System.out.println("RH:" + parameters[16]);
				dataVo.setRH(Float.parseFloat(parameters[16]));
			} else {
				dataVo.setRH(-9999);
			}

			if (!parameters[20].equals("") && parameters[20] != "0") {
				System.out.println("u*:" + parameters[20]);
				dataVo.setuStar(Float.parseFloat(parameters[20]));
			} else {
				dataVo.setuStar(-9999);
			}

			if (!parameters[17].equals("") && parameters[17] != "0") {
				System.out.println("VPD:" + parameters[17]);
				dataVo.setVPD(Float.parseFloat(parameters[17]) / 1000);
			} else {
				dataVo.setVPD(-9999);
			}

			if (!parameters[19].equals("") && parameters[19] != "0") {
				System.out.println("Wind dir:" + parameters[19]);
				dataVo.setWind_dir(Float.parseFloat(parameters[19]));
			} else {
				dataVo.setWind_dir(-9999);
			}

			if (!parameters[18].equals("") && parameters[18] != "0") {
				System.out.println("Wind speed:" + parameters[18]);
				dataVo.setWindSpeed(Float.parseFloat(parameters[18]));
			} else {
				dataVo.setWindSpeed(-9999);
			}

			try {
				atq.addNoGapData(dataVo, conn, preStmt, rs);
				System.out.println("Uploaded");
			} catch (GCRGException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lineNo++;
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		System.out.println("Done with " + lineNo + "rows");
	}
}