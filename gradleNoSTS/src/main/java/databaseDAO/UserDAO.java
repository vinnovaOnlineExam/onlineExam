package databaseDAO;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapperFactory;
import org.skife.jdbi.v2.tweak.BeanMapperFactory;

import core.User;

@RegisterMapperFactory(BeanMapperFactory.class)
public interface UserDAO {
	@SqlUpdate("create table if not exists USERS (id int auto_increment, name varchar(80), email varchar(80) primary key, password varchar(20), score int(11) )")
	void createUserTable();

	@SqlUpdate("insert into USERS (name, email, password, score) values (:name, :email, :password, :score)")
	void insertUser(@BindBean User userSay);

	@SqlUpdate("update USERS set name = :u.name, email = :u.email, password = :u.password, score = :u.score where id = :id")
	void updateUser(@BindBean("u") User userSay, @Bind("id") int id);

	@SqlQuery("select * from USERS where id = :id")
	User findUserById(@Bind("id") int id);

	@SqlQuery("select * from USERS where email = :it")
	User findUserByEmail(@Bind String email);

	@SqlQuery("select password from USERS where email = :it")
	User findPasswordByEmail(@Bind String email);

	@SqlQuery("select * from USERS")
	List<User> getAllUsers();

	@SqlUpdate("delete from USERS where id = :it")
	void deleteUserById(@Bind int id);

	@SqlUpdate("delete from USERS where email = :it")
	void deleteUserByEmail(@Bind String email);

}
