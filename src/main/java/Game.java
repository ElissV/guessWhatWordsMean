
class Game {

    private GameForm gameForm;
    private Question question;
    private Question nextQuestion;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askQuestion() {
        question = new Question();
        question.createQuestion();
        nextQuestion = new NextQuestion();
        nextQuestion.createQuestion();
        gameForm.showQuestion(question);
    }

    Question getQuestion() {
        return question;
    }

    int getQUESTIONS_QTY() {
        int QUESTIONS_QTY = 10;
        return QUESTIONS_QTY;
    }

}