package co.sunny.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.sunny.entities.PersonVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.DBConnector;

public class PersonDAO {

	public List<PersonVO> getAllPeople() throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		List<PersonVO> people = new ArrayList<PersonVO>();

		try {
			ps = con.prepareStatement("SELECT * FROM people");
			rs = ps.executeQuery();

			while (rs.next()) {
				PersonVO person = new PersonVO();
				person.setId(rs.getInt("BOOKING_ID"));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setBookingDate(rs.getString("BOOKING_DATE"));
				person.setBookingTime(rs.getString("BOOKING_TIME"));
				person.setEmail(rs.getString("EMAIL"));
				person.setPhone(rs.getString("PHONE"));
				person.setPartySize(rs.getInt("PARTY_SIZE"));
				person.setOccasion(rs.getString("OCCASION"));
				people.add(person);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return people;
	}

	public PersonVO getPerson(int id) throws EateryException {

		Connection con = DBConnector.getDBConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;

		PersonVO person = new PersonVO();

		try {
			ps = con.prepareStatement("SELECT * FROM people WHERE BOOKING_ID=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();

			if (rs.next()) {
				person.setId(rs.getInt("BOOKING_ID"));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setBookingDate(rs.getString("BOOKING_DATE"));
				person.setBookingTime(rs.getString("BOOKING_TIME"));
				person.setEmail(rs.getString("EMAIL"));
				person.setPhone(rs.getString("PHONE"));
				person.setPartySize(rs.getInt("PARTY_SIZE"));
				person.setOccasion(rs.getString("OCCASION"));
			} else {
				throw new EateryException("Person with the ID=" + id
						+ " not found in the system.");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(ps, rs, con);
		}

		return person;
	}

	public PersonVO addPerson(PersonVO person) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"INSERT INTO people (FIRST_NAME, LAST_NAME, EMAIL, BOOKING_DATE, BOOKING_TIME, PHONE, PARTY_SIZE, OCCASION) VALUES (?,?,?,?,?,?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, person.getFirstName());
			preStmt.setString(2, person.getLastName());
			preStmt.setString(3, person.getEmail());
			preStmt.setString(4, person.getBookingDate());
			preStmt.setString(5, person.getBookingTime());
			preStmt.setString(6, person.getPhone());
			preStmt.setInt(7, person.getPartySize());
			preStmt.setString(8, person.getOccasion());

			preStmt.executeUpdate();
			rs = preStmt.getGeneratedKeys();

			if (rs.next()) {
				person.setId(rs.getInt(1));
			}

		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}

		return person;
	}

	public PersonVO updatePerson(PersonVO person) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		try {
			preStmt = conn
					.prepareStatement(
							"UPDATE people SET FIRST_NAME=?, LAST_NAME=?, EMAIL=?, BOOKING_DATE=?, BOOKING_TIME=?, PHONE=?, PARTY_SIZE=?, OCCASION=? WHERE BOOKING_ID=?",
							PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setString(1, person.getFirstName());
			preStmt.setString(2, person.getLastName());
			preStmt.setString(3, person.getEmail());
			preStmt.setString(4, person.getBookingDate());
			preStmt.setString(5, person.getBookingTime());
			preStmt.setString(6, person.getPhone());
			preStmt.setInt(7, person.getPartySize());
			preStmt.setString(8, person.getOccasion());
			preStmt.setInt(9, person.getId());
			if (preStmt.executeUpdate() <= 0) {
				throw new EateryException("OwnerLogin with the ID="
						+ person.getId() + " not found in the system.");
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error: " + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return person;
	}

	public List<PersonVO> deletePerson(int id) throws EateryException {

		Connection conn = DBConnector.getDBConnection();
		PreparedStatement preStmt = null;
		ResultSet rs = null;
		List<PersonVO> people = new ArrayList<PersonVO>();

		try {
			preStmt = conn.prepareStatement(
					"DELETE FROM people WHERE BOOKING_ID=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			preStmt.setInt(1, id);
			if (preStmt.executeUpdate() <= 0) {
				throw new EateryException("Person with the ID=" + id
						+ " not found in the system.");
			}
			rs = conn.prepareStatement("SELECT * FROM people",
					PreparedStatement.RETURN_GENERATED_KEYS).executeQuery();
			while (rs.next()) {
				PersonVO person = new PersonVO();
				person.setId(rs.getInt("BOOKING_ID"));
				person.setFirstName(rs.getString("FIRST_NAME"));
				person.setLastName(rs.getString("LAST_NAME"));
				person.setBookingDate(rs.getString("BOOKING_DATE"));
				person.setBookingTime(rs.getString("BOOKING_TIME"));
				person.setEmail(rs.getString("EMAIL"));
				person.setPhone(rs.getString("PHONE"));
				person.setPartySize(rs.getInt("PARTY_SIZE"));
				person.setOccasion(rs.getString("OCCASION"));
				people.add(person);
			}
		} catch (SQLException e) {
			System.err.println("Error " + e.getMessage());
			e.getStackTrace();
			throw new EateryException("Error:" + e.getMessage(), e.getCause());
		} finally {
			DBConnector.closeResources(preStmt, rs, conn);
		}
		return people;
	}
}
