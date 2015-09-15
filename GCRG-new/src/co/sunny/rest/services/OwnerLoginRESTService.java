package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.OwnerLoginDAO;
import co.sunny.entities.OwnerLoginVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;

@Path("/ownerLogin")
public class OwnerLoginRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllPeople() {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			List<OwnerLoginVO> list = dao.getAllPeople();
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
	public GCRGResponse addOwnerLogin(OwnerLoginVO ownerLogin) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			ownerLogin = dao.addOwnerLogin(ownerLogin);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getOwnerLogin(@PathParam("id") String id) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			OwnerLoginVO ownerLogin = dao.getOwnerLogin(id);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public GCRGResponse deleteOwnerLogin(@PathParam("id") String id) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		GCRGResponse resp = new GCRGResponse();
		List<OwnerLoginVO> people = null;
		try {
			people = dao.deleteOwnerLogin(id);
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
	public GCRGResponse updateOwnerLogin(OwnerLoginVO ownerLogin) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			ownerLogin = dao.updateOwnerLogin(ownerLogin);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
