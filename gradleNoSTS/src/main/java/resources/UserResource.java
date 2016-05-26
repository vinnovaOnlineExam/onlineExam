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

import core.User;
import databaseDAO.UserDAO;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserDAO myDAO;

	public UserResource(UserDAO dao) {
		myDAO = dao;
	}

	@GET
	@Path("/{id}")
	@Timed
	public User getUser(@PathParam("id") Integer id) {
		User u = myDAO.findUserById(id);
		if (u != null) {
			return u;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Timed
	public List<User> listUsers() {
		return myDAO.getAllUsers();
	}

	@POST
	@Timed
	public void saveUser(User userSay) {
		if (userSay != null) {
			myDAO.insertUser(userSay);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

	}

	@PUT
	@Path("/{id}")
	public void updateUser(@PathParam("id") int id, User userSay) {
		if (userSay != null) {
			myDAO.updateUser(userSay, id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public void deleteUser(@PathParam("id") Integer id) {

		if (myDAO.findUserById(id) != null) {
			myDAO.deleteUserById(id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
