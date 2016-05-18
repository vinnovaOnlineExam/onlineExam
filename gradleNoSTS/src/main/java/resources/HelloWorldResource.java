package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {

	private String what;

    public HelloWorldResource(String what) {
		this.what = what;

    }

    @GET
    @Timed
    public String sayHello() {
        return "sadsad" + what;
    }
}
