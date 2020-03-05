package com.game.question;

import com.game.gui.GameForm;

public class QuestionCreator {

    private GameForm gameForm;
    private Question currentQuestion;
    private NextQuestion nextQuestion;

    public QuestionCreator(GameForm gameForm) {
        this.gameForm = gameForm;
    }

    public void createQuestions() {
        createNextQuestion();
        currentQuestion = new CurrentQuestion();
        gameForm.showFirstQuestion();
    }

    public void getCurrentAndNextQuestion() {
        if (nextQuestionIsNotCreated()) {
            createNextQuestion();
            gameForm.wordIsLoading();
        } else {
            currentQuestion = nextQuestion;
            gameForm.showQuestion();
            if (nextQuestion.questionIsNotBeingCreated()) {
                createNextQuestion();
            }
        }
    }

    private boolean nextQuestionIsNotCreated() {
        return currentQuestion.getWordForQuestion()
                .equals(nextQuestion.getWordForQuestion());
    }

    private void createNextQuestion() {
        Runnable questionCreatorRunnable =
                this::createNewQuestionAndCheck;
        new Thread(questionCreatorRunnable).start();
    }

    private void createNewQuestionAndCheck() {
        nextQuestion = new NextQuestion();
        if (userWaitsForQuestion()) {
            showCreatedQuestionAndGetAnotherNextQuestion();
        }
    }

    private boolean userWaitsForQuestion() {
        return gameForm.getQuestionLabelText().equals("Loading...");
    }

    private void showCreatedQuestionAndGetAnotherNextQuestion() {
        currentQuestion = nextQuestion;
        gameForm.showQuestion();
        nextQuestion = new NextQuestion();
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

}