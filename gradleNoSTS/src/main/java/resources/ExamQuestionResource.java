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

import core.ExamQuestion;
import databaseDAO.ExamDAO;

@Path("/examQuestions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ExamQuestionResource {
	private ExamDAO examDAO;

	public ExamQuestionResource(ExamDAO dao) {

		examDAO = dao;
	}

	@GET
	@Path("/{id}")
	@Timed
	public ExamQuestion getExamQuestion(@PathParam("id") Integer id) {
		ExamQuestion eq = examDAO.findExamQuestionById(id);
		if (eq != null) {
			return eq;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Timed
	public List<ExamQuestion> listExamQuestions() {
		return examDAO.getAllExamQuestions();
	}

	@POST
	@Timed
	public void saveExamQuestion(ExamQuestion examQuestion) {
		if (examQuestion != null) {
			examDAO.insertExamQuestion(examQuestion);
			throw new WebApplicationException(Response.Status.OK);

		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@PUT
	@Path("/{id}")
	public void updateExamQuestion(@PathParam("id") int id, ExamQuestion examQuestion) {
		if (examQuestion != null) {
			examDAO.updateExamQuestion(examQuestion, id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public void deleteExamQuestion(@PathParam("id") Integer id) {
		if (examDAO.findExamQuestionById(id) != null) {
			examDAO.deleteExamQuestionById(id);
			throw new WebApplicationException(Response.Status.OK);

		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

}
