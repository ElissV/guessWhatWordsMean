class GameProcess {

    private GameForm gameForm;
    private Question currentQuestion;
    private NextQuestion nextQuestion;
    private boolean programWaitsForAnswer = true;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    GameProcess(GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askFirstQuestion() {
        System.out.println(this);
        gameForm.setGame(this);
        getNextQuestion();
        currentQuestion = new CurrentQuestion();
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
            if (currentQuestion.getWordForQuestion().equals(nextQuestion.getWordForQuestion())) {
                getNextQuestion();
                gameForm.wordIsLoading();
                return;
            } else {
                currentQuestion = nextQuestion;
                gameForm.showQuestion();
                if (nextQuestion.questionIsNotBeingCreated()) {
                    getNextQuestion();
                }
            }
        }
        if (currentQuestion.getWordForQuestion().equals(nextQuestion.getWordForQuestion())) {
            getNextQuestion();
            gameForm.wordIsLoading();
            return;
        }
        currentQuestion = nextQuestion;
        gameForm.showQuestion();
        if (nextQuestion.questionIsNotBeingCreated()) {
            getNextQuestion();
        }
    }

    private void getNextQuestion() {
        Runnable questionCreatorRunnable =
                this::createNewQuestionAndCheck;
        new Thread(questionCreatorRunnable).start();
    }

    private void createNewQuestionAndCheck() {
        nextQuestion = new NextQuestion();
        if (gameForm.getQuestionLabelText().equals("Loading...")) {
            currentQuestion = nextQuestion;
            gameForm.showQuestion();
            nextQuestion = new NextQuestion();
        }
    }

    Question getCurrentQuestion() {
        return currentQuestion;
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