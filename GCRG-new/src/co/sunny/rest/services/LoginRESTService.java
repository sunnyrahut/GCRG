package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.UserDAO;
import co.sunny.entities.UserVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;

@Path("/login")
public class LoginRESTService {
	@POST
	@Path("/authenticate")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse authenticate(UserVO user) {
		UserDAO dao = new UserDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			List<UserVO> list = dao.getAllUsers();
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getEmail().equals(user.getEmail())
						&& list.get(i).getPassword().equals(user.getPassword())) {
					resp.setStatus("SUCCESS");
					resp.setData(list);
					return resp;
				}
			}
			throw new GCRGException("User credentials do not match");
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
