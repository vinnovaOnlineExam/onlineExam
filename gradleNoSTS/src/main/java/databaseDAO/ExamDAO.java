package databaseDAO;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import core.ExamQuestion;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface ExamDAO {
	@SqlUpdate("create table if not exists EXAMS (id int auto_increment primary key, examQuestion varchar(255), examTopic varchar(80), examOpa varchar(80), examOpb varchar(80), examOpc varchar(80), examCorr_op varchar(80))")
	void createExamsTable();

	@SqlUpdate("insert into EXAMS (examQuestion, examTopic, examOpa, examOpb, examOpc, examCorr_op) values (:examQuestion, :examTopic, :examOpa, :examOpb, :examOpc, :examCorr_op)")
	void insertExamQuestion(@BindBean ExamQuestion examQuestion);

	@SqlUpdate("update EXAMS set examQuestion = :u.examQuestion, examTopic = :u.examTopic, examOpa = :u.examOpa, examOpb = :u.examOpb, examOpc = :u.examOpc, examCorr_op = :u.examCorr_op where id = :id")
	void updateExamQuestion(@BindBean("u") ExamQuestion examQuestion, @Bind("id") int id);

	@SqlQuery("select * from EXAMS where id = :id")
	ExamQuestion findExamQuestionById(@Bind("id") int id);

	@SqlQuery("select * from EXAMS where examQuestion = :examQuestion")
	ExamQuestion findExamQuestionByName(@Bind("examQuestion") String examQuestion);

	@SqlQuery("select * from EXAMS")
	List<ExamQuestion> getAllExamQuestions();

	@SqlUpdate("delete from EXAMS where id = :it")
	void deleteExamQuestionById(@Bind int id);

	@SqlUpdate("delete from EXAMS where examQuestion = :it")
	void deleteExamQuestionByName(@Bind String examQuestion);
}
