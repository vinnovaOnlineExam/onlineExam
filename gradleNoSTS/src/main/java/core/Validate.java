package core;

public class Validate {

	private String question;
	private String option;
	private Integer mark;

	// {"1":"opb"}
	// [{ question: 1, option: opb} ]
	public Validate() {
	}
	

	public Validate(Integer mark) {
		super();
		this.mark = mark;
	}


	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public Integer getMark() {
		return mark;
	}

	public void setMark(Integer mark) {
		this.mark = mark;
	}

}
