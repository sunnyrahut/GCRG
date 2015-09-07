package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.ContactDAO;
import co.sunny.entities.ContactVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/contacts")
public class ContactRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllContacts() {

		ContactDAO dao = new ContactDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<ContactVO> list = dao.getAllContacts();
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse addContact(ContactVO contact) {

		ContactDAO dao = new ContactDAO();
		EateryResponse resp = new EateryResponse();

		try {
			contact = dao.addContact(contact);
			resp.setStatus("SUCCESS");
			resp.setData(contact);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deleteContact(@PathParam("id") int id) {

		ContactDAO dao = new ContactDAO();
		EateryResponse resp = new EateryResponse();
		List<ContactVO> contacts = null;
		try {
			contacts = dao.deleteContact(id);
			resp.setStatus("SUCCESS");
			resp.setData(contacts);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse updateContact(ContactVO contact) {

		ContactDAO dao = new ContactDAO();
		EateryResponse resp = new EateryResponse();
		try {
			contact = dao.updateContact(contact);
			resp.setStatus("SUCCESS");
			resp.setData(contact);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
