
class Game {

    private static final int QUESTIONS_QTY = 10;
    private GameForm gameForm;
    private Question currentQuestion;
    private Question nextQuestion;
    private boolean userWaitsForQuestion = false;
    private boolean programWaitsForAnswer = true;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
    }

    void askFirstQuestion() {
        gameForm.setGameAndQuestion(this);
        getNextQuestion();
        currentQuestion = new Question();
        gameForm.showQuestion();
    }

    void askQuestion() {
        if (!programWaitsForAnswer) {
            getCurrentAndNextQuestion();
            programWaitsForAnswer = true;
        }
    }

    private void getCurrentAndNextQuestion() {
        String askedQuestion = gameForm.getQuestionLabelText();
        if (askedQuestion.equals("Loading...")) {
            if (currentQuestion.getWord().equals(nextQuestion.getWord())) {
                userWaitsForQuestion = true;
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
            userWaitsForQuestion = true;
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
        System.out.println("Next created and user waits: " + userWaitsForQuestion);
        if (gameForm.getQuestionLabelText().equals("Loading...")) {
            currentQuestion = nextQuestion;
            gameForm.showQuestion();
            System.out.println(currentQuestion.getWord() + " | " + nextQuestion.getWord());
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

    public boolean programWaitsForAnswer() {
        return programWaitsForAnswer;
    }

    int getRightAnswersGiven() {
        return rightAnswersGiven;
    }
}