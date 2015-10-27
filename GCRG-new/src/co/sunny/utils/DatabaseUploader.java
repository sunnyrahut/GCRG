package co.sunny.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.GapFilledDataVO;
import co.sunny.entities.MeteorologicalDataVO;
import co.sunny.entities.NoGapFilledDataVO;
import co.sunny.exception.GCRGException;

public class DatabaseUploader {

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
				+ ((Float.parseFloat(parameters[4]) % 1) * 24) % 1);
		if ((((Float.parseFloat(parameters[4]) % 1) * 24) % 1) >= 0.9
				|| (((Float.parseFloat(parameters[4]) % 1) * 24) % 1) <= 0.1) {
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

			System.out.println("H:" + parameters[5]);
			dataVo.setH(Float.parseFloat(parameters[5]));

			System.out.println("Air pressure:" + parameters[18]);
			dataVo.setAir_pressure(Float.parseFloat(parameters[18]));

			System.out.println("CH4 mixing ratio:" + parameters[17]);
			dataVo.setCh4_mixing_ratio(Float.parseFloat(parameters[17]));

			System.out.println("CO2 mixing ratio:" + parameters[15]);
			dataVo.setCo2_mixing_ratio(Float.parseFloat(parameters[15]));

			System.out.println("CO2 flux:" + parameters[7]);
			dataVo.setCo2_flux(Float.parseFloat(parameters[7]));

			System.out.println("H2O flux:" + parameters[9]);
			dataVo.setH2o_flux(Float.parseFloat(parameters[9]));

			System.out.println("H2O mixing ratio:" + parameters[16]);
			dataVo.setH2o_mixing_ratio(Float.parseFloat(parameters[16]));

			System.out.println("LE:" + parameters[6]);
			dataVo.setLE(Float.parseFloat(parameters[6]));

			System.out.println("QC ch4 flux:" + parameters[13]);
			dataVo.setQc_ch4_flux(Float.parseFloat(parameters[13]));

			System.out.println("Qc H:" + parameters[10]);
			dataVo.setQc_H(Float.parseFloat(parameters[10]));

			System.out.println("Qc LE:" + parameters[11]);
			dataVo.setQc_LE(Float.parseFloat(parameters[11]));

			System.out.println("RH:" + parameters[19]);
			dataVo.setRH(Float.parseFloat(parameters[19]));

			System.out.println("u*:" + parameters[23]);
			dataVo.setuStar(Float.parseFloat(parameters[23]));

			System.out.println("VPD:" + parameters[20]);
			dataVo.setVPD(Float.parseFloat(parameters[20]));

			System.out.println("Wind dir:" + parameters[22]);
			dataVo.setWind_dir(Float.parseFloat(parameters[22]));

			System.out.println("Wind speed:" + parameters[21]);
			dataVo.setWindSpeed(Float.parseFloat(parameters[21]));

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

	public void uploadMeteoData(String[] parameters) throws GCRGException {
		int lineNo = 0;
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, Integer.parseInt(parameters[2]));
		calendar.set(Calendar.YEAR, Integer.parseInt(parameters[1]));
		calendar.set(Calendar.HOUR_OF_DAY,
				Integer.parseInt(parameters[3]) / 100);
		calendar.set(Calendar.MINUTE, Integer.parseInt(parameters[3]) % 100);
		// String oldFormat = "MM/dd/yyyy HH:mm";
		String newFormat = "yyyy/MM/dd HH:mm";
		SimpleDateFormat sdf;
		MeteorologicalDataVO dataVo;
		try {
			dataVo = new MeteorologicalDataVO();
			// use comma as separator
			sdf = new SimpleDateFormat(newFormat);
			Date date = new Date(calendar.getTimeInMillis());
			System.out.println("date:" + sdf.format(date));
			dataVo.setTimeStamp(sdf.format(date));

			dataVo.setAir_T_AVG_L(Float.parseFloat(parameters[14]));
			System.out.println("Air_T_AVG_L:" + parameters[14]);

			dataVo.setG_1_AVG_L(Float.parseFloat(parameters[16]));
			System.out.println("G_1_AVG_L:" + parameters[16]);

			dataVo.setG_2_AVG_L(Float.parseFloat(parameters[17]));
			System.out.println("G_2_AVG_L:" + parameters[17]);

			dataVo.setG_3_AVG_L(Float.parseFloat(parameters[18]));
			System.out.println("G_3_AVG_L:" + parameters[18]);

			dataVo.setP2_SWC_15_AVG_L(Float.parseFloat(parameters[45]));
			System.out.println("P2_SWC_15_AVG_L:" + parameters[45]);

			dataVo.setP2_SWC_30_AVG_L(Float.parseFloat(parameters[46]));
			System.out.println("P2_SWC_30_AVG_L:" + parameters[46]);

			dataVo.setP2_SWC_5_AVG_L(Float.parseFloat(parameters[44]));
			System.out.println("P2_SWC_5_AVG_L:" + parameters[44]);

			dataVo.setP3_SolT15_AVG_L(Float.parseFloat(parameters[51]));
			System.out.println("P3_SolT15_AVG_L:" + parameters[51]);

			dataVo.setP3_SolT30_AVG_L(Float.parseFloat(parameters[52]));
			System.out.println("P3_SolT30_AVG_L:" + parameters[52]);

			dataVo.setP3_SolT5_AVG_L(Float.parseFloat(parameters[50]));
			System.out.println("P3_SolT5_AVG_L:" + parameters[50]);

			dataVo.setP3_SWC_15_AVG_L(Float.parseFloat(parameters[50]));
			System.out.println("P3_SWC_15_AVG_L:" + parameters[50]);

			dataVo.setP3_SWC_30_AVG_L(Float.parseFloat(parameters[51]));
			System.out.println("P3_SWC_30_AVG_L:" + parameters[51]);

			dataVo.setP3_SWC_5_AVG_L(Float.parseFloat(parameters[47]));
			System.out.println("P3_SWC_5_AVG_L:" + parameters[47]);

			dataVo.setP4_SolT15_AVG_L(Float.parseFloat(parameters[54]));
			System.out.println("P4_SolT15_AVG_L:" + parameters[54]);

			dataVo.setP4_SolT30_AVG_L(Float.parseFloat(parameters[55]));
			System.out.println("P4_SolT30_AVG_L:" + parameters[55]);

			dataVo.setP4_SolT5_AVG_L(Float.parseFloat(parameters[53]));
			System.out.println("P4_SolT5_AVG_L:" + parameters[53]);

			dataVo.setPAR_AVG_L(Float.parseFloat(parameters[5]));
			System.out.println("PAR_AVG_L:" + parameters[5]);

			dataVo.setPPT_TOT_L(Float.parseFloat(parameters[20]));
			System.out.println("PPT_TOT_L:" + parameters[20]);

			dataVo.setPress_mb_AVG_L(Float.parseFloat(parameters[26]));
			System.out.println("Press_mb_AVG_L:" + parameters[26]);

			dataVo.setRH_AVG_L(Float.parseFloat(parameters[15]));
			System.out.println("RH_AVG_L:" + parameters[15]);

			dataVo.setRnet_WC_AVG_L(Float.parseFloat(parameters[9]));
			System.out.println("Rnet_WC_AVG_L:" + parameters[9]);

			dataVo.setRsolar_AVG_L(Float.parseFloat(parameters[6]));
			System.out.println("Rsolar_AVG_L:" + parameters[6]);

			dataVo.setSnowDepth_L(Float.parseFloat(parameters[56]));
			System.out.println("SnowDepth_L:" + parameters[56]);

			dataVo.setSS_Dif_R_AVG_L(Float.parseFloat(parameters[28]));
			System.out.println("SS_Dif_R_AVG_L:" + parameters[28]);

			dataVo.setSS_Tl_R_AVG_L(Float.parseFloat(parameters[27]));
			System.out.println("SS_Tl_R_AVG_L:" + parameters[27]);

			dataVo.setSWC_1_AVG_L(Float.parseFloat(parameters[40]));
			System.out.println("SWC_1_AVG_L:" + parameters[40]);

			dataVo.setSWC_2_AVG_L(Float.parseFloat(parameters[41]));
			System.out.println("SWC_2_AVG_L:" + parameters[41]);

			dataVo.setSWC_3_AVG_L(Float.parseFloat(parameters[42]));
			System.out.println("SWC_3_AVG_L:" + parameters[42]);

			dataVo.setSWC_4_AVG_L(Float.parseFloat(parameters[43]));
			System.out.println("SWC_4_AVG_L:" + parameters[43]);

			try {
				atq.addMeteoData(dataVo, conn, preStmt, rs);
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

	public void uploadGapFilledData(GapFilledDataVO data) throws GCRGException {
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;

		try {
			atq.addGapData(data, conn, preStmt, rs);
			System.out.println("Uploaded");
		} catch (GCRGException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
	}
}