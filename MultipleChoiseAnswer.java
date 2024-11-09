package id316553098_id316220482;

public class MultipleChoiseAnswer extends Answer {
	private boolean isCorrect;

	public MultipleChoiseAnswer(String text, boolean isCorrect) {
		super(text);
		this.isCorrect = isCorrect;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (isCorrect ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MultipleChoiseAnswer other = (MultipleChoiseAnswer) obj;
		if (isCorrect != other.isCorrect)
			return false;
		return true;
	}

}
