package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

import core.Validate;
import databaseDAO.QuestionDAO;

@Path("/Qvalidate")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ValidateQuestionResource {
	private QuestionDAO questionDAO;

	public ValidateQuestionResource(QuestionDAO dao) {
		questionDAO = dao;
	}

	@POST
	@Timed
	public void validateQuestion(List<Validate> validates) {
		
		// for (Validate validate : validates) {
		// validate.getQuestion();
		// }
	}

}
