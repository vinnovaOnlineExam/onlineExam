package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;

import db.MyDAO;
import sayingpack.UserSay;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserMgmnt {
	private MyDAO myDAO;

	public UserMgmnt(MyDAO dao) {
		myDAO = dao;
	}

	@GET
	@Path("/{id}")
	@Timed
	public UserSay getUser(@PathParam("id") Integer id) {
		UserSay u = myDAO.findById(id);
		if (u != null) {
			return u;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Timed
	public List<UserSay> listUsers() {
		return myDAO.getAll();
	}

	@POST
	@Timed
	public void save(UserSay userSay) {
		if (userSay != null) {
			myDAO.insert(userSay);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

	}

	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") int id, UserSay userSay) {
		if (userSay != null) {
			myDAO.update(userSay, id);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public void deleteUser(@PathParam("id") Integer id) {

		if (myDAO.findById(id) != null) {
			myDAO.deleteById(id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
