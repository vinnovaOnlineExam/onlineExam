import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class AppConfiguration extends Configuration{
	
	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();
	
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory(){
		return dataSourceFactory;
	}
	/*@NotEmpty
	private String appName;
	
	@JsonProperty
	public String getAppName(){
		return appName;
	}
	
	@JsonProperty
	public void setAppName(final String appName){
		this.appName = appName;
	}*/
	
	
}