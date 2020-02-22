class Game {

    private Question question;
    private static final int QUESTIONS_QTY = 10;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    void askQuestion() {
        question = new Question();
    }

}
