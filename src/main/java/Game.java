
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
        nextQuestion = new NextQuestion();
        question = new CurrentQuestion();
        gameForm.showQuestion(question);
    }

    Question getQuestion() {
        return question;
    }

    int getQUESTIONS_QTY() {
        return QUESTIONS_QTY;
    }

}