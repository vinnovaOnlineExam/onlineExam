package db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import sayingpack.QuestSay;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface QuesDAO {
	@SqlUpdate("create table if not exists QUESTIONS (id int auto_increment primary key, question varchar(255), topic varchar(80), opa varchar(80), opb varchar(80), opc varchar(80), corr_op varchar(80))")
	void createQuestTable();

	@SqlUpdate("insert into QUESTIONS (question, topic, opa, opb, opc, corr_op) values (:question, :topic, :opa, :opb, :opc, :corr_op)")
	void insertQues(@BindBean QuestSay questSay);

	@SqlUpdate("update QUESTIONS set question = :u.question, topic = :u.topic, opa = :u.opa, opb = :u.opb, opc = :u.opc, corr_op = :u.corr_op where id = :id")
	void updateQues(@BindBean("u") QuestSay questSay, @Bind("id") int id);

	@SqlQuery("select * from QUESTIONS where id = :id")
	QuestSay findQuesById(@Bind("id") int id);

	@SqlQuery("select * from QUESTIONS where question = :question")
	QuestSay findQuesByStr(@Bind("question") String question);

	@SqlQuery("select * from QUESTIONS")
	List<QuestSay> getAllQues();

	@SqlUpdate("delete from QUESTIONS where id = :it")
	void deleteQuesById(@Bind int id);

	@SqlUpdate("delete from QUESTIONS where question = :it")
	void deleteQuesByStr(@Bind String question);
}
