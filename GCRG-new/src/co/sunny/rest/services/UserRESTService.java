package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.UserDAO;
import co.sunny.entities.UserVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/users")
public class UserRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllUsers() {

		UserDAO dao = new UserDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<UserVO> list = dao.getAllUsers();
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
	public EateryResponse addUser(UserVO user) {

		UserDAO dao = new UserDAO();
		EateryResponse resp = new EateryResponse();

		try {
			user = dao.addUser(user);
			resp.setStatus("SUCCESS");
			resp.setData(user);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getUser(@PathParam("id") int id) {

		UserDAO dao = new UserDAO();
		EateryResponse resp = new EateryResponse();

		try {
			UserVO user = dao.getUser(id);
			resp.setStatus("SUCCESS");
			resp.setData(user);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deleteUser(@PathParam("id") int id) {

		UserDAO dao = new UserDAO();
		EateryResponse resp = new EateryResponse();
		List<UserVO> users = null;
		try {
			users = dao.deleteUser(id);
			resp.setStatus("SUCCESS");
			resp.setData(users);
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
	public EateryResponse updateUser(UserVO user) {

		UserDAO dao = new UserDAO();
		EateryResponse resp = new EateryResponse();
		try {
			user = dao.updateUser(user);
			resp.setStatus("SUCCESS");
			resp.setData(user);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}

}
