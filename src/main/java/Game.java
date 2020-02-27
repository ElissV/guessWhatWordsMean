
class Game {

    private static final int QUESTIONS_QTY = 10;
    private GameForm gameForm;
    private CurrentQuestion question;
    private CurrentQuestion nextQuestion;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askQuestion() {
        nextQuestion = new NextQuestion();
        //nextQuestion.createQuestion();
        question = new CurrentQuestion();
        question.createQuestion();
        gameForm.showQuestion(question);
    }

    CurrentQuestion getQuestion() {
        return question;
    }

    int getQUESTIONS_QTY() {
        return QUESTIONS_QTY;
    }

}