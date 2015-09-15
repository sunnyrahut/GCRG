package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.sunny.entities.AutoVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class AutoDAO {
	public AutoVO getAuto() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		AutoVO auto = new AutoVO();

		try {
			ps = con.prepareStatement("SELECT * FROM auto_assign WHERE ASSIGN=?");
			ps.setString(1, "Assign");
			rs = ps.executeQuery();

			if (rs.next()) {
				auto.setAuto(rs.getBoolean("AUTO"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return auto;
	}

	public AutoVO updateAuto(AutoVO auto) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn.prepareStatement(
					"update auto_assign set AUTO=? where ASSIGN=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setBoolean(1, auto.isAuto());
			preStmt.setString(2, "Assign");
			preStmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return auto;
	}
}
