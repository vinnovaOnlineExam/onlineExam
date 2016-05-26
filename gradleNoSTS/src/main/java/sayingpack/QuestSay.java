package sayingpack;

public class QuestSay {
	private Integer id;
	private String question;
	private String topic;
	private String opa;
	private String opb;
	private String opc;
	private String corr_op;

	public QuestSay() {
		super();
	}

	public QuestSay(Integer id, String question) {
		super();
		this.id = id;
		this.question = question;
	}

	public QuestSay(Integer id, String question, String topic) {
		super();
		this.id = id;
		this.question = question;
		this.topic = topic;
	}

	public QuestSay(Integer id, String question, String topic, String opa) {
		super();
		this.id = id;
		this.question = question;
		this.topic = topic;
		this.opa = opa;
	}

	public QuestSay(Integer id, String question, String topic, String opa, String opb) {
		super();
		this.id = id;
		this.question = question;
		this.topic = topic;
		this.opa = opa;
		this.opb = opb;
	}

	public QuestSay(Integer id, String question, String topic, String opa, String opb, String opc) {
		super();
		this.id = id;
		this.question = question;
		this.topic = topic;
		this.opa = opa;
		this.opb = opb;
		this.opc = opc;
	}

	public QuestSay(Integer id, String question, String topic, String opa, String opb, String opc, String corr_op) {
		super();
		this.id = id;
		this.question = question;
		this.topic = topic;
		this.opa = opa;
		this.opb = opb;
		this.opc = opc;
		this.corr_op = corr_op;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getOpa() {
		return opa;
	}

	public void setOpa(String opa) {
		this.opa = opa;
	}

	public String getOpb() {
		return opb;
	}

	public void setOpb(String opb) {
		this.opb = opb;
	}

	public String getOpc() {
		return opc;
	}

	public void setOpc(String opc) {
		this.opc = opc;
	}

	public String getCorr_op() {
		return corr_op;
	}

	public void setCorr_op(String corr_op) {
		this.corr_op = corr_op;
	}

}
