import java.sql.SQLException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration.Dynamic;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.h2.tools.Server;
import org.skife.jdbi.v2.DBI;

import com.google.common.base.Joiner;

import databaseDAO.ExamDAO;
import databaseDAO.QuestionDAO;
import databaseDAO.UserDAO;
import io.dropwizard.Application;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import resources.ExamQuestionResource;
import resources.HelloWorldResource;
import resources.QuestionResource;
import resources.UserResource;

public class CleanCodeApplication extends Application<ApplicationConfiguration> {

	private static final String API_URL_PATTERN = "/api/*";

	public static void main(final String[] args) throws Exception {
		new CleanCodeApplication().run(args);
	}

	@Override
	public void run(final ApplicationConfiguration configuration, final Environment environment) throws SQLException {

		configureCrossOriginFilter(configuration, environment);

		HelloWorldResource helloWorldResource = new HelloWorldResource(configuration.getTemplate(),
				configuration.getDefaultName());
		environment.jersey().register(helloWorldResource);

		DBIFactory factory = new DBIFactory();
		DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "h2");
		Server myH2adminGUI = org.h2.tools.Server.createWebServer("-webDaemon");
		myH2adminGUI.start();

		UserDAO userDAO = jdbi.onDemand(UserDAO.class);
		userDAO.createUserTable();

		QuestionDAO questionDAO = jdbi.onDemand(QuestionDAO.class);
		questionDAO.createQuestionTable();

		ExamDAO examDAO = jdbi.onDemand(ExamDAO.class);
		examDAO.createExamsTable();

		// environment.addResource(new UserMgmnt(myDAO));
		UserResource userResource = new UserResource(userDAO);
		environment.jersey().register(userResource);

		QuestionResource questionResource = new QuestionResource(questionDAO);
		environment.jersey().register(questionResource);

		ExamQuestionResource examQuestionResource = new ExamQuestionResource(examDAO);
		environment.jersey().register(examQuestionResource);

	}

	private void configureCrossOriginFilter(ApplicationConfiguration configuration, Environment environment) {
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
