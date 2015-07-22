package co.sunny.rest.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.sunny.dao.TableDAO;
import co.sunny.entities.TableVO;
import co.sunny.exception.EateryException;
import co.sunny.utils.EateryResponse;

@Path("/table")
public class TableRESTService {

	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAllPeople() {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();

		try {
			List<TableVO> list = dao.getAllPeople();
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
	public EateryResponse addTable(TableVO table) {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();

		try {
			table = dao.addTable(table);
			resp.setStatus("SUCCESS");
			resp.setData(table);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getTable(@PathParam("id") int id) {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();

		try {
			TableVO table = dao.getTable(id);
			resp.setStatus("SUCCESS");
			resp.setData(table);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@GET
	@Path("/getAvailable")
	@Produces(MediaType.APPLICATION_JSON)
	public EateryResponse getAvailableTable() {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();

		try {
			TableVO table = dao.getAvailableTable();
			resp.setStatus("SUCCESS");
			resp.setData(table);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}

		return resp;
	}

	@POST
	@Path("/delete/{id}")
	public EateryResponse deleteTable(@PathParam("id") int id) {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();
		List<TableVO> tables = null;
		try {
			tables = dao.deleteTable(id);
			resp.setStatus("SUCCESS");
			resp.setData(tables);
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
	public EateryResponse updateTable(TableVO table) {

		TableDAO dao = new TableDAO();
		EateryResponse resp = new EateryResponse();
		try {
			table = dao.updateTable(table);
			resp.setStatus("SUCCESS");
			resp.setData(table);
		} catch (EateryException e) {
			resp.setStatus("ERROR");
			resp.setMsg(e.getMessage());
		}
		return resp;
	}
}
