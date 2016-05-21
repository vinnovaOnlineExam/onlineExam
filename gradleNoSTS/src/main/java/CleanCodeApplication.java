import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;

import com.google.common.base.Joiner;

import db.MyDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import resources.HelloWorldResource;
import resources.UserMgmnt;
import sayingpack.UserSay;

public class CleanCodeApplication extends Application<AppConfiguration> {

	private static final String API_URL_PATTERN = "/api/*";

	private static List<UserSay> users;

	static {
		users = Collections.synchronizedList(new ArrayList<UserSay>());
		users.add(new UserSay("Per", "per@jaffa.co", "12345678"));
		users.add(new UserSay("Mag", "mag@jaffa.coo"));
		users.add(new UserSay("Ron", "ron@jaffa.co"));
		users.add(new UserSay("Aug", "aug@jaffa.co"));
		users.add(new UserSay("Hel", "hel@jaffa.co"));
	}

	public static void main(final String[] args) throws Exception {
		new CleanCodeApplication().run(args);
	}

	@Override
	public void run(final AppConfiguration configuration, final Environment environment) throws SQLException {

		configureCrossOriginFilter(configuration, environment);

		HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(helloWorldResource);

		DBIFactory factory = new DBIFactory();
		DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
		Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
		myH2adminGUI.start();

		MyDAO myDAO = jdbi.onDemand(MyDAO.class);
		myDAO.createUserTable();
		seedTheDatabase(myDAO);

		// environment.addResource(new UserMgmnt(myDAO));
		UserMgmnt userMgmt = new UserMgmnt(myDAO);
		environment.jersey().register(userMgmt);

	}

	private void seedTheDatabase(MyDAO myDAO) {
		for (UserSay u : users) {
			myDAO.insert(u);
		}
	}

	private void configureCrossOriginFilter(AppConfiguration configuration, Environment environment) {
		String[] allowedOrigins = configuration.getAllowedOrigins();
		if (allowedOrigins == null || allowedOrigins.length == 0) {
			return;
		}

		Dynamic filter = environment.servlets().addFilter("CrossOriginFilter", CrossOriginFilter.class);
		filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, API_URL_PATTERN);
		filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, Joiner.on(',').join(allowedOrigins));
		filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
				"X-Requested-With,Content-Type,Accept,Accept-Language,Origin,Authorization");
		filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,PUT,DELETE");
		filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
	}

}
