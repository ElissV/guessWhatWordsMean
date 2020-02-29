
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
        getCurrentAndNextQuestion();
    }

    void askFirstQuestion() {
        getNextQuestion();
        question = new Question();
        gameForm.setGameAndQuestion(this);
        gameForm.showQuestion();
    }

    private void getCurrentAndNextQuestion() {
        System.out.println("CREATE NEXT QUESTION: " + nextQuestion.questionIsBeingCreated());
        question = nextQuestion;
        System.out.println(question.getWord() + " | " + nextQuestion.getWord());
        gameForm.showQuestion();
        if (!nextQuestion.questionIsBeingCreated()) {
            getNextQuestion();
        }
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

    void gotAnswer(String answer) {
        if (question.isRightAnswer(answer))
            rightAnswersGiven++;
        questionsAnswered++;
    }

    public int getQuestionsAnswered() {
        return questionsAnswered;
    }

    public int getRightAnswersGiven() {
        return rightAnswersGiven;
    }
}