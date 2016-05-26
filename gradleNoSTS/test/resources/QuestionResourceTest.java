package resources;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.google.common.collect.Lists;

import core.Question;
import databaseDAO.QuestionDAO;

public class QuestionResourceTest {

	@Test
	public void testListQues() {
		QuestionDAO dao = Mockito.mock(QuestionDAO.class);
		Question questSay = new Question(1, "Gandam style");
		Question questSay2 = new Question(2, "Gandam Style222222");
		Mockito.when(dao.getAllQuestions()).thenReturn(Lists.newArrayList(questSay, questSay2));
		
		QuestionResource questionResource = new QuestionResource(dao);
		
		List<Question> listQues = questionResource.listQuestions();
		
		Assert.assertEquals(2, listQues.size());
		//Gandam style!
		Assert.assertEquals("Gandam style", listQues.get(0).getQuestion());
		
		
	}
}
