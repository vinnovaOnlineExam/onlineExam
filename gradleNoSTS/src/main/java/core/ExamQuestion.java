package core;

public class ExamQuestion {
	private Integer id;
	private String examQuestion;
	private String examTopic;
	private String examOpa;
	private String examOpb;
	private String examOpc;
	private String examCorr_op;

	public ExamQuestion() {
		super();
	}

	public ExamQuestion(Integer id, String examQuestion) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
	}

	public ExamQuestion(Integer id, String examQuestion, String examTopic) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
		this.examTopic = examTopic;
	}

	public ExamQuestion(Integer id, String examQuestion, String examTopic, String examOpa) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
		this.examTopic = examTopic;
		this.examOpa = examOpa;
	}

	public ExamQuestion(Integer id, String examQuestion, String examTopic, String examOpa, String examOpb) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
		this.examTopic = examTopic;
		this.examOpa = examOpa;
		this.examOpb = examOpb;
	}

	public ExamQuestion(Integer id, String examQuestion, String examTopic, String examOpa, String examOpb,
			String examOpc) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
		this.examTopic = examTopic;
		this.examOpa = examOpa;
		this.examOpb = examOpb;
		this.examOpc = examOpc;
	}

	public ExamQuestion(Integer id, String examQuestion, String examTopic, String examOpa, String examOpb,
			String examOpc, String examCorr_op) {
		super();
		this.id = id;
		this.examQuestion = examQuestion;
		this.examTopic = examTopic;
		this.examOpa = examOpa;
		this.examOpb = examOpb;
		this.examOpc = examOpc;
		this.examCorr_op = examCorr_op;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getExamQuestion() {
		return examQuestion;
	}

	public void setExamQuestion(String examQuestion) {
		this.examQuestion = examQuestion;
	}

	public String getExamTopic() {
		return examTopic;
	}

	public void setExamTopic(String examTopic) {
		this.examTopic = examTopic;
	}

	public String getExamOpa() {
		return examOpa;
	}

	public void setExamOpa(String examOpa) {
		this.examOpa = examOpa;
	}

	public String getExamOpb() {
		return examOpb;
	}

	public void setExamOpb(String examOpb) {
		this.examOpb = examOpb;
	}

	public String getExamOpc() {
		return examOpc;
	}

	public void setExamOpc(String examOpc) {
		this.examOpc = examOpc;
	}

	public String getExamCorr_op() {
		return examCorr_op;
	}

	public void setExamCorr_op(String examCorr_op) {
		this.examCorr_op = examCorr_op;
	}

}
