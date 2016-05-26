
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

public class ApplicationConfiguration extends Configuration {

	@NotEmpty
	private String template;

	@NotEmpty
	private String defaultName = "Stranger";

	private String[] allowedOrigins;

	@JsonProperty
	public String getTemplate() {
		return template;
	}

	@JsonProperty
	public void setTemplate(String template) {
		this.template = template;
	}

	@JsonProperty
	public String getDefaultName() {
		return defaultName;
	}

	@JsonProperty
	public void setDefaultName(String name) {
		this.defaultName = name;
	}

	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}

	@JsonProperty
	public String[] getAllowedOrigins() {
		return allowedOrigins;
	}

	@JsonProperty
	public void setAllowedOrigins(String[] allowedOrigins) {
		this.allowedOrigins = allowedOrigins;
	}
	/*
	 * @NotEmpty private String appName;
	 * 
	 * @JsonProperty public String getAppName(){ return appName; }
	 * 
	 * @JsonProperty public void setAppName(final String appName){ this.appName
	 * = appName; }
	 */

}