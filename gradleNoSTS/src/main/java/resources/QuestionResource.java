package resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.codahale.metrics.annotation.Timed;

import core.Question;
import databaseDAO.QuestionDAO;

@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestionResource {

	private QuestionDAO questionDAO;

	public QuestionResource(QuestionDAO dao) {
		questionDAO = dao;
	}

	@GET
	@Path("/{id}")
	@Timed
	public Question getQuestion(@PathParam("id") Integer id) {
		Question q = questionDAO.findQuestionById(id);
		if (q != null) {
			return q;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	public List<Question> listQuestions() {
		return questionDAO.getAllQuestions();
	}

	@GET
	@Timed
	public List<Question> nebondaGet(@QueryParam("ids") String ids, @QueryParam("topic") String topic) {
		if (ids == null)
			return listQuestions();
		else
			return getQuestionByIdList(ids);
	}

	public List<Question> getQuestionByIdList(String ids) {
		String[] idList = ids.split(",");
		return Stream.of(idList) //
				.map(id -> questionDAO.findQuestionById(Integer.parseInt(id))) //
				.filter(q -> q != null) //
				.collect(Collectors.toList());
//		List<Question> qList = new ArrayList<Question>();
//		if (idList.length > 0) {
//			for (int i = 0; i < idList.length; i++) {
//				Question q = questionDAO.findQuestionById(Integer.parseInt(idList[i]));
//				if (q != null) {
//					qList.add(q);
//				}
//			}
//		}
//		return qList;
	}

	@POST
	@Timed
	public void saveQuestion(Question question) {
		if (question != null) {
			questionDAO.insertQuestion(question);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

	}

	@PUT
	@Path("/{id}")
	public void updateQuestion(@PathParam("id") int id, Question question) {
		if (question != null) {
			questionDAO.updateQuestion(question, id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public void deleteQuestion(@PathParam("id") Integer id) {

		if (questionDAO.findQuestionById(id) != null) {
			questionDAO.deleteQuestionById(id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	// @DELETE
	// @Path("/{question}")
	// @Timed
	// @Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML,
	// MediaType.TEXT_PLAIN })
	// public void deleteQuestStr(@PathParam("question") String question) {
	//
	// if (quesDAO.findQuesByStr(question) != null) {
	// quesDAO.deleteQuesByStr(question);
	// throw new WebApplicationException(Response.Status.OK);
	// } else {
	// throw new WebApplicationException(Response.Status.NOT_FOUND);
	// }
	// }

}
