package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.NoGapDataVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;

@Path("/atq")
public class AtqasukRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllData() {

		AtqasukDAO dao = new AtqasukDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			List<NoGapDataVO> list = dao.getAllData();
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}
}
