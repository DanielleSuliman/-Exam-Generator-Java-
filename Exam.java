package id316553098_id316220482;

public class Exam {
	private static int id_generator = 1000;
	private int id;
	private Question[] questions;
	private MultipleChoiseAnswer[] chosenAnswers;
	private int numberOfQuestions;
	private int numberOfChosenAnswers; // Only matters for MultiQuestins.

	public Exam(int currentNumberOfQuestions, Question[] questions, MultipleChoiseAnswer[] chosenAnswers,
			int numberOfChosenAnswers) {
		id = id_generator++;
		this.numberOfQuestions = currentNumberOfQuestions;
		this.questions = questions;
		this.chosenAnswers = chosenAnswers;
		this.numberOfChosenAnswers = numberOfChosenAnswers;
	}

	public Question[] getQuestions() {
		return questions;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	public int getNumberOfChosenAnswers() {
		return numberOfChosenAnswers;
	}

	public void setNumberOfChosenAnswers(int numberOfChosenAnswers) {
		this.numberOfChosenAnswers = numberOfChosenAnswers;
	}

	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}

	public MultipleChoiseAnswer[] getChosenAnswers() {
		return chosenAnswers;
	}

	public void setChosenAnswers(MultipleChoiseAnswer[] chosenAnswers) {
		this.chosenAnswers = chosenAnswers;
	}

	public int getId() {
		return id;
	}

	public String allQuestionstoString() {
		return questions.toString();
	}

	@Override
	public String toString() {
		return "Exam ID: " + id + "\n" + allQuestionsToStringByColumn();
	}

	public StringBuffer allQuestionsToStringByColumn() {
		StringBuffer s = new StringBuffer("");
		MultipleChoiceQuestions other_mult;
		OpenQuestion other_open;
		for (int i = 0; i < getNumberOfQuestions(); i++) {
			if (getQuestions()[i] instanceof MultipleChoiceQuestions) {
				s.append("Question no. " + (i + 1) + ") " + getQuestions()[i].getText() + "\n");
				other_mult = (MultipleChoiceQuestions) getQuestions()[i];
				for (int j = 0; j < other_mult.getCurrentNumberOfAnswers(); j++) {
					if (isChosenAnswer(other_mult.getAnswers()[j])) {
						s.append((j + 1) + ") " + other_mult.getAnswers()[j].getText() + "\n");
					}
				}
				s.append((other_mult.getCurrentNumberOfAnswers() + 1) + ") " + "All answers are incorrect" + "\n");
				s.append((other_mult.getCurrentNumberOfAnswers() + 2) + ") " + "There is more then one correct answer"
						+ "\n");
			} else if (getQuestions()[i] instanceof OpenQuestion) {
				other_open = (OpenQuestion) getQuestions()[i];
				s.append((i + 1) + ") " + other_open.toString() + "\n");
			}
			s.append("\n");
		}
		return s;
	}

	// helper
	public boolean isChosenAnswer(MultipleChoiseAnswer answer) {
		for (int i = 0; i < chosenAnswers.length; i++) {
			if (answer.equals(chosenAnswers[i])) {
				return true;
			}
		}
		return false;
	}

}