package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.OwnerDAO;
import co.sunny.entities.OwnerVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/owner")
public class OwnerRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllPeople() {

		OwnerDAO dao = new OwnerDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<OwnerVO> list = dao.getAllPeople();
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
	public EateryResponse addOwner(OwnerVO owner) {

		OwnerDAO dao = new OwnerDAO();
		EateryResponse resp = new EateryResponse();

		try {
			owner = dao.addOwner(owner);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getOwner(@PathParam("id") int id) {

		OwnerDAO dao = new OwnerDAO();
		EateryResponse resp = new EateryResponse();

		try {
			OwnerVO owner = dao.getOwner(id);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deleteOwner(@PathParam("id") int id) {

		OwnerDAO dao = new OwnerDAO();
		EateryResponse resp = new EateryResponse();
		List<OwnerVO> people = null;
		try {
			people = dao.deleteOwner(id);
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
	public EateryResponse updateOwner(OwnerVO owner) {

		OwnerDAO dao = new OwnerDAO();
		EateryResponse resp = new EateryResponse();
		try {
			owner = dao.updateOwner(owner);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
