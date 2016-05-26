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

import db.QuesDAO;
import sayingpack.QuestSay;

@Path("/questions")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuestMgmnt {
	private QuesDAO quesDAO;

	public QuestMgmnt(QuesDAO dao) {
		quesDAO = dao;
	}

	@GET
	@Path("/{id}")
	@Timed
	public QuestSay getQuestion(@PathParam("id") Integer id) {
		QuestSay q = quesDAO.findQuesById(id);
		if (q != null) {
			return q;
		} else {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
	}

	@GET
	@Timed
	public List<QuestSay> listQues() {
		return quesDAO.getAllQues();
	}

	@POST
	@Timed
	public void saveQues(QuestSay questSay) {
		if (questSay != null) {
			quesDAO.insertQues(questSay);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}

	}

	@PUT
	@Path("/{id}")
	public void update(@PathParam("id") int id, QuestSay questSay) {
		if (questSay != null) {
			quesDAO.updateQues(questSay, id);
			throw new WebApplicationException(Response.Status.OK);
		} else {
			throw new WebApplicationException(Status.BAD_REQUEST);
		}
	}

	@DELETE
	@Path("/{id}")
	@Timed
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.TEXT_PLAIN })
	public void deleteQuest(@PathParam("id") Integer id) {

		if (quesDAO.findQuesById(id) != null) {
			quesDAO.deleteQuesById(id);
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
