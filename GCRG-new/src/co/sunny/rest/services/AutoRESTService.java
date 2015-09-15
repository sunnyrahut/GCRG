package co.sunny.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.AutoDAO;
import co.sunny.entities.AutoVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;

@Path("/auto")
public class AutoRESTService {

	@GET
	@Path("/get")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAuto() {

		AutoDAO dao = new AutoDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			AutoVO Auto = dao.getAuto();
			resp.setStatus("SUCCESS");
			resp.setData(Auto);
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
	public GCRGResponse updateAuto(AutoVO auto) {

		AutoDAO dao = new AutoDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			auto = dao.updateAuto(auto);
			resp.setStatus("SUCCESS");
			resp.setData(auto);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
