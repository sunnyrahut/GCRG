package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.CSVData;
import co.sunny.entities.GapFilledDataVO;
import co.sunny.entities.GenerateCSVVO;
import co.sunny.entities.MeteorologicalDataVO;
import co.sunny.entities.NoGapFilledDataVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class AtqasukDAO {

	public List<NoGapFilledDataVO> getAllNoGapData(String fromDate,
			String toDate) throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<NoGapFilledDataVO> allData = new ArrayList<NoGapFilledDataVO>();
		try {
			ps = con.prepareStatement("SELECT * FROM atq_no_gap_filled_cleaned WHERE time_stamp<=? AND time_stamp>=?");
			ps.setString(1, toDate);
			ps.setString(2, fromDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				NoGapFilledDataVO data = new NoGapFilledDataVO();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setAir_pressure(rs.getFloat("air_pressure"));
				data.setCh4_flux(rs.getFloat("ch4_flux"));
				data.setCh4_mixing_ratio(rs.getFloat("ch4_mixing_ratio"));
				data.setCo2_mixing_ratio(rs.getFloat("co2_mixing_ratio"));
				data.setH(rs.getFloat("H"));
				data.setH2o_flux(rs.getFloat("h2o_flux"));
				data.setH2o_mixing_ratio(rs.getFloat("h2o_mixing_ratio"));
				data.setLE(rs.getFloat("LE"));
				data.setQc_ch4_flux(rs.getFloat("ch4_flux"));
				data.setQc_H(rs.getFloat("qc_H"));
				data.setQc_LE(rs.getFloat("qc_LE"));
				data.setRH(rs.getFloat("RH"));
				data.setuStar(rs.getFloat("uStar"));
				data.setVPD(rs.getFloat("VPD"));
				data.setWind_dir(rs.getFloat("wind_dir"));
				data.setWindSpeed(rs.getFloat("wind_speed"));
				allData.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return allData;
	}

	public NoGapFilledDataVO addNoGapData(NoGapFilledDataVO data,
			Connection conn, PreparedStatement preStmt, ResultSet rs)
			throws GCRGException {

		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO atq_no_gap_filled_cleaned (time_stamp, air_pressure, ch4_flux, ch4_mixing_ratio, co2_mixing_ratio, H, h2o_flux, h2o_mixing_ratio, LE, qc_ch4_flux, qc_H, qc_LE, RH, uStar, VPD, wind_dir, wind_speed) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			// date = format.parse(data.getTimeStamp());
			preStmt.setString(1, data.getTimeStamp());

			if (data.getAir_pressure() != 0) {
				preStmt.setFloat(2, data.getAir_pressure());
			} else {
				preStmt.setNull(2, Types.FLOAT);
			}

			if (data.getCh4_flux() != 0) {
				preStmt.setFloat(3, data.getCh4_flux());
			} else {
				preStmt.setNull(3, Types.FLOAT);
			}

			if (data.getCh4_mixing_ratio() != 0) {
				preStmt.setFloat(4, data.getCh4_mixing_ratio());
			} else {
				preStmt.setNull(4, Types.FLOAT);
			}

			if (data.getCo2_mixing_ratio() != 0) {
				preStmt.setFloat(5, data.getCo2_mixing_ratio());
			} else {
				preStmt.setNull(5, Types.FLOAT);
			}

			if (data.getH() != 0) {
				preStmt.setFloat(6, data.getH());
			} else {
				preStmt.setNull(6, Types.FLOAT);
			}

			if (data.getH2o_flux() != 0) {
				preStmt.setFloat(7, data.getH2o_flux());
			} else {
				preStmt.setNull(7, Types.FLOAT);
			}

			if (data.getH2o_mixing_ratio() != 0) {
				preStmt.setFloat(8, data.getH2o_mixing_ratio());
			} else {
				preStmt.setNull(8, Types.FLOAT);
			}

			if (data.getLE() != 0) {
				preStmt.setFloat(9, data.getLE());
			} else {
				preStmt.setNull(9, Types.FLOAT);
			}

			if (data.getQc_ch4_flux() != 0) {
				preStmt.setFloat(10, data.getQc_ch4_flux());
			} else {
				preStmt.setNull(10, Types.FLOAT);
			}

			if (data.getQc_H() != 0) {
				preStmt.setFloat(11, data.getQc_H());
			} else {
				preStmt.setNull(11, Types.FLOAT);
			}

			if (data.getQc_LE() != 0) {
				preStmt.setFloat(12, data.getQc_LE());
			} else {
				preStmt.setNull(12, Types.FLOAT);
			}

			if (data.getRH() != 0) {
				preStmt.setFloat(13, data.getRH());
			} else {
				preStmt.setNull(13, Types.FLOAT);
			}

			if (data.getuStar() != 0) {
				preStmt.setFloat(14, data.getuStar());
			} else {
				preStmt.setNull(14, Types.FLOAT);
			}

			if (data.getVPD() != 0) {
				preStmt.setFloat(15, data.getVPD());
			} else {
				preStmt.setNull(15, Types.FLOAT);
			}

			if (data.getWind_dir() != 0) {
				preStmt.setFloat(16, data.getWind_dir());
			} else {
				preStmt.setNull(16, Types.FLOAT);
			}

			if (data.getWindSpeed() != 0) {
				preStmt.setFloat(17, data.getWindSpeed());
			} else {
				preStmt.setNull(17, Types.FLOAT);
			}

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		}
		return data;
	}

	public List<GapFilledDataVO> getAllGapData(String fromDate, String toDate)
			throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<GapFilledDataVO> allData = new ArrayList<GapFilledDataVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM atq_gap_filled WHERE time_stamp<=? AND time_stamp>=?");
			ps.setString(1, toDate);
			ps.setString(2, fromDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				GapFilledDataVO data = new GapFilledDataVO();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setGPP_f(rs.getFloat("GPP_f"));
				data.setH_f(rs.getFloat("H_f"));
				data.setH_fqcOK(rs.getFloat("H_fqcOK"));
				data.setLE_f(rs.getFloat("LE_f"));
				data.setLE_fqcOK(rs.getFloat("LE_fqcOK"));
				data.setNEE_f(rs.getFloat("NEE_f"));
				data.setNEE_fqcOK(rs.getFloat("NEE_fqcOK"));
				data.setReco(rs.getFloat("Reco"));
				allData.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return allData;
	}

	public GapFilledDataVO addGapData(GapFilledDataVO data, Connection conn,
			PreparedStatement preStmt, ResultSet rs) throws GCRGException {

		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO atq_gap_filled (time_stamp, NEE_f, NEE_fqcOK, LE_f, LE_fqcOK, H_fqcOK, H_f, Reco, GPP_f) VALUES (?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			// date = format.parse(data.getTimeStamp());
			preStmt.setString(1, data.getTimeStamp());

			if (data.getNEE_f() != 0) {
				preStmt.setFloat(2, data.getNEE_f());
			} else {
				preStmt.setNull(2, Types.FLOAT);
			}

			if (data.getNEE_fqcOK() != 0) {
				preStmt.setFloat(3, data.getNEE_fqcOK());
			} else {
				preStmt.setNull(3, Types.FLOAT);
			}

			if (data.getLE_f() != 0) {
				preStmt.setFloat(4, data.getLE_f());
			} else {
				preStmt.setNull(4, Types.FLOAT);
			}

			if (data.getLE_fqcOK() != 0) {
				preStmt.setFloat(5, data.getLE_fqcOK());
			} else {
				preStmt.setNull(5, Types.FLOAT);
			}

			if (data.getH_fqcOK() != 0) {
				preStmt.setFloat(6, data.getH_fqcOK());
			} else {
				preStmt.setNull(6, Types.FLOAT);
			}

			if (data.getH_f() != 0) {
				preStmt.setFloat(7, data.getH_f());
			} else {
				preStmt.setNull(7, Types.FLOAT);
			}

			if (data.getReco() != 0) {
				preStmt.setFloat(8, data.getReco());
			} else {
				preStmt.setNull(8, Types.FLOAT);
			}

			if (data.getGPP_f() != 0) {
				preStmt.setFloat(9, data.getGPP_f());
			} else {
				preStmt.setNull(9, Types.FLOAT);
			}

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		}
		return data;
	}

	public List<MeteorologicalDataVO> getAllMeteoData(String fromDate,
			String toDate) throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<MeteorologicalDataVO> allData = new ArrayList<MeteorologicalDataVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM atq_meteorological WHERE time_stamp<=? AND time_stamp>=?");
			ps.setString(1, toDate);
			ps.setString(2, fromDate);
			rs = ps.executeQuery();

			while (rs.next()) {
				MeteorologicalDataVO data = new MeteorologicalDataVO();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setAir_T_AVG_L(rs.getFloat("Air_T_AVG_L"));
				data.setG_1_AVG_L(rs.getFloat("G_1_AVG_L"));
				data.setG_2_AVG_L(rs.getFloat("G_2_AVG_L"));
				data.setG_3_AVG_L(rs.getFloat("G_3_AVG_L"));
				data.setP2_SWC_5_AVG_L(rs.getFloat("P2_SWC_5_AVG_L"));
				data.setP2_SWC_15_AVG_L(rs.getFloat("P2_SWC_15_AVG_L"));
				data.setP2_SWC_30_AVG_L(rs.getFloat("P2_SWC_30_AVG_L"));
				data.setP3_SolT15_AVG_L(rs.getFloat("P3_SolT15_AVG_L"));
				data.setP3_SolT30_AVG_L(rs.getFloat("P3_SolT30_AVG_L"));
				data.setP3_SolT5_AVG_L(rs.getFloat("P3_SolT5_AVG_L"));
				data.setP3_SWC_15_AVG_L(rs.getFloat("P3_SWC_15_AVG_L"));
				data.setP3_SWC_30_AVG_L(rs.getFloat("P3_SWC_30_AVG_L"));
				data.setP3_SWC_5_AVG_L(rs.getFloat("P3_SWC_5_AVG_L"));
				data.setP4_SolT15_AVG_L(rs.getFloat("P4_SolT15_AVG_L"));
				data.setP4_SolT30_AVG_L(rs.getFloat("P4_SolT30_AVG_L"));
				data.setP4_SolT5_AVG_L(rs.getFloat("P4_SolT5_AVG_L"));
				data.setPAR_AVG_L(rs.getFloat("PAR_AVG_L"));
				data.setPPT_TOT_L(rs.getFloat("PPT_TOT_L"));
				data.setPress_mb_AVG_L(rs.getFloat("Press_mb_AVG_L"));
				data.setRH_AVG_L(rs.getFloat("RH_AVG_L"));
				data.setRnet_WC_AVG_L(rs.getFloat("Rnet_WC_AVG_L"));
				data.setRsolar_AVG_L(rs.getFloat("Rsolar_AVG_L"));
				data.setSnowDepth_L(rs.getFloat("SnowDepth_L"));
				data.setSS_Dif_R_AVG_L(rs.getFloat("SS_Dif_R_AVG_L"));
				data.setSS_Tl_R_AVG_L(rs.getFloat("SS_Tl_R_AVG_L"));
				data.setSWC_1_AVG_L(rs.getFloat("SWC_1_AVG_L"));
				data.setSWC_2_AVG_L(rs.getFloat("SWC_2_AVG_L"));
				data.setSWC_3_AVG_L(rs.getFloat("SWC_3_AVG_L"));
				data.setSWC_4_AVG_L(rs.getFloat("SWC_4_AVG_L"));
				allData.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return allData;
	}

	public MeteorologicalDataVO addMeteoData(MeteorologicalDataVO data,
			Connection conn, PreparedStatement preStmt, ResultSet rs)
			throws GCRGException {

		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO atq_meteorological (time_stamp, PAR_AVG_L, Rsolar_AVG_L, Rnet_WC_AVG_L, Air_T_AVG_L, RH_AVG_L, G_1_AVG_L, G_2_AVG_L, G_3_AVG_L, PPT_TOT_L, Press_mb_AVG_L, SS_Tl_R_AVG_L, SS_Dif_R_AVG_L, SWC_1_AVG_L, SWC_2_AVG_L, SWC_3_AVG_L, SWC_4_AVG_L, P2_SWC_5_AVG_L, P2_SWC_15_AVG_L, P2_SWC_30_AVG_L, P3_SWC_5_AVG_L, P3_SWC_15_AVG_L, P3_SWC_30_AVG_L, P3_SolT5_AVG_L, P3_SolT15_AVG_L, P3_SolT30_AVG_L, P4_SolT5_AVG_L, P4_SolT15_AVG_L, P4_SolT30_AVG_L, SnowDepth_L) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			// date = format.parse(data.getTimeStamp());
			preStmt.setString(1, data.getTimeStamp());

			if (data.getPAR_AVG_L() != 0) {
				preStmt.setFloat(2, data.getPAR_AVG_L());
			} else {
				preStmt.setNull(2, Types.FLOAT);
			}

			if (data.getRsolar_AVG_L() != 0) {
				preStmt.setFloat(3, data.getRsolar_AVG_L());
			} else {
				preStmt.setNull(3, Types.FLOAT);
			}

			if (data.getRnet_WC_AVG_L() != 0) {
				preStmt.setFloat(4, data.getRnet_WC_AVG_L());
			} else {
				preStmt.setNull(4, Types.FLOAT);
			}

			if (data.getAir_T_AVG_L() != 0) {
				preStmt.setFloat(5, data.getAir_T_AVG_L());
			} else {
				preStmt.setNull(5, Types.FLOAT);
			}

			if (data.getRH_AVG_L() != 0) {
				preStmt.setFloat(6, data.getRH_AVG_L());
			} else {
				preStmt.setNull(6, Types.FLOAT);
			}

			if (data.getG_1_AVG_L() != 0) {
				preStmt.setFloat(7, data.getG_1_AVG_L());
			} else {
				preStmt.setNull(7, Types.FLOAT);
			}

			if (data.getG_2_AVG_L() != 0) {
				preStmt.setFloat(8, data.getG_2_AVG_L());
			} else {
				preStmt.setNull(8, Types.FLOAT);
			}

			if (data.getG_3_AVG_L() != 0) {
				preStmt.setFloat(9, data.getG_3_AVG_L());
			} else {
				preStmt.setNull(9, Types.FLOAT);
			}

			if (data.getPPT_TOT_L() != 0) {
				preStmt.setFloat(10, data.getPPT_TOT_L());
			} else {
				preStmt.setNull(10, Types.FLOAT);
			}

			if (data.getPress_mb_AVG_L() != 0) {
				preStmt.setFloat(11, data.getPress_mb_AVG_L());
			} else {
				preStmt.setNull(11, Types.FLOAT);
			}

			if (data.getSS_Tl_R_AVG_L() != 0) {
				preStmt.setFloat(12, data.getSS_Tl_R_AVG_L());
			} else {
				preStmt.setNull(12, Types.FLOAT);
			}

			if (data.getSS_Dif_R_AVG_L() != 0) {
				preStmt.setFloat(13, data.getSS_Dif_R_AVG_L());
			} else {
				preStmt.setNull(13, Types.FLOAT);
			}

			if (data.getSWC_1_AVG_L() != 0) {
				preStmt.setFloat(14, data.getSWC_1_AVG_L());
			} else {
				preStmt.setNull(14, Types.FLOAT);
			}

			if (data.getSWC_2_AVG_L() != 0) {
				preStmt.setFloat(15, data.getSWC_2_AVG_L());
			} else {
				preStmt.setNull(15, Types.FLOAT);
			}

			if (data.getSWC_3_AVG_L() != 0) {
				preStmt.setFloat(16, data.getSWC_3_AVG_L());
			} else {
				preStmt.setNull(16, Types.FLOAT);
			}

			if (data.getSWC_4_AVG_L() != 0) {
				preStmt.setFloat(17, data.getSWC_4_AVG_L());
			} else {
				preStmt.setNull(17, Types.FLOAT);
			}

			if (data.getP2_SWC_5_AVG_L() != 0) {
				preStmt.setFloat(18, data.getP2_SWC_5_AVG_L());
			} else {
				preStmt.setNull(18, Types.FLOAT);
			}

			if (data.getP2_SWC_15_AVG_L() != 0) {
				preStmt.setFloat(19, data.getP2_SWC_15_AVG_L());
			} else {
				preStmt.setNull(19, Types.FLOAT);
			}

			if (data.getP2_SWC_30_AVG_L() != 0) {
				preStmt.setFloat(20, data.getP2_SWC_30_AVG_L());
			} else {
				preStmt.setNull(20, Types.FLOAT);
			}

			if (data.getP3_SWC_5_AVG_L() != 0) {
				preStmt.setFloat(21, data.getP3_SWC_5_AVG_L());
			} else {
				preStmt.setNull(21, Types.FLOAT);
			}

			if (data.getP3_SWC_15_AVG_L() != 0) {
				preStmt.setFloat(22, data.getP3_SWC_15_AVG_L());
			} else {
				preStmt.setNull(22, Types.FLOAT);
			}

			if (data.getP3_SWC_30_AVG_L() != 0) {
				preStmt.setFloat(23, data.getP3_SWC_30_AVG_L());
			} else {
				preStmt.setNull(23, Types.FLOAT);
			}

			if (data.getP3_SolT5_AVG_L() != 0) {
				preStmt.setFloat(24, data.getP3_SolT5_AVG_L());
			} else {
				preStmt.setNull(24, Types.FLOAT);
			}

			if (data.getP3_SolT15_AVG_L() != 0) {
				preStmt.setFloat(25, data.getP3_SolT15_AVG_L());
			} else {
				preStmt.setNull(25, Types.FLOAT);
			}

			if (data.getP3_SolT30_AVG_L() != 0) {
				preStmt.setFloat(26, data.getP3_SolT30_AVG_L());
			} else {
				preStmt.setNull(26, Types.FLOAT);
			}

			if (data.getP4_SolT5_AVG_L() != 0) {
				preStmt.setFloat(27, data.getP4_SolT5_AVG_L());
			} else {
				preStmt.setNull(27, Types.FLOAT);
			}

			if (data.getP4_SolT15_AVG_L() != 0) {
				preStmt.setFloat(28, data.getP4_SolT15_AVG_L());
			} else {
				preStmt.setNull(28, Types.FLOAT);
			}

			if (data.getP4_SolT30_AVG_L() != 0) {
				preStmt.setFloat(29, data.getP4_SolT30_AVG_L());
			} else {
				preStmt.setNull(29, Types.FLOAT);
			}

			if (data.getSnowDepth_L() != 0) {
				preStmt.setFloat(30, data.getSnowDepth_L());
			} else {
				preStmt.setNull(30, Types.FLOAT);
			}

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		}
		return data;
	}

	public List<CSVData> getParamData(GenerateCSVVO generateCSV)
			throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<CSVData> allData = new ArrayList<CSVData>();

		try {
			ps = con.prepareStatement("SELECT " + generateCSV.getParameter()
					+ ",time_stamp FROM " + generateCSV.getDataType());
			rs = ps.executeQuery();

			while (rs.next()) {
				CSVData data = new CSVData();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setParameter(rs.getString(generateCSV.getParameter()));
				allData.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return allData;
	}
}
