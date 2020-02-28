
class Game {

    private static final int QUESTIONS_QTY = 10;
    private GameForm gameForm;
    private Question question;
    private Question nextQuestion;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askQuestion() {
        getNextQuestion();
        question = new Question();
        gameForm.setGameAndQuestion(this, question);
        gameForm.showQuestion();
    }

    private void getNextQuestion() {
        Runnable questionCreatorRunnable = () ->
                nextQuestion = new Question();
        new Thread(questionCreatorRunnable).start();
    }

    Question getQuestion() {
        return question;
    }

    int getQUESTIONS_QTY() {
        return QUESTIONS_QTY;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public int getRightAnswersGiven() {
        return rightAnswersGiven;
    }
}