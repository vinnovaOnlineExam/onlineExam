package db;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import sayingpack.UserSay;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface MyDAO {
	@SqlUpdate("create table if not exists USERS (id int auto_increment primary key, name varchar(80), email varchar(80), password varchar(20))")
	void createUserTable();

	@SqlUpdate("insert into USERS (name, email, password) values (:name, :email, :password)")
	void insert(@BindBean UserSay userSay);

	@SqlUpdate("update USERS set name = :u.name, email = :u.email, password = :u.password where id = :id")
	void update(@BindBean("u") UserSay userSay, @Bind("id") int id);

	@SqlQuery("select * from USERS where id = :id")
	UserSay findById(@Bind("id") int id);

	@SqlQuery("select * from USERS")
	List<UserSay> getAll();

	@SqlUpdate("delete from USERS where id = :it")
	void deleteById(@Bind int id);

	@SqlUpdate("delete from USERS where email = :it")
	void deleteByEmail(@Bind String email);
	
	@SqlUpdate("create table if not exists QUESTIONS (id int auto_increment primary key, question varchar(255), topic varchar(80), opa varchar(80), opb varchar(80), opc varchar(80), corr_op varchar(80))")
	void createQuestTable();
	
	@SqlUpdate("insert into QUESTIONS (question, topic, opa, opb, opc, corr_op) values (:question, :topic, :opa, :opb, :opc, :corr_op)")
	void insertQues(@BindBean UserSay userSay);
	
	@SqlUpdate("update QUESTIONS set question = :u.question, topic = :u.topic, opa = :u.opa, opb = :u.opb, opc = :u.opc, corr_op = :u.corr_op where id = :id")
	void updateQues(@BindBean("u") UserSay userSay, @Bind("id") int id);
	
	@SqlQuery("select * from QUESTIONS where id = :id")
	UserSay findQuesById(@Bind("id") int id);
	
	
}
