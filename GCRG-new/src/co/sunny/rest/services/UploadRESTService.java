package co.sunny.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.utils.CSVFileReader;
import co.sunny.utils.EateryResponse;

@Path("/upload")
public class UploadRESTService {

	@POST
	@Path("/{location}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllData(@PathParam("location") String location) {

		EateryResponse resp = new EateryResponse();
		try {
			CSVFileReader csv = new CSVFileReader();
			location = location.replace('-', '/');
			csv.run(location);
			resp.setStatus("SUCCESS");
			resp.setData(location);
		} catch (Exception e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
