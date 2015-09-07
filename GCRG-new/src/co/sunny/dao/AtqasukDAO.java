package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.DataVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.DBConnector;

public class AtqasukDAO {

	public List<DataVO> getAllData() throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<DataVO> allData = new ArrayList<DataVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM atq");
			rs = ps.executeQuery();

			while (rs.next()) {
				DataVO data = new DataVO();
				data.setTimeStamp(rs.getTimestamp("time_stamp").toString());
				data.setCh4Ppm(rs.getFloat("CH4"));
				data.setCh4Ppm(rs.getFloat("co2_flux"));
				data.setCh4Ppm(rs.getFloat("h2o_flux"));
				allData.add(data);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return allData;
	}

	public DataVO addData(DataVO data, Connection conn,
			PreparedStatement preStmt, ResultSet rs) throws EateryException {

		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO atq (time_stamp, CH4, co2_flux, h2o_flux) VALUES (?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			// date = format.parse(data.getTimeStamp());
			preStmt.setString(1, data.getTimeStamp());
			if (data.getCh4Ppm() != 0) {
				preStmt.setFloat(2, data.getCh4Ppm());
			} else {
				preStmt.setNull(2, Types.FLOAT);
			}

			if (data.getCo2Flux() != 0) {
				preStmt.setFloat(3, data.getCo2Flux());
			} else {
				preStmt.setNull(3, Types.FLOAT);
			}
			if (data.getH2oFlux() != 0) {
				preStmt.setFloat(4, data.getH2oFlux());
			} else {
				preStmt.setNull(4, Types.FLOAT);
			}

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		}
		return data;
	}
}
