package co.sunny.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.MeteorologicalDataVO;
import co.sunny.exception.GCRGException;

public class MetUploader {
	public void uploadMeteoData(String[] parameters) throws GCRGException {
		int lineNo = 0;
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		MeteorologicalDataVO dataVo;
		String newFormat = "yyyy/MM/dd HH:mm";
		SimpleDateFormat sdf;
		try {
			dataVo = new MeteorologicalDataVO();
			sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
			try {
				Date date = sdf.parse(parameters[1]);
				sdf.applyPattern(newFormat);
				dataVo.setTimeStamp(sdf.format(date));
				System.out.println("date:" + sdf.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// use comma as separator

			if (parameters[5] != null) {
				dataVo.setAir_T_AVG_L(Float.parseFloat(parameters[5]));
				System.out.println("Air_T_AVG_L:" + parameters[5]);
			} else {
				dataVo.setAir_T_AVG_L(-9999);
			}

			if (!parameters[6].equals("")) {
				dataVo.setG_1_AVG_L(Float.parseFloat(parameters[6]));
				System.out.println("G_1_AVG_L:" + parameters[6]);
			} else {
				dataVo.setG_1_AVG_L(-9999);
			}

			if (!parameters[7].equals("")) {
				dataVo.setG_2_AVG_L(Float.parseFloat(parameters[7]));
				System.out.println("G_2_AVG_L:" + parameters[7]);
			} else {
				dataVo.setG_2_AVG_L(-9999);
			}

			if (!parameters[8].equals("")) {
				dataVo.setG_3_AVG_L(Float.parseFloat(parameters[8]));
				System.out.println("G_3_AVG_L:" + parameters[8]);
			} else {
				dataVo.setG_3_AVG_L(-9999);
			}

			if (!parameters[9].equals("")) {
				dataVo.setRH_AVG_L(Float.parseFloat(parameters[9]));
				System.out.println("RH_AVG_L:" + parameters[9]);
			} else {
				dataVo.setRH_AVG_L(-9999);
			}

			if (!parameters[3].equals("")) {
				dataVo.setPAR_AVG_L(Float.parseFloat(parameters[3]));
				System.out.println("PAR_AVG_L:" + parameters[3]);
			} else {
				dataVo.setPAR_AVG_L(-9999);
			}
			if (!parameters[4].equals("")) {
				dataVo.setRnet_WC_AVG_L(Float.parseFloat(parameters[4]));
				System.out.println("Rnet_WC_AVG_L:" + parameters[4]);
			} else {
				dataVo.setRnet_WC_AVG_L(-9999);
			}
			if (!parameters[14].equals("")) {
				dataVo.setRsolar_AVG_L(Float.parseFloat(parameters[14]));
				System.out.println("Rsolar_AVG_L:" + parameters[14]);
			} else {
				dataVo.setRsolar_AVG_L(-9999);
			}
			if (!parameters[16].equals("")) {
				dataVo.setSoil_1_AVG_L(Float.parseFloat(parameters[16]));
				System.out.println("Soil_1_AVG_L:" + parameters[16]);
			} else {
				dataVo.setSoil_1_AVG_L(-9999);
			}
			if (!parameters[17].equals("")) {
				dataVo.setSoil_2_AVG_L(Float.parseFloat(parameters[17]));
				System.out.println("Soil_2_AVG_L:" + parameters[17]);
			} else {
				dataVo.setSoil_2_AVG_L(-9999);
			}
			if (!parameters[18].equals("")) {
				dataVo.setSoil2_1_AVG_L(Float.parseFloat(parameters[18]));
				System.out.println("Soil2_1_AVG_L:" + parameters[18]);
			} else {
				dataVo.setSoil2_1_AVG_L(-9999);
			}
			if (!parameters[19].equals("")) {
				dataVo.setSoil2_2_AVG_L(Float.parseFloat(parameters[19]));
				System.out.println("Soil2_2_AVG_L:" + parameters[19]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

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
}

// alter table atq_meteorological add Soil_1_AVG_L double;