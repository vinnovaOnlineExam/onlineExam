package resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
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
	// private UserDAO userDAO;
	private QuestionDAO questionDAO;
	private Integer score;

	public ValidateQuestionResource(QuestionDAO dao, Integer score) {
		questionDAO = dao;
		this.score = score;

	}

	@POST
	@Timed
	public void validateQuestion(List<Validate> validates) {
		score = 0;
		for (Validate validate : validates) {
			String quest = validate.getQuestion();
			// System.out.println("************" + quest);
			// System.out.println("************" +
			// questionDAO.findQuestionById(Integer.parseInt("1")));
			if (validate.getOption().equals(questionDAO.findQuestionById(Integer.parseInt(quest)).getCorr_op())) {
				score = score + 1;

			}
		}
		System.out.println("ssssssssssssssssssssssss" + score);

		// return new Validate(score);

	}

	@GET
	@Timed
	public Integer sayScore() {
		return score;
	}
}
