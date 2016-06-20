package core;

public class ValidateU {
	private String email;
	private String password;
	private Integer user;

	public ValidateU() {
		super();
	}

	public ValidateU(Integer user) {
		super();
		this.user = user;
	}

	public ValidateU(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getUser() {
		return user;
	}

	public void setUser(Integer user) {
		this.user = user;
	}

}
