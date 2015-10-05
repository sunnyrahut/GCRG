package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.UserVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class UserDAO {

	public List<UserVO> getAllUsers() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<UserVO> users = new ArrayList<UserVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM users");
			rs = ps.executeQuery();

			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserID(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setPassword(rs.getString("password"));
				user.setTimeStamp(rs.getTimestamp("time_stamp"));
				user.setUserType(rs.getString("user_type"));
				users.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return users;
	}

	public UserVO getUser(int id) throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		UserVO user = new UserVO();

		try {
			ps = con.prepareStatement("SELECT * FROM users WHERE user_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				user.setUserID(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setPassword(rs.getString("password"));
				user.setTimeStamp(rs.getTimestamp("time_stamp"));
				user.setUserType(rs.getString("user_type"));
			} else {
				throw new GCRGException("user with the ID=" + id
						+ " not found in the system.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return user;
	}

	public UserVO addUser(UserVO user) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO users (time_stamp, first_name, last_name, password, email, phone, user_type) VALUES (?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setTimestamp(1, user.getTimeStamp());
			preStmt.setString(2, user.getFirstName());
			preStmt.setString(3, user.getLastName());
			preStmt.setString(4, user.getPassword());
			preStmt.setString(5, user.getEmail());
			preStmt.setString(6, user.getPhone());
			preStmt.setString(7, user.getUserType());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

			if (rs.next()) {
				user.setUserID(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return user;
	}

	public UserVO updateUser(UserVO user) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"UPDATE users SET first_name=?, last_name=?, time_stamp=?, password=?, EMAIL=?, PHONE=?, user_type=? WHERE user_id=?",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, user.getFirstName());
			preStmt.setString(2, user.getLastName());
			preStmt.setTimestamp(3, user.getTimeStamp());
			preStmt.setString(4, user.getPassword());
			preStmt.setString(5, user.getEmail());
			preStmt.setString(6, user.getPhone());
			preStmt.setString(7, user.getUserType());
			preStmt.setLong(8, user.getUserID());
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("OwnerLogin with the ID="
						+ user.getUserID() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return user;
	}

	public List<UserVO> deleteUser(int id) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<UserVO> users = new ArrayList<UserVO>();

		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM users WHERE user_id=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, id);
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("user with the ID=" + id
						+ " not found in the system.");
			}
			rs = conn.prepareStatement("SELECT * FROM users",
					PreparedStatement.RETURN_GENERATED_KEYS).executeQuery();
			while (rs.next()) {
				UserVO user = new UserVO();
				user.setUserID(rs.getInt("user_id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPhone(rs.getString("PHONE"));
				user.setPassword(rs.getString("password"));
				user.setTimeStamp(rs.getTimestamp("time_stamp"));
				user.setUserType(rs.getString("user_type"));
				;
				users.add(user);
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error:" + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return users;
	}
}
