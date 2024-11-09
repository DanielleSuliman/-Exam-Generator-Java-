package id316553098_id316220482;

import java.util.Arrays;
import java.util.Scanner;

import Exception.NumberTooHigh;
import Exception.UserChoiceExecptions;

public class main {

	public static void main(String[] args) {
		Manager manager = new Manager();
		int pageState = 0;
		Scanner sc = new Scanner(System.in);
		int user_choise_mistake = 0;
		// Creating questions meant to be tested.

		do {

			switch (pageState) {
			case 0:
				System.out.println("Hello, this is a menu please choose an option.");
				System.out.println("1) Print all existing questions (with thier answers");
				System.out.println("2) Add Question and Answer");
				System.out.println("3) Edit exist question");
				System.out.println("4) Edit exist answer");
				System.out.println("5) Delete exist answer");
				System.out.println("6) Create exam manually");
				System.out.println("7) Create exam automatically");
				System.out.println("8) Exit program");
				System.out.println("9) Initialize program");
				try {
					pageState = sc.nextInt();
					break;
				} catch (Exception e) {
					System.out.println("Must be number!");
					pageState = 0;

					sc.next();
					break;
				}

			case 1:
				System.out.println(manager.allQuestionsToStringByColumn());
				pageState = 0;
				break;
			case 2:
				int userChoise;
				System.out.println("Please press 1 for open question, press 2 for multiple choise question (American)");

				try {
					userChoise = sc.nextInt();
					if (userChoise == 1) {
						System.out.println("Please insert Question's text");
						sc.nextLine();
						String text = sc.nextLine();
						System.out.println("Please insert Question's answer (it's a string)");
						String answer = sc.nextLine();
						if (manager.addOpenQuestion(text, new Answer(answer))) {
							System.out.println("Question and answer Added! returning to main menu! \n");
							pageState = 0;
							break;
						} else {
							System.out.println("Question already exists or there is an error with data.");
							pageState = 2;
							break;
						}
					} else if (userChoise == 2) {
						System.out.println("Please insert Question's text");
						sc.nextLine();
						String text = sc.nextLine();
						System.out.println("How many answers would you like the question to have? (Maximum 10)");

						try {
							int numberOfAnswers = sc.nextInt();
							if (numberOfAnswers <= 10) {
								MultipleChoiseAnswer[] array = new MultipleChoiseAnswer[numberOfAnswers];
								System.out.println(
										"First please enter the correct answer, and then we will ask you add the rest of the answers");
								System.out.println("Correct answer text:");

//							String correct_answer_text = sc.nextLine();
								array[0] = new MultipleChoiseAnswer(sc.next(), true);

								System.out.println("Now add another more: " + (numberOfAnswers - 1)
										+ " answers which will be incorrect");
								for (int i = 1; i < numberOfAnswers; i++) {
									System.out.println("Another answer");
									String wront_answer_text = sc.next();
									array[i] = new MultipleChoiseAnswer(wront_answer_text, false);

								}
								manager.addMultiQuestion(text, array, numberOfAnswers);
								System.out.println("New question created");
								pageState = 0;
								break;
							} else {
								System.out.println("Maximum number of answers is 10!");
								sc.next();
								pageState = 2;
								break;
							}
						} catch (Exception e) {
							System.out.println("General Error: " + e.getMessage());
							pageState = 0;
							sc.next();
							break;
						}
					} else {
						throw new UserChoiceExecptions();
					}
				} catch (UserChoiceExecptions e) {
					System.out.println(e.getMessage());
					pageState = 2;
					break;
				} catch (Exception e) {
					System.out.println("Must be number");
					sc.next();
					pageState = 2;
					break;
				}
			case 3:
				// First check if there are questions available to edit.
				if (manager.getTotalNumberOfQuestions() == 0) {
					System.out.println("There are no questions! returning to main menu");

					pageState = 0;
					break;
				} else {// Happends when there are questions available to edit

					try {
						int user_choise;
						if (!(user_choise_mistake != 1) || !(user_choise_mistake == 2)) {
							System.out.println("If you know the Question id type 1");
							System.out.println("To watch and choose from list please press 2");
							user_choise = sc.nextInt();
						} else {
							user_choise = user_choise_mistake;
						}

						if (user_choise == 1) {
							System.out.println("Please enter the question's id");
							int id = sc.nextInt();
							if (manager.helper.getQuestionById(id) == null) {
								System.out.println("Error: Question doesn't exist");
								pageState = 3;
								break;
							}
							System.out.println("Please insert the new question's text (it's a string)");
							sc.nextLine();
							manager.editQuestionById(id, sc.nextLine());
							System.out.println("Question - " + id + " has a new text");
							pageState = 0;
							break;
						} else if (user_choise == 2) {
							Question[] arr = manager.getAllQuestions();

							for (int i = 0; i < manager.getTotalNumberOfQuestions(); i++) {
								System.out.println((i + 1) + ") " + arr[i].getText() + " id: " + arr[i].getId());
							}
							try {
								System.out.println("Please make a choise out of the list above:");
								int choise = sc.nextInt();
								System.out.println("Please insert new text (it's a string)");
								sc.nextLine();
								String text = sc.nextLine();
								System.out.println(arr[choise - 1].getId());
								System.out.println(text);
								if (manager.editQuestionById(arr[choise - 1].getId(), text)) {
									System.out.println("Question - " + arr[choise - 1].getId() + " has a new text");
									pageState = 0;
									break;
								} else {
									System.out.println("Something wen't wrong");
									pageState = 3;
								}

							} catch (Exception e) {
								sc.next();
								user_choise_mistake = 2;
								pageState = 3;
								break;
							}

						} else {
							throw new UserChoiceExecptions();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
						pageState = 3;
						break;
					}

				}

			case 4:
				System.out.println(manager.allQuestionsToStringByColumn());
				System.out.println(
						"Please choose the question that has the answer you would like to edit from the list above.");
				try {

					int choise = sc.nextInt();
					if (choise > manager.getTotalNumberOfQuestions()) {
						throw new NumberTooHigh(manager.getTotalNumberOfQuestions());
					}
					if (manager.getAllQuestions()[choise - 1] instanceof MultipleChoiceQuestions) {
						MultipleChoiceQuestions other = (MultipleChoiceQuestions) manager.getAllQuestions()[choise - 1];
						System.out.println(other.toString());

						System.out.println("Please choose the answer you would like to edit.");
						int index_choise = sc.nextInt(); // Second
						System.out.println("Enter new answer's text");
						sc.nextLine();
						String text = sc.nextLine();
						manager.editAnswerByQuestionId(manager.getAllQuestions()[choise - 1].getId(), text,
								index_choise - 1);
						System.out.println("Answer has changed");

						pageState = 0;
						break;
					} else {
						System.out.println("Enter new answer's text");
						sc.nextLine();
						String text = sc.nextLine();
						manager.editAnswerByQuestionId(manager.getAllQuestions()[choise - 1].getId(), text, 0);
						pageState = 0;
						break;
					}
				} catch (Exception e) {
					System.out.println(e.getMessage());
					sc.next();
					pageState = 4;
					break;
				}

			case 5:
				Question[] arr = manager.getAllQuestions();
				System.out.println(manager.allQuestionsToStringByColumn());
				System.out.println("Please choose a question that you would to delete answer from.");
				int user_choise = sc.nextInt();
				if (arr[user_choise - 1] instanceof MultipleChoiceQuestions) {
					MultipleChoiceQuestions other = (MultipleChoiceQuestions) arr[user_choise - 1];
					MultipleChoiseAnswer[] questionAnswerArr = other.getAnswers();
					System.out.println(other.getAnswerListAsColumn());
					System.out.println("Please choose the answer you would like to delete");
					int index = sc.nextInt();
					manager.deleteAnswerFromQuestion(other.getId(), questionAnswerArr[index - 1].getId());
					System.out.println("Answer was successfully deleted!");
					pageState = 0;
					break;
				} else if (arr[user_choise - 1] instanceof OpenQuestion) {
					OpenQuestion other = (OpenQuestion) arr[user_choise - 1];
					other.deleteAnswer();
					System.out.println("Answer was successfully deleted!");
					pageState = 0;
					break;
				} else {
					pageState = 5;
					break;
				}
			case 6:
				System.out.println("How many questions would you like the exam to include?");

				try {
					int currentNumberOfQuestions = sc.nextInt();
					int numbOfChosenAnswers = 0;
					System.out.println(manager.allQuestionsToStringByColumn());
					System.out.println("Your exam includes " + currentNumberOfQuestions
							+ " questions. \n pleas choose the questions that you would like the exam to include");
					Question[] chosenQuestionsArr = new Question[currentNumberOfQuestions];
					Question[] allQuestionsArr = manager.getAllQuestions();
					MultipleChoiseAnswer[] chosenAnswerArr = new MultipleChoiseAnswer[1];
					for (int i = 0; i < currentNumberOfQuestions; i++) {
						System.out.println("Please choose question number " + (i + 1));

						int index = sc.nextInt();
						;// The user will choose a quesion by index
						try {
							if (index > manager.getTotalNumberOfQuestions() || index < 1) {
								throw new NumberTooHigh(manager.getTotalNumberOfQuestions());
							}
						} catch (Exception e) {
							System.out.println(e.getMessage());
							i--;
						}

						if (allQuestionsArr[index - 1] instanceof MultipleChoiceQuestions) {
							chosenQuestionsArr[i] = allQuestionsArr[index - 1];

							MultipleChoiceQuestions other = (MultipleChoiceQuestions) allQuestionsArr[index - 1];

							System.out.println(other.getAnswerListAsColumn());

							// Validating answersToShow by do while loop. As long as the user will ask to
							// show more answers than the question has the loop will keep send him back.

							int answersToShow;
							do {
								System.out.println("How many answers would you like to be shown from the list above?");
								answersToShow = sc.nextInt();

								if (answersToShow > other.getCurrentNumberOfAnswers() || answersToShow < 1) {
									System.out.println("Invalid");

								} else if (answersToShow != other.getCurrentNumberOfAnswers()) {
									System.out.println(
											"Please choose the answers you would like to be shown. press -1 to to save the chosen answers.");
									int chosen_answer_index = 0;

									for (int j = 0; j < answersToShow; j++) {
										try {
											System.out.println("Choose answer number:  " + (j + 1));
											chosen_answer_index = sc.nextInt();
											if (chosen_answer_index > answersToShow) {
												throw new NumberTooHigh(answersToShow);
											}
											chosenAnswerArr = Arrays.copyOf(chosenAnswerArr,
													chosenAnswerArr.length * 2);
											chosenAnswerArr[numbOfChosenAnswers++] = other
													.getAnswers()[chosen_answer_index - 1];
										} catch (Exception e) {
											System.out.println(e.getMessage());
											j--;
										}
									}
								} else {
									for (int j = 0; j < answersToShow; j++) {

										chosenAnswerArr = Arrays.copyOf(chosenAnswerArr, chosenAnswerArr.length * 2);
										chosenAnswerArr[numbOfChosenAnswers++] = other.getAnswers()[j];
									}
								}
							} while (answersToShow > other.getCurrentNumberOfAnswers() || answersToShow < 1);

						} else if (allQuestionsArr[index - 1] instanceof OpenQuestion) {
							chosenQuestionsArr[i] = allQuestionsArr[index - 1];
						}

					}

					Exam exam = new Exam(currentNumberOfQuestions, chosenQuestionsArr, chosenAnswerArr,
							numbOfChosenAnswers);
					manager.createNewExam(exam);

					System.out.println(manager.getExamById(exam.getId()).toString());

				} catch (Exception e) {
					pageState = 6;
					sc.next();
					break;
				}
				pageState = 0;
				break;
			case 7:
				System.out.println("How many questions would you like the exam to include?");
				sc.nextLine();
				int numOfQuestions = sc.nextInt();
				if (numOfQuestions > manager.getTotalNumberOfQuestions() || numOfQuestions < 1) {
					System.out.printf("There are %d questions available! \n", manager.getTotalNumberOfQuestions());
					pageState = 7;
					break;
				}

				System.out.print(manager.createAutoExam(numOfQuestions).toString());
				pageState = 0;
				break;

			case 9:
				manager.initializeSystem();
				System.out.print("System initialized! Returning to main menu \n");
				pageState = 0;
				break;
			default:
				pageState = 0;
				break;
			}
		} while (pageState != 8);
		System.out.print("Shutting down. Good bye!");
	}

};