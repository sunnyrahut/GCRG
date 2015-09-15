package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.OwnerVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class OwnerDAO {

	public List<OwnerVO> getAllPeople() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<OwnerVO> people = new ArrayList<OwnerVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM restaurant_profile");
			rs = ps.executeQuery();

			while (rs.next()) {
				OwnerVO owner = new OwnerVO();
				owner.setRoleId(rs.getInt("ROLE_ID"));
				owner.setRole(rs.getString("ROLE"));
				owner.setFirstName(rs.getString("FIRST_NAME"));
				owner.setEmail(rs.getString("EMAIL"));
				owner.setPhone(rs.getString("PHONE"));
				people.add(owner);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return people;
	}

	public OwnerVO getOwner(int id) throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		OwnerVO owner = new OwnerVO();

		try {
			ps = con.prepareStatement("SELECT * FROM restaurant_profile WHERE ROLE_ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				owner.setRoleId(rs.getInt("ROLE_ID"));
				owner.setRole(rs.getString("ROLE"));
				owner.setFirstName(rs.getString("FIRST_NAME"));
				owner.setEmail(rs.getString("EMAIL"));
				owner.setPhone(rs.getString("PHONE"));
			} else {
				throw new GCRGException("Owner with the ID=" + id
						+ " not found in the system.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return owner;
	}

	public OwnerVO addOwner(OwnerVO owner) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO restaurant_profile (ROLE_ID, ROLE, FIRST_NAME, PHONE, EMAIL) VALUES (?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, owner.getRoleId());
			preStmt.setString(2, owner.getRole());
			preStmt.setString(3, owner.getFirstName());
			preStmt.setString(4, owner.getPhone());
			preStmt.setString(5, owner.getEmail());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return owner;
	}

	public OwnerVO updateOwner(OwnerVO owner) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"UPDATE restaurant_profile SET FIRST_NAME=?, EMAIL=?, PHONE=?, ROLE=? WHERE ROLE_ID=?",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(5, owner.getRoleId());
			preStmt.setString(4, owner.getRole());
			preStmt.setString(1, owner.getFirstName());
			preStmt.setString(3, owner.getPhone());
			preStmt.setString(2, owner.getEmail());
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("OwnerLogin with the ID="
						+ owner.getRoleId() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return owner;
	}

	public List<OwnerVO> deleteOwner(int id) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<OwnerVO> people = new ArrayList<OwnerVO>();
		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM restaurant_profile WHERE ROLE_ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, id);
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("Owner with the ID=" + id
						+ " not found in the system.");
			}

			rs = conn.prepareStatement("SELECT * FROM restaurant_profile")
					.executeQuery();

			while (rs.next()) {
				OwnerVO owner = new OwnerVO();
				owner.setRoleId(rs.getInt("ROLE_ID"));
				owner.setRole(rs.getString("ROLE"));
				owner.setFirstName(rs.getString("FIRST_NAME"));
				owner.setEmail(rs.getString("EMAIL"));
				owner.setPhone(rs.getString("PHONE"));
				people.add(owner);
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
