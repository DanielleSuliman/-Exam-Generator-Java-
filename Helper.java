package id316553098_id316220482;

import java.util.ArrayList;

public class Helper {
	Manager manager;

	public Helper(Manager manager) {
		this.manager = manager;
	}

//Quesions
	public boolean isQuestionExists(Question q) {
		if (manager.getTotalNumberOfQuestions() == 0) {
			return false;
		}

		Question[] arr = manager.getAllQuestions();

		for (int i = 0; i < manager.getTotalNumberOfQuestions(); i++) {
			if (arr[i].getText().equals(q.getText())) {

				return true;

			}
		}

		return false;
	}

	public Question getQuestionById(int id) {
		Question[] arr = manager.getAllQuestions();
		for (int i = 0; i < manager.getTotalNumberOfQuestions(); i++) {
			if (arr[i].getId() == id) {
				return arr[i];
			}
		}
		return null;
	}

//This area is for task 8;

	// This function meant to create random number that will be used as a index in
	// questions array.(Could also be used just for create random number in certain
	// range of numbers).
	public int setRandom(int rangeOfIndexes) {
		double rand = 0 + (Math.random() * rangeOfIndexes);
		return (int) rand;
	}

	public ArrayList<Integer> createRadomIndexArrayList(int lengthOfPrevArray, int lengthOfNewArrayList) {
		ArrayList<Integer> indexList = new ArrayList<Integer>(lengthOfNewArrayList);
		int rand;
		int counter = 0;
		do {
			rand = setRandom(lengthOfPrevArray);
			if (!indexList.contains(rand)) {
				indexList.add(rand);
				counter++;
			}
		} while (counter != lengthOfNewArrayList);
		return indexList;
	}

	public Question[] sortQuestionArrayByText(Question[] arr) {
		Question[] newArr = arr;

		Question temp;
		for (int i = 0; i < arr.length; i++) {
			for (int j = i + 1; j < arr.length; j++) {
				if (newArr[i].getText().compareTo(newArr[j].getText()) > 0) {
					temp = newArr[i];
					newArr[i] = newArr[j];
					newArr[j] = temp;
				}
			}
		}
		return newArr;
	}

	public boolean isChosenAnswerAlreayChose(MultipleChoiseAnswer multAns, MultipleChoiseAnswer[] chosenAnswers,
			int numOfChosen) {
		for (int i = 0; i < numOfChosen; i++) {
			if (multAns.equals(chosenAnswers[i])) {
				return true;
			}
		}
		return false;
	}
}
