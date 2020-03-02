class Game {

    private static final int QUESTIONS_QTY = 10;
    private GameForm gameForm;
    private Question currentQuestion;
    private Question nextQuestion;
    private boolean programWaitsForAnswer = true;
    private int questionsAnswered = 9;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askFirstQuestion() {
        gameForm.setGame(this);
        getNextQuestion();
        currentQuestion = new Question();
        gameForm.showQuestion();
    }

    void askQuestion() {
        if (questionsAnswered == 10) {
            Main.showMessage("Your score is " + rightAnswersGiven + "/" + questionsAnswered + "!\n" +
                                "Press OK to try again.");
            Main.startGame();
            return;
        }
        if (!programWaitsForAnswer) {
            getCurrentAndNextQuestion();
            programWaitsForAnswer = true;
        }
    }

    private void getCurrentAndNextQuestion() {
        String askedQuestion = gameForm.getQuestionLabelText();
        if (askedQuestion.equals("Loading...")) {
            if (currentQuestion.getWord().equals(nextQuestion.getWord())) {
                getNextQuestion();
                gameForm.wordIsLoading();
                return;
            } else {
                currentQuestion = nextQuestion;
                gameForm.showQuestion();
                if (!nextQuestion.questionIsBeingCreated()) {
                    getNextQuestion();
                }
            }
        }
        if (currentQuestion.getWord().equals(nextQuestion.getWord())) {
            getNextQuestion();
            gameForm.wordIsLoading();
            return;
        }
        currentQuestion = nextQuestion;
        gameForm.showQuestion();
        if (!nextQuestion.questionIsBeingCreated()) {
            getNextQuestion();
        }
    }

    private void getNextQuestion() {
        Runnable questionCreatorRunnable =
                this::createNewQuestionAndCheck;
        new Thread(questionCreatorRunnable).start();
    }

    private void createNewQuestionAndCheck() {
        nextQuestion = new Question();
        if (gameForm.getQuestionLabelText().equals("Loading...")) {
            currentQuestion = nextQuestion;
            gameForm.showQuestion();
            nextQuestion = new Question();
        }
    }

    Question getCurrentQuestion() {
        return currentQuestion;
    }

    int getQUESTIONS_QTY() {
        return QUESTIONS_QTY;
    }

    void gotAnswer(String answer) {
        if (currentQuestion.isRightAnswer(answer))
            rightAnswersGiven++;
        questionsAnswered++;
    }

    void setWaitsForAnswerFalse() {
        programWaitsForAnswer = false;
    }

    int getQuestionsAnswered() {
        return questionsAnswered;
    }

    boolean programWaitsForAnswer() {
        return programWaitsForAnswer;
    }

    int getRightAnswersGiven() {
        return rightAnswersGiven;
    }
}