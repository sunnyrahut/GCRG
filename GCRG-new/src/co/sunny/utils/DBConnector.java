package co.sunny.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import co.sunny.exception.EateryException;

public class DBConnector {
	private final static String USERNAME = "root";
	private final static String PASSWORD = "GCRG";
	private final static String DBURL = "jdbc:mysql://localhost:3306/eatery";

	public static Connection getDBConnection() throws EateryException {

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			conn = DriverManager.getConnection(DBURL, USERNAME, PASSWORD);
			System.out.println("Connected to the database");
		} catch (SQLException e) {
			System.err.println("Connection Error" + e.getMessage());
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		}

		return conn;
	}

	public static void closeResources(PreparedStatement ps, ResultSet rs,
			Connection conn) {

		try {
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (conn != null) {
				conn.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
	}

}
