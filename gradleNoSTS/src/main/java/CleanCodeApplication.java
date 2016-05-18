import java.sql.SQLException;

import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;

import db.MyDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import resources.HelloWorldResource;

public class CleanCodeApplication extends Application<AppConfiguration> {

	public static void main(final String[] args) throws Exception {
		new CleanCodeApplication().run(args);
	}

	@Override
	public void run(final AppConfiguration configuration, final Environment environment) throws SQLException {
		final DBIFactory factory = new DBIFactory();
		final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
		Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
		myH2adminGUI.start();
		
		MyDAO myDAO = jdbi.onDemand(MyDAO.class);
		myDAO.createSomethingTable();
		
		HelloWorldResource helloWorldResource = new HelloWorldResource("what whay");
		environment.jersey().register(helloWorldResource);
	}

}
