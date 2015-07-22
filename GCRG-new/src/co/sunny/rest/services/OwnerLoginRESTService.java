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
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/ownerLogin")
public class OwnerLoginRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllPeople() {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<OwnerLoginVO> list = dao.getAllPeople();
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
	public EateryResponse addOwnerLogin(OwnerLoginVO ownerLogin) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		EateryResponse resp = new EateryResponse();

		try {
			ownerLogin = dao.addOwnerLogin(ownerLogin);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getOwnerLogin(@PathParam("id") String id) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		EateryResponse resp = new EateryResponse();

		try {
			OwnerLoginVO ownerLogin = dao.getOwnerLogin(id);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deleteOwnerLogin(@PathParam("id") String id) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		EateryResponse resp = new EateryResponse();
		List<OwnerLoginVO> people = null;
		try {
			people = dao.deleteOwnerLogin(id);
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
	public EateryResponse updateOwnerLogin(OwnerLoginVO ownerLogin) {

		OwnerLoginDAO dao = new OwnerLoginDAO();
		EateryResponse resp = new EateryResponse();
		try {
			ownerLogin = dao.updateOwnerLogin(ownerLogin);
			resp.setStatus("SUCCESS");
			resp.setData(ownerLogin);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
