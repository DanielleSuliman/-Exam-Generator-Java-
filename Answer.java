package id316553098_id316220482;

public class Answer {
	public static int id_generator = 1000;
	private int id;
	private String text;

	public Answer(String text) {
		id = id_generator++;
		setText(text);
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public boolean setText(String text) {
		this.text = text;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", text=" + text + "]";
	}

}
