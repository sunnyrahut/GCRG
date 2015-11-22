package co.sunny.rest.services;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.AtqasukDAO;
import co.sunny.entities.CSVData;
import co.sunny.entities.GapFilledDataVO;
import co.sunny.entities.GenerateCSVVO;
import co.sunny.entities.MeteorologicalDataVO;
import co.sunny.entities.NoGapFilledDataVO;
import co.sunny.exception.GCRGException;
import co.sunny.utils.GCRGResponse;
import co.sunny.utils.GenerateCSVFile;

@Path("/atq")
public class AtqasukRESTService {

	@GET
	@Path("/getAllNoGap/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllNoGapData(@PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {
		fromDate = fromDate.replaceAll("-", "/");
		toDate = toDate.replaceAll("-", "/");
		AtqasukDAO dao = new AtqasukDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			List<NoGapFilledDataVO> list = dao
					.getAllNoGapData(fromDate, toDate);
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/getAllGap/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllGapData(@PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {
		fromDate = fromDate.replaceAll("-", "/");
		toDate = toDate.replaceAll("-", "/");
		AtqasukDAO dao = new AtqasukDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			List<GapFilledDataVO> list = dao.getAllGapData(fromDate, toDate);
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/getAllMeteorological/{fromDate}/{toDate}")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getAllMeteorologicalData(
			@PathParam("fromDate") String fromDate,
			@PathParam("toDate") String toDate) {
		fromDate = fromDate.replaceAll("-", "/");
		toDate = toDate.replaceAll("-", "/");
		AtqasukDAO dao = new AtqasukDAO();
		GCRGResponse resp = new GCRGResponse();
		try {
			List<MeteorologicalDataVO> list = dao.getAllMeteoData(fromDate,
					toDate);
			resp.setStatus("SUCCESS");
			resp.setData(list);
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/generateCsv")
	@Produces(MediaType.APPLICATION_JSON)
	public GCRGResponse getCsv(GenerateCSVVO generateCSV) {

		AtqasukDAO dao = new AtqasukDAO();
		GCRGResponse resp = new GCRGResponse();

		try {
			generateCSV.setTimeStampFrom(generateCSV.getTimeStampFrom()
					.replaceAll("-", "/"));
			generateCSV.setTimeStampTo(generateCSV.getTimeStampTo().replaceAll(
					"-", "/"));
			List<?> list = dao.getData(generateCSV);
			// GenerateCSVFile.generate(list, generateCSV);
			resp.setData(list);
			resp.setStatus("SUCCESS");
		} catch (GCRGException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}
}