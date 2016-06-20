package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import core.ValidateU;
import databaseDAO.UserDAO;

@Path("/Uvalidate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValidateUserResource {

	private UserDAO userDAO;
	private Integer user;

	public ValidateUserResource(UserDAO dao, Integer user) {
		super();
		this.userDAO = dao;
		this.user = user;
	}

	@POST
	@Timed
	public void validateUser(List<ValidateU> validates) {
		user = 0;
		for (ValidateU validate : validates) {
			String mail = validate.getEmail();
			String pass = validate.getPassword();
			System.out.println("************" + mail + "-----------" + pass);
			if (validate.getEmail().equals(userDAO.findPasswordByEmail(mail))) {
				user = 1;
			}
			System.out.println("ssssssssssssssssssssssss" + user);

		}
	}

	@GET
	@Timed
	public Integer sayUser() {
		return user;
	}
}
