package co.sunny.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.MeteorologicalDataVO;
import co.sunny.exception.GCRGException;

public class NewMetDataUploader {
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

			if (!parameters[15].equals("") && !parameters[15].equals("NaN")) {
				dataVo.setAir_T_AVG_L(Float.parseFloat(parameters[15]));
				System.out.println("Air_T_AVG_L:" + parameters[15]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[17].equals("") && !parameters[17].equals("NaN")) {
				dataVo.setG_1_AVG_L(Float.parseFloat(parameters[17]));
				System.out.println("G_1_AVG_L:" + parameters[17]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[18].equals("") && !parameters[18].equals("NaN")) {
				dataVo.setG_2_AVG_L(Float.parseFloat(parameters[18]));
				System.out.println("G_2_AVG_L:" + parameters[18]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[19].equals("") && !parameters[19].equals("NaN")) {
				dataVo.setG_3_AVG_L(Float.parseFloat(parameters[19]));
				System.out.println("G_3_AVG_L:" + parameters[19]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[46].equals("") && !parameters[46].equals("NaN")) {
				dataVo.setP2_SWC_15_AVG_L(Float.parseFloat(parameters[46]));
				System.out.println("P2_SWC_15_AVG_L:" + parameters[46]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[47].equals("") && !parameters[47].equals("NaN")) {
				dataVo.setP2_SWC_30_AVG_L(Float.parseFloat(parameters[47]));
				System.out.println("P2_SWC_30_AVG_L:" + parameters[47]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[45].equals("") && !parameters[45].equals("NaN")) {
				dataVo.setP2_SWC_5_AVG_L(Float.parseFloat(parameters[45]));
				System.out.println("P2_SWC_5_AVG_L:" + parameters[45]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[52].equals("") && !parameters[52].equals("NaN")) {
				dataVo.setP3_SolT15_AVG_L(Float.parseFloat(parameters[52]));
				System.out.println("P3_SolT15_AVG_L:" + parameters[52]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[53].equals("") && !parameters[53].equals("NaN")) {
				dataVo.setP3_SolT30_AVG_L(Float.parseFloat(parameters[53]));
				System.out.println("P3_SolT30_AVG_L:" + parameters[53]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[51].equals("") && !parameters[51].equals("NaN")) {
				dataVo.setP3_SolT5_AVG_L(Float.parseFloat(parameters[51]));
				System.out.println("P3_SolT5_AVG_L:" + parameters[51]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[49].equals("") && !parameters[49].equals("NaN")) {
				dataVo.setP3_SWC_15_AVG_L(Float.parseFloat(parameters[49]));
				System.out.println("P3_SWC_15_AVG_L:" + parameters[49]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[50].equals("") && !parameters[50].equals("NaN")) {
				dataVo.setP3_SWC_30_AVG_L(Float.parseFloat(parameters[50]));
				System.out.println("P3_SWC_30_AVG_L:" + parameters[50]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[48].equals("") && !parameters[48].equals("NaN")) {
				dataVo.setP3_SWC_5_AVG_L(Float.parseFloat(parameters[48]));
				System.out.println("P3_SWC_5_AVG_L:" + parameters[48]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[52].equals("") && !parameters[52].equals("NaN")) {
				dataVo.setP4_SolT15_AVG_L(Float.parseFloat(parameters[52]));
				System.out.println("P4_SolT15_AVG_L:" + parameters[52]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[53].equals("") && !parameters[53].equals("NaN")) {
				dataVo.setP4_SolT30_AVG_L(Float.parseFloat(parameters[53]));
				System.out.println("P4_SolT30_AVG_L:" + parameters[53]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[54].equals("") && !parameters[54].equals("NaN")) {
				dataVo.setP4_SolT5_AVG_L(Float.parseFloat(parameters[54]));
				System.out.println("P4_SolT5_AVG_L:" + parameters[54]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[6].equals("") && !parameters[6].equals("NaN")) {
				dataVo.setPAR_AVG_L(Float.parseFloat(parameters[6]));
				System.out.println("PAR_AVG_L:" + parameters[6]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[21].equals("") && !parameters[21].equals("NaN")) {
				dataVo.setPPT_TOT_L(Float.parseFloat(parameters[21]));
				System.out.println("PPT_TOT_L:" + parameters[21]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[27].equals("") && !parameters[27].equals("NaN")) {
				dataVo.setPress_mb_AVG_L(Float.parseFloat(parameters[27]));
				System.out.println("Press_mb_AVG_L:" + parameters[27]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[16].equals("") && !parameters[16].equals("NaN")) {
				dataVo.setRH_AVG_L(Float.parseFloat(parameters[16]));
				System.out.println("RH_AVG_L:" + parameters[16]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[10].equals("") && !parameters[10].equals("NaN")) {
				dataVo.setRnet_WC_AVG_L(Float.parseFloat(parameters[10]));
				System.out.println("Rnet_WC_AVG_L:" + parameters[10]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[7].equals("") && !parameters[7].equals("NaN")) {
				dataVo.setRsolar_AVG_L(Float.parseFloat(parameters[7]));
				System.out.println("Rsolar_AVG_L:" + parameters[7]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[57].equals("") && !parameters[57].equals("NaN")) {
				dataVo.setSnowDepth_L(Float.parseFloat(parameters[57]));
				System.out.println("SnowDepth_L:" + parameters[57]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[29].equals("") && !parameters[29].equals("NaN")) {
				dataVo.setSS_Dif_R_AVG_L(Float.parseFloat(parameters[29]));
				System.out.println("SS_Dif_R_AVG_L:" + parameters[29]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[28].equals("") && !parameters[28].equals("NaN")) {
				dataVo.setSS_Tl_R_AVG_L(Float.parseFloat(parameters[28]));
				System.out.println("SS_Tl_R_AVG_L:" + parameters[28]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[41].equals("") && !parameters[41].equals("NaN")) {
				dataVo.setSWC_1_AVG_L(Float.parseFloat(parameters[41]));
				System.out.println("SWC_1_AVG_L:" + parameters[41]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[42].equals("") && !parameters[42].equals("NaN")) {
				dataVo.setSWC_2_AVG_L(Float.parseFloat(parameters[42]));
				System.out.println("SWC_2_AVG_L:" + parameters[42]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[43].equals("") && !parameters[43].equals("NaN")) {
				dataVo.setSWC_3_AVG_L(Float.parseFloat(parameters[43]));
				System.out.println("SWC_3_AVG_L:" + parameters[43]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[44].equals("") && !parameters[44].equals("NaN")) {
				dataVo.setSWC_4_AVG_L(Float.parseFloat(parameters[44]));
				System.out.println("SWC_4_AVG_L:" + parameters[44]);
			} else {
				dataVo.setSoil2_2_AVG_L(-9999);
			}

			if (!parameters[16].equals("")) {
				dataVo.setSoil_1_AVG_L(Float.parseFloat(parameters[11]));
				System.out.println("Soil_1_AVG_L:" + parameters[11]);
			} else {
				dataVo.setSoil_1_AVG_L(-9999);
			}
			if (!parameters[17].equals("")) {
				dataVo.setSoil_2_AVG_L(Float.parseFloat(parameters[12]));
				System.out.println("Soil_2_AVG_L:" + parameters[12]);
			} else {
				dataVo.setSoil_2_AVG_L(-9999);
			}
			if (!parameters[18].equals("")) {
				dataVo.setSoil2_1_AVG_L(Float.parseFloat(parameters[13]));
				System.out.println("Soil2_1_AVG_L:" + parameters[13]);
			} else {
				dataVo.setSoil2_1_AVG_L(-9999);
			}
			if (!parameters[19].equals("") && !parameters[19].equals("NaN")) {
				dataVo.setSoil2_2_AVG_L(Float.parseFloat(parameters[14]));
				System.out.println("Soil2_2_AVG_L:" + parameters[14]);
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