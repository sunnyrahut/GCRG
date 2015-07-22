package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.PersonDAO;
import co.sunny.entities.PersonVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/people")
public class PeopleRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllPeople() {

		PersonDAO dao = new PersonDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<PersonVO> list = dao.getAllPeople();
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
	public EateryResponse addPerson(PersonVO person) {

		PersonDAO dao = new PersonDAO();
		EateryResponse resp = new EateryResponse();

		try {
			person = dao.addPerson(person);
			resp.setStatus("SUCCESS");
			resp.setData(person);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getPerson(@PathParam("id") int id) {

		PersonDAO dao = new PersonDAO();
		EateryResponse resp = new EateryResponse();

		try {
			PersonVO person = dao.getPerson(id);
			resp.setStatus("SUCCESS");
			resp.setData(person);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deletePerson(@PathParam("id") int id) {

		PersonDAO dao = new PersonDAO();
		EateryResponse resp = new EateryResponse();
		List<PersonVO> people = null;
		try {
			people = dao.deletePerson(id);
			resp.setStatus("SUCCESS");
			resp.setData(people);
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
	public EateryResponse updatePerson(PersonVO person) {

		PersonDAO dao = new PersonDAO();
		EateryResponse resp = new EateryResponse();
		try {
			person = dao.updatePerson(person);
			resp.setStatus("SUCCESS");
			resp.setData(person);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

}
