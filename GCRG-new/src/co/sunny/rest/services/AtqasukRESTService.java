package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.DataVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/atq")
public class AtqasukRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllData() {

		AtqasukDAO dao = new AtqasukDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<DataVO> list = dao.getAllData();
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}
}
