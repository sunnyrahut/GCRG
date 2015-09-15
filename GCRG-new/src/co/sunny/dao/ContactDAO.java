package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.ContactVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.DBConnector;

public class ContactDAO {

	public List<ContactVO> getAllContacts() throws GCRGException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<ContactVO> contacts = new ArrayList<ContactVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM contacts");
			rs = ps.executeQuery();

			while (rs.next()) {
				ContactVO contact = new ContactVO();
				contact.setUserID(rs.getInt("user_id"));
				contact.setName(rs.getString("name"));
				contact.setRole(rs.getString("role"));
				contact.setEmail(rs.getString("email"));
				contact.setPhone(rs.getString("phone"));
				contacts.add(contact);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return contacts;
	}

	public ContactVO addContact(ContactVO contact) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO contacts (user_id, name, role, phone, email) VALUES (?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, contact.getUserID());
			preStmt.setString(2, contact.getName());
			preStmt.setString(3, contact.getRole());
			preStmt.setString(4, contact.getPhone());
			preStmt.setString(5, contact.getEmail());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return contact;
	}

	public ContactVO updateContact(ContactVO contact) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"UPDATE contacts SET email=?, phone=?, role=?, name=? WHERE user_id=?",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, contact.getEmail());
			preStmt.setString(2, contact.getPhone());
			preStmt.setString(3, contact.getRole());
			preStmt.setString(4, contact.getName());
			preStmt.setInt(5, contact.getUserID());
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("contact with the ID="
						+ contact.getUserID() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return contact;
	}

	public List<ContactVO> deleteContact(int id) throws GCRGException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<ContactVO> contacts = new ArrayList<ContactVO>();
		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM contacts WHERE user_id=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, id);
			if (preStmt.executeUpdate() <= 0) {
				throw new GCRGException("contact with the ID=" + id
						+ " not found in the system.");
			}

			rs = conn.prepareStatement("SELECT * FROM contacts").executeQuery();

			while (rs.next()) {
				ContactVO contact = new ContactVO();
				contact.setUserID(rs.getInt("user_id"));
				contact.setEmail(rs.getString("email"));
				contact.setName(rs.getString("name"));
				contact.setPhone(rs.getString("phone"));
				contact.setRole(rs.getString("role"));
				contacts.add(contact);
			}

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new GCRGException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return contacts;
	}
}
