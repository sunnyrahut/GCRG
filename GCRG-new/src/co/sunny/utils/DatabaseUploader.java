package co.sunny.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.NoGapFilledDataVO;
import co.sunny.exception.GCRGException;

public class DatabaseUploader {

	public void run(String[] parameters) throws GCRGException {
		int lineNo = 0;
		AtqasukDAO atq = new AtqasukDAO();
		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		// String oldFormat = "MM/dd/yyyy HH:mm";
		String newFormat = "yyyy/MM/dd HH:mm";
		SimpleDateFormat sdf;
		try {
			NoGapFilledDataVO dataVo = new NoGapFilledDataVO();
			// use comma as separator
			try {
				sdf = new SimpleDateFormat(newFormat);
				Date date = sdf.parse(parameters[1].replace("-", "/") + " "
						+ parameters[2]);
				System.out.println("date:" + sdf.format(date));
				dataVo.setTimeStamp(sdf.format(date));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("H:" + parameters[10]);
			dataVo.setH(Float.parseFloat(parameters[10]));

			System.out.println("Air pressure:" + parameters[60]);
			dataVo.setAir_pressure(Float.parseFloat(parameters[60]));

			System.out.println("CH4 mixing ratio:" + parameters[50]);
			dataVo.setCh4_mixing_ratio(Float.parseFloat(parameters[50]));

			System.out.println("CO2 flux:" + parameters[16]);
			dataVo.setCo2_flux(Float.parseFloat(parameters[16]));

			System.out.println("CH4 flux:" + parameters[22]);
			dataVo.setCo2_flux(Float.parseFloat(parameters[22]));

			System.out.println("CO2 mixing ratio:" + parameters[40]);
			dataVo.setCo2_mixing_ratio(Float.parseFloat(parameters[40]));

			System.out.println("H2O flux:" + parameters[19]);
			dataVo.setH2o_flux(Float.parseFloat(parameters[19]));

			System.out.println("H2O mixing ratio:" + parameters[45]);
			dataVo.setH2o_mixing_ratio(Float.parseFloat(parameters[45]));

			System.out.println("LE:" + parameters[13]);
			dataVo.setLE(Float.parseFloat(parameters[13]));

			System.out.println("QC ch4 flux:" + parameters[23]);
			dataVo.setQc_ch4_flux(Float.parseFloat(parameters[23]));

			System.out.println("QC CO2 flux:" + parameters[17]);
			dataVo.setQc_co2_flux(Float.parseFloat(parameters[17]));

			System.out.println("Qc H:" + parameters[11]);
			dataVo.setQc_H(Float.parseFloat(parameters[11]));

			System.out.println("Qc H2O flux:" + parameters[20]);
			dataVo.setQc_h2o_flux(Float.parseFloat(parameters[20]));

			System.out.println("Qc LE:" + parameters[14]);
			dataVo.setQc_LE(Float.parseFloat(parameters[14]));

			System.out.println("RH:" + parameters[68]);
			dataVo.setRH(Float.parseFloat(parameters[68]));

			System.out.println("u*:" + parameters[83]);
			dataVo.setuStar(Float.parseFloat(parameters[83]));

			System.out.println("VPD:" + parameters[69]);
			dataVo.setVPD(Float.parseFloat(parameters[69]));

			System.out.println("Wind dir:" + parameters[79]);
			dataVo.setWind_dir(Float.parseFloat(parameters[79]));

			System.out.println("Wind speed:" + parameters[77]);
			dataVo.setWindSpeed(Float.parseFloat(parameters[77]));

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