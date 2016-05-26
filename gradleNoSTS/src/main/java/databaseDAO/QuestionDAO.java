package databaseDAO;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import core.Question;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface QuestionDAO {
	@SqlUpdate("create table if not exists QUESTIONS (id int auto_increment primary key, question varchar(255), topic varchar(80), opa varchar(80), opb varchar(80), opc varchar(80), corr_op varchar(80))")
	void createQuestionTable();

	@SqlUpdate("insert into QUESTIONS (question, topic, opa, opb, opc, corr_op) values (:question, :topic, :opa, :opb, :opc, :corr_op)")
	void insertQuestion(@BindBean Question questionSay);

	@SqlUpdate("update QUESTIONS set question = :u.question, topic = :u.topic, opa = :u.opa, opb = :u.opb, opc = :u.opc, corr_op = :u.corr_op where id = :id")
	void updateQuestion(@BindBean("u") Question questionSay, @Bind("id") int id);

	@SqlQuery("select * from QUESTIONS where id = :id")
	Question findQuestionById(@Bind("id") int id);

	@SqlQuery("select * from QUESTIONS where question = :question")
	Question findQuestionByName(@Bind("question") String question);

	@SqlQuery("select * from QUESTIONS")
	List<Question> getAllQuestions();

	@SqlUpdate("delete from QUESTIONS where id = :it")
	void deleteQuestionById(@Bind int id);

	@SqlUpdate("delete from QUESTIONS where question = :it")
	void deleteQuestionByName(@Bind String question);
}
