package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.OwnerLoginVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class OwnerLoginDAO {

	public List<OwnerLoginVO> getAllPeople() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<OwnerLoginVO> people = new ArrayList<OwnerLoginVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM login");
			rs = ps.executeQuery();

			while (rs.next()) {
				OwnerLoginVO ownerLogin = new OwnerLoginVO();
				ownerLogin.setId(rs.getString("LOGIN_ID"));
				ownerLogin.setPassword(rs.getString("LOGIN_PASSWORD"));
				people.add(ownerLogin);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return people;
	}

	public OwnerLoginVO getOwnerLogin(String id) throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		OwnerLoginVO ownerLogin = new OwnerLoginVO();

		try {
			ps = con.prepareStatement("SELECT * FROM login WHERE LOGIN_ID=?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				ownerLogin.setId(rs.getString("LOGIN_ID"));
				ownerLogin.setPassword(rs.getString("LOGIN_PASSWORD"));
			} else {
				throw new GCRGException("OwnerLogin with the ID=" + id
						+ " not found in the system.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return ownerLogin;
	}

	public OwnerLoginVO addOwnerLogin(OwnerLoginVO ownerLogin)
			throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO login (LOGIN_ID, LOGIN_PASSWORD) VALUES (?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, ownerLogin.getId());
			preStmt.setString(2, ownerLogin.getPassword());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return ownerLogin;
	}

	public OwnerLoginVO updateOwnerLogin(OwnerLoginVO ownerLogin)
			throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn.prepareStatement(
					"UPDATE login SET LOGIN_PASSWORD=? WHERE LOGIN_ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, ownerLogin.getPassword());
			preStmt.setString(2, ownerLogin.getId());
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("OwnerLogin with the ID="
						+ ownerLogin.getId() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return ownerLogin;
	}

	public List<OwnerLoginVO> deleteOwnerLogin(String id)
			throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<OwnerLoginVO> people = new ArrayList<OwnerLoginVO>();
		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM login WHERE LOGIN_ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, id);
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("OwnerLogin with the ID=" + id
						+ " not found in the system.");
			}

			rs = conn.prepareStatement("SELECT * FROM login").executeQuery();

			while (rs.next()) {
				OwnerLoginVO ownerLogin = new OwnerLoginVO();
				ownerLogin.setId(rs.getString("LOGIN_ID"));
				ownerLogin.setPassword(rs.getString("LOGIN_PASSWORD"));
				people.add(ownerLogin);
			}

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return people;
	}
}
