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
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;

@Path("/owner")
public class OwnerRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllPeople() {

		OwnerDAO dao = new OwnerDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			List<OwnerVO> list = dao.getAllPeople();
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse addOwner(OwnerVO owner) {

		OwnerDAO dao = new OwnerDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			owner = dao.addOwner(owner);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getOwner(@PathParam("id") int id) {

		OwnerDAO dao = new OwnerDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			OwnerVO owner = dao.getOwner(id);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public GCRGResponse deleteOwner(@PathParam("id") int id) {

		OwnerDAO dao = new OwnerDAO();
		GCRGResponse resp = new GCRGResponse();
		List<OwnerVO> people = null;
		try {
			people = dao.deleteOwner(id);
			resp.setStatus("SUCCESS");
			resp.setData(people);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse updateOwner(OwnerVO owner) {

		OwnerDAO dao = new OwnerDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			owner = dao.updateOwner(owner);
			resp.setStatus("SUCCESS");
			resp.setData(owner);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
