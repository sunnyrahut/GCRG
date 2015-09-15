package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.NoGapDataVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class AtqasukDAO {

	public List<NoGapDataVO> getAllData() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<NoGapDataVO> allData = new ArrayList<NoGapDataVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM atq_no_gap_filled");
			rs = ps.executeQuery();

			while (rs.next()) {
				NoGapDataVO data = new NoGapDataVO();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setAir_pressure(rs.getFloat("air_pressure"));
				data.setCh4_flux(rs.getFloat("ch4_flux"));
				data.setCh4_mixing_ratio(rs.getFloat("ch4_mixing_ratio"));
				data.setCo2_flux(rs.getFloat("co2_flux"));
				data.setCo2_mixing_ratio(rs.getFloat("co2_mixing_ratio"));
				data.setH(rs.getFloat("H"));
				data.setH2o_flux(rs.getFloat("h2o_flux"));
				data.setH2o_mixing_ratio(rs.getFloat("h2o_mixing_ratio"));
				data.setLE(rs.getFloat("LE"));
				data.setQc_ch4_flux(rs.getFloat("ch4_flux"));
				data.setQc_co2_flux(rs.getFloat("co2_flux"));
				data.setQc_H(rs.getFloat("qc_H"));
				data.setQc_h2o_flux(rs.getFloat("qc_h2o_flux"));
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

	public NoGapDataVO addData(NoGapDataVO data, Connection conn,
			PreparedStatement preStmt, ResultSet rs) throws GCRGException {

		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO atq_no_gap_filled (time_stamp, air_pressure, ch4_flux, ch4_mixing_ratio, co2_flux, co2_mixing_ratio, H, h2o_flux, h2o_mixing_ratio, LE, qc_ch4_flux, qc_co2_flux, qc_H, qc_h2o_flux, qc_LE, RH, uStar, VPD, wind_dir, wind_speed) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
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

			if (data.getCo2_flux() != 0) {
				preStmt.setFloat(5, data.getCo2_flux());
			} else {
				preStmt.setNull(5, Types.FLOAT);
			}

			if (data.getCo2_mixing_ratio() != 0) {
				preStmt.setFloat(6, data.getCo2_mixing_ratio());
			} else {
				preStmt.setNull(6, Types.FLOAT);
			}

			if (data.getH() != 0) {
				preStmt.setFloat(7, data.getH());
			} else {
				preStmt.setNull(7, Types.FLOAT);
			}

			if (data.getH2o_flux() != 0) {
				preStmt.setFloat(8, data.getH2o_flux());
			} else {
				preStmt.setNull(8, Types.FLOAT);
			}

			if (data.getH2o_mixing_ratio() != 0) {
				preStmt.setFloat(9, data.getH2o_mixing_ratio());
			} else {
				preStmt.setNull(9, Types.FLOAT);
			}

			if (data.getLE() != 0) {
				preStmt.setFloat(10, data.getLE());
			} else {
				preStmt.setNull(10, Types.FLOAT);
			}

			if (data.getQc_ch4_flux() != 0) {
				preStmt.setFloat(11, data.getQc_ch4_flux());
			} else {
				preStmt.setNull(11, Types.FLOAT);
			}

			if (data.getQc_co2_flux() != 0) {
				preStmt.setFloat(12, data.getQc_co2_flux());
			} else {
				preStmt.setNull(12, Types.FLOAT);
			}

			if (data.getQc_H() != 0) {
				preStmt.setFloat(13, data.getQc_H());
			} else {
				preStmt.setNull(13, Types.FLOAT);
			}

			if (data.getQc_h2o_flux() != 0) {
				preStmt.setFloat(14, data.getQc_h2o_flux());
			} else {
				preStmt.setNull(14, Types.FLOAT);
			}

			if (data.getQc_LE() != 0) {
				preStmt.setFloat(15, data.getQc_LE());
			} else {
				preStmt.setNull(15, Types.FLOAT);
			}

			if (data.getRH() != 0) {
				preStmt.setFloat(16, data.getRH());
			} else {
				preStmt.setNull(16, Types.FLOAT);
			}

			if (data.getuStar() != 0) {
				preStmt.setFloat(17, data.getuStar());
			} else {
				preStmt.setNull(17, Types.FLOAT);
			}

			if (data.getVPD() != 0) {
				preStmt.setFloat(18, data.getVPD());
			} else {
				preStmt.setNull(18, Types.FLOAT);
			}

			if (data.getWind_dir() != 0) {
				preStmt.setFloat(19, data.getWind_dir());
			} else {
				preStmt.setNull(19, Types.FLOAT);
			}

			if (data.getWindSpeed() != 0) {
				preStmt.setFloat(20, data.getWindSpeed());
			} else {
				preStmt.setNull(20, Types.FLOAT);
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
}
