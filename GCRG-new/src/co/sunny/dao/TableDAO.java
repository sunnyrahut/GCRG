package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.TableVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.DBConnector;

public class TableDAO {

	public List<TableVO> getAllPeople() throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<TableVO> tables = new ArrayList<TableVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM assign_table");
			rs = ps.executeQuery();

			while (rs.next()) {
				TableVO table = new TableVO();
				table.setTableNo(rs.getInt("TABLE_NO"));
				table.setBookingId(rs.getInt("BOOKING_ID"));
				table.setTableStatus(rs.getString("TABLE_STATUS"));
				table.setSince(rs.getString("SINCE"));
				tables.add(table);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return tables;
	}

	public TableVO getTable(int no) throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		TableVO table = new TableVO();

		try {
			ps = con.prepareStatement("SELECT * FROM assign_table WHERE TABLE_NO=?");
			ps.setInt(1, no);
			rs = ps.executeQuery();

			if (rs.next()) {
				table.setTableNo(rs.getInt("TABLE_NO"));
				table.setBookingId(rs.getInt("BOOKING_ID"));
				table.setTableStatus(rs.getString("TABLE_STATUS"));
				table.setSince(rs.getString("SINCE"));
			} else {
				throw new EateryException("Table with the ID=" + no
						+ " not found in the system.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return table;
	}

	public TableVO getAvailableTable() throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		TableVO table = new TableVO();

		try {
			ps = con.prepareStatement("select * from assign_table where TABLE_STATUS=?");
			ps.setString(1, "Available");
			rs = ps.executeQuery();

			if (rs.next()) {
				table.setTableNo(rs.getInt("TABLE_NO"));
				table.setBookingId(rs.getInt("BOOKING_ID"));
				table.setTableStatus(rs.getString("TABLE_STATUS"));
				table.setSince(rs.getString("SINCE"));
			} else {
				throw new EateryException("No available tables");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return table;
	}

	public TableVO addTable(TableVO table) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO assign_table (TABLE_NO, BOOKING_ID, TABLE_STATUS,SINCE) VALUES (?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, table.getTableNo());
			preStmt.setInt(2, table.getBookingId());
			preStmt.setString(3, table.getTableStatus());
			preStmt.setString(4, table.getSince());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return table;
	}

	public TableVO updateTable(TableVO table) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"UPDATE assign_table SET BOOKING_ID=?, TABLE_STATUS=?,SINCE=? WHERE TABLE_NO=?",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, table.getBookingId());
			preStmt.setInt(4, table.getTableNo());
			preStmt.setString(2, table.getTableStatus());
			preStmt.setString(3, table.getSince());
			if (preStmt.executeUpdate() <= 0) {
				throw new EateryException("Table with the ID="
						+ table.getTableNo() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return table;
	}

	public List<TableVO> deleteTable(int no) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<TableVO> tables = new ArrayList<TableVO>();
		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM assign_table WHERE TABLE_NO=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, no);
			if (preStmt.executeUpdate() <= 0) {
				throw new EateryException("Table with the ID=" + no
						+ " not found in the system.");
			}

			rs = conn.prepareStatement("SELECT * FROM assign_table")
					.executeQuery();

			while (rs.next()) {
				TableVO table = new TableVO();
				table.setTableNo(rs.getInt("TABLE_NO"));
				table.setBookingId(rs.getInt("BOOKING_ID"));
				table.setTableStatus(rs.getString("TABLE_STATUS"));
				table.setSince(rs.getString("SINCE"));
				tables.add(table);
			}

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return tables;
	}
}
