package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
				data.setCo2_flux(rs.getFloat("co2_flux"));
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
							"INSERT INTO atq_no_gap_filled_cleaned (time_stamp, air_pressure, ch4_flux, ch4_mixing_ratio, co2_mixing_ratio, H, co2_flux, h2o_flux, h2o_mixing_ratio, LE, qc_ch4_flux, qc_H, qc_LE, RH, uStar, VPD, wind_dir, wind_speed) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			// date = format.parse(data.getTimeStamp());
			preStmt.setString(1, data.getTimeStamp());
			preStmt.setFloat(2, data.getAir_pressure());
			preStmt.setFloat(3, data.getCh4_flux());
			preStmt.setFloat(4, data.getCh4_mixing_ratio());
			preStmt.setFloat(5, data.getCo2_mixing_ratio());
			preStmt.setFloat(6, data.getH());
			preStmt.setFloat(7, data.getCo2_flux());
			preStmt.setFloat(8, data.getH2o_flux());
			preStmt.setFloat(9, data.getH2o_mixing_ratio());
			preStmt.setFloat(10, data.getLE());
			preStmt.setFloat(11, data.getQc_ch4_flux());
			preStmt.setFloat(12, data.getQc_H());
			preStmt.setFloat(13, data.getQc_LE());
			preStmt.setFloat(14, data.getRH());
			preStmt.setFloat(15, data.getuStar());
			preStmt.setFloat(16, data.getVPD());
			preStmt.setFloat(17, data.getWind_dir());
			preStmt.setFloat(18, data.getWindSpeed());

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

			preStmt.setFloat(2, data.getNEE_f());
			preStmt.setFloat(3, data.getNEE_fqcOK());
			preStmt.setFloat(4, data.getLE_f());
			preStmt.setFloat(5, data.getLE_fqcOK());
			preStmt.setFloat(6, data.getH_fqcOK());
			preStmt.setFloat(7, data.getH_f());
			preStmt.setFloat(8, data.getReco());
			preStmt.setFloat(9, data.getGPP_f());

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

			preStmt.setFloat(2, data.getPAR_AVG_L());
			preStmt.setFloat(3, data.getRsolar_AVG_L());
			preStmt.setFloat(4, data.getRnet_WC_AVG_L());
			preStmt.setFloat(5, data.getAir_T_AVG_L());
			preStmt.setFloat(6, data.getRH_AVG_L());
			preStmt.setFloat(7, data.getG_1_AVG_L());
			preStmt.setFloat(8, data.getG_2_AVG_L());
			preStmt.setFloat(9, data.getG_3_AVG_L());
			preStmt.setFloat(10, data.getPPT_TOT_L());
			preStmt.setFloat(11, data.getPress_mb_AVG_L());
			preStmt.setFloat(12, data.getSS_Tl_R_AVG_L());
			preStmt.setFloat(13, data.getSS_Dif_R_AVG_L());
			preStmt.setFloat(14, data.getSWC_1_AVG_L());
			preStmt.setFloat(15, data.getSWC_2_AVG_L());
			preStmt.setFloat(16, data.getSWC_3_AVG_L());
			preStmt.setFloat(17, data.getSWC_4_AVG_L());
			preStmt.setFloat(18, data.getP2_SWC_5_AVG_L());
			preStmt.setFloat(19, data.getP2_SWC_15_AVG_L());
			preStmt.setFloat(20, data.getP2_SWC_30_AVG_L());
			preStmt.setFloat(21, data.getP3_SWC_5_AVG_L());
			preStmt.setFloat(22, data.getP3_SWC_15_AVG_L());
			preStmt.setFloat(23, data.getP3_SWC_30_AVG_L());
			preStmt.setFloat(24, data.getP3_SolT5_AVG_L());
			preStmt.setFloat(25, data.getP3_SolT15_AVG_L());
			preStmt.setFloat(26, data.getP3_SolT30_AVG_L());
			preStmt.setFloat(27, data.getP4_SolT5_AVG_L());
			preStmt.setFloat(28, data.getP4_SolT15_AVG_L());
			preStmt.setFloat(29, data.getP4_SolT30_AVG_L());
			preStmt.setFloat(30, data.getSnowDepth_L());

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

		System.out.println("Time: " + generateCSV);
		try {
			ps = con.prepareStatement("SELECT " + generateCSV.getParameter()
					+ ",time_stamp FROM " + generateCSV.getDataType()
					+ " WHERE time_stamp<=? AND time_stamp>=?");
			ps.setString(1, generateCSV.getTimeStampTo());
			ps.setString(2, generateCSV.getTimeStampFrom());
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
