package id316553098_id316220482;

public abstract class Question {
	public static int id_generator = 1000;
	private String text;
	private int id;

	public Question(String text) {
		id = id_generator++;
		this.setText(text);
	}

	public String getText() {
		return text;
	}

	public int getId() {
		return id;
	}

	public void setText(String text) {
		this.text = text;
	}

}
