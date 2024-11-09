package id316553098_id316220482;

public class OpenQuestion extends Question {
	private Answer answer;

	public OpenQuestion(String text, Answer answer) {
		super(text);
		this.answer = answer;
	}

	public String getAnswerText() {
		return answer.getText();
	}

	public Answer getAnswer() {
		return answer;
	}

	public boolean setAnswer(Answer answer) {
		this.answer = answer;
		return true;
	}

	public boolean deleteAnswer() {
		setAnswer(null);
		return true;
	}

	@Override
	public String toString() {
		return super.getText() + " id: " + super.getId()
				+ (answer == null ? " This Question has no answer." : " Answer: " + getAnswerText());
	}

}
