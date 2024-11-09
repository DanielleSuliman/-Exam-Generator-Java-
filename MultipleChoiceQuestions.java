package id316553098_id316220482;

import java.util.Arrays;

public class MultipleChoiceQuestions extends Question {

	private final int MAX_ANSWERS = 10;
	private final int ENLARGE_ARRAY = 2;
	private int currentNumberOfAnswers;;
	private MultipleChoiseAnswer[] answers; // Notice that the answer at index 0 is the correct answer

	public MultipleChoiceQuestions(String text, MultipleChoiseAnswer[] answersArray, int currentNumberOfAnswers) {
		super(text);
		answers = answersArray;
		this.currentNumberOfAnswers = currentNumberOfAnswers;
	}

	public boolean addAnswer(MultipleChoiseAnswer answer) {
		if (this.currentNumberOfAnswers >= this.MAX_ANSWERS) {
			return false;
		}
		this.answers = Arrays.copyOf(this.answers, this.answers.length * this.ENLARGE_ARRAY);
		this.answers[currentNumberOfAnswers] = answer;
		currentNumberOfAnswers++;
		return true;
	}

	public int getCurrentNumberOfAnswers() {
		return currentNumberOfAnswers;
	}

	public MultipleChoiseAnswer[] getAnswers() {
		return answers;
	}

	public String getCorrectAnswer() {
		return answers[0].getText();
	}

	public void setAnswers(MultipleChoiseAnswer[] answers) {
		this.answers = answers;
	}

	public boolean deleteAnswerById(int id) {
		MultipleChoiseAnswer[] arr = getAnswers();
		for (int i = 0; i < currentNumberOfAnswers; i++) {
			if (arr[i].getId() == id) {
				arr[i] = null;
				orderArrayAfterDeleteQuestion(i);
				currentNumberOfAnswers--;
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.getText() + " id: " + super.getId() + " Answers: \n" + getAnswerListAsColumn();
	}

//helper function
	public StringBuffer getAnswerListAsColumn() {
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < currentNumberOfAnswers; i++) {
			if (answers[i] != null) {
				str.append((i + 1) + ") " + answers[i].getText() + " "
						+ (answers[i].isCorrect() ? "Correct" : "Incorrect") + "\n");
			}
		}
		return str;
	}

	// The function will order the array from the wanted index
	public void orderArrayAfterDeleteQuestion(int index) {
		for (int i = index; i < currentNumberOfAnswers - 1; i++) {
			answers[i] = answers[i + 1];
		}
	}

}
