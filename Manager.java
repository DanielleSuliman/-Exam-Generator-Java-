package id316553098_id316220482;

import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
	Helper helper;

	private Exam[] exames;
	private Question[] questionStore;
	private Answer[] answerStore;
	private int totalNumberOfQuestions;
	private int totalNumberOfExams;

	public Manager() {
		questionStore = new Question[1];
		exames = new Exam[1];
		answerStore = new MultipleChoiseAnswer[1];
		totalNumberOfQuestions = 0;
		helper = new Helper(this);
	}

	public Question[] getAllQuestions() {
		return questionStore;
	}

	public StringBuffer allQuestionsToStringByColumn() {
		StringBuffer s = new StringBuffer("");
		for (int i = 0; i < getTotalNumberOfQuestions(); i++) {
			s.append((i + 1) + ") " + getAllQuestions()[i].toString() + "\n");
		}
		return s;
	}

	public int getTotalNumberOfQuestions() {
		return totalNumberOfQuestions;
	}

	public boolean addOpenQuestion(String text, Answer correctAnswer) {
		OpenQuestion oq = new OpenQuestion(text, correctAnswer);
		if (helper.isQuestionExists(oq)) {
			return false;
		}
		questionStore = Arrays.copyOf(questionStore, questionStore.length * 2);
		questionStore[totalNumberOfQuestions++] = oq;
		return true;
	}

	public boolean addMultiQuestion(String text, MultipleChoiseAnswer[] answersArray, int currentNumberOfAnswers) {
		if (answersArray.length > 10) {
			return false;
		}
		MultipleChoiceQuestions q = new MultipleChoiceQuestions(text, answersArray, currentNumberOfAnswers);
		if (helper.isQuestionExists(q)) {
			return false;
		}
		// Add to the question array
		questionStore = Arrays.copyOf(questionStore, questionStore.length * 2);
		questionStore[totalNumberOfQuestions++] = q;
		return true;
	}

	public boolean editQuestionById(int id, String text) {
		Question q = helper.getQuestionById(id);
		q.setText(text);
		return true;
	}

//Answers
	public boolean editAnswerByQuestionId(int id, String text, int index) {
		// If the instance is OpenQuestion the index will be 0
		Question q = helper.getQuestionById(id);
		if (q instanceof MultipleChoiceQuestions) {
			MultipleChoiceQuestions other = (MultipleChoiceQuestions) q;
			other.getAnswers()[index].setText(text);
		} else {
			OpenQuestion other = (OpenQuestion) q;
			other.getAnswer().setText(text);
		}

		return true;
	}

	public boolean deleteAnswerFromQuestion(int question_id, int answer_id) {
		Question q = helper.getQuestionById(question_id);
		if (q == null) {
			return false; // Incase question does'nt exist
		}
		if (q instanceof MultipleChoiceQuestions) {
			((MultipleChoiceQuestions) q).deleteAnswerById(answer_id);
		} else if (q instanceof OpenQuestion) {
			((OpenQuestion) q).deleteAnswer();
		}
		return true;
	}

	// Exam
	public boolean createNewExam(Exam exam) {
		exames = Arrays.copyOf(exames, exames.length * 2);
		exames[totalNumberOfExams++] = exam;
		return true;
	}

	public Exam createAutoExam(int numOfQuestionsChosen) {

		if (numOfQuestionsChosen > getTotalNumberOfQuestions() || numOfQuestionsChosen < 1) {
			return null;
		}

		int numberOfChosenAnswers = 0;
		Question[] questions = new Question[numOfQuestionsChosen];
		MultipleChoiseAnswer[] chosenAnswers = new MultipleChoiseAnswer[1];

		// This arraylist will contain all the indexes that has been added already so
		// they won't show up again.
		ArrayList<Integer> questionsIndexList = helper.createRadomIndexArrayList(getTotalNumberOfQuestions(),
				numOfQuestionsChosen);
		ArrayList<Integer> answersIndexList;
		String[] arrayToSortByText = new String[numOfQuestionsChosen];

		Question fromStoreByIndex;
		for (int i = 0; i < questionsIndexList.toArray().length; i++) {
			fromStoreByIndex = getAllQuestions()[(int) questionsIndexList.toArray()[i]];

			if (fromStoreByIndex instanceof MultipleChoiceQuestions) {
				MultipleChoiceQuestions other = (MultipleChoiceQuestions) fromStoreByIndex;
				if (other.getCurrentNumberOfAnswers() < 4) {
					answersIndexList = helper.createRadomIndexArrayList(other.getCurrentNumberOfAnswers(),
							other.getCurrentNumberOfAnswers());

				} else {
					answersIndexList = helper.createRadomIndexArrayList(other.getCurrentNumberOfAnswers(), 4);
				}
				int indexToPush = 0;
				for (int j = 0; j < answersIndexList.toArray().length; j++) {
					indexToPush = (int) answersIndexList.toArray()[j];
					if (!helper.isChosenAnswerAlreayChose(other.getAnswers()[indexToPush], chosenAnswers,
							numberOfChosenAnswers)) {
						chosenAnswers = Arrays.copyOf(chosenAnswers, chosenAnswers.length * 2);
						chosenAnswers[numberOfChosenAnswers++] = other.getAnswers()[indexToPush];
					}
				}
			}

			questions[i] = fromStoreByIndex;
		}
		questions = helper.sortQuestionArrayByText(questions);
		Exam exam = new Exam(numOfQuestionsChosen, questions, chosenAnswers, numberOfChosenAnswers);
		createNewExam(exam);
		return exam;
	}

	public Exam getExamById(int id) {
		for (int i = 0; i < exames.length; i++) {
			if (exames[i].getId() == id) {
				return exames[i];
			}
		}
		return null;
	}

	// This Method was cre
	public void initializeSystem() {
		for (int i = 0; i < 20; i++) {
			addOpenQuestion("OPEN QUESTION  " + (i + 1), new Answer("OPEN answer number " + (i + 1)));
		}

		MultipleChoiseAnswer[] answers = new MultipleChoiseAnswer[9];
		for (int i = 0; i < answers.length; i++) {
			answers[i] = new MultipleChoiseAnswer("Answer number: " + (i + 1), (i == 0 ? true : false));
		}
		for (int i = 0; i < 5; i++) {
			addMultiQuestion("MultipleChoise Question number: " + (i + 1), answers, 9);
		}

	}

}
