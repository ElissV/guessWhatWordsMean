package com.game;

import com.game.gui.GameForm;
import com.game.question.CurrentQuestion;
import com.game.question.NextQuestion;
import com.game.question.Question;

import javax.swing.*;

public class GameProcess {

    private GameForm gameForm;
    private Question currentQuestion;
    private NextQuestion nextQuestion;
    private boolean programWaitsForAnswer;
    private int questionsAnswered;
    private int rightAnswersGiven;


    GameProcess(GameForm gameForm) {
        this.gameForm = gameForm;
        setInitialValues();
    }

    private void setInitialValues() {
        currentQuestion = null;
        nextQuestion = null;
        questionsAnswered = 0;
        rightAnswersGiven = 0;
        programWaitsForAnswer = true;
    }

    void askFirstQuestion() {
        gameForm.setGame(this);
        getNextQuestion();
        currentQuestion = new CurrentQuestion();
        gameForm.showFirstQuestion();
    }

    public void askQuestion() {
        startOverIfGameFinished();
        if (!programWaitsForAnswer) {
            getCurrentAndNextQuestion();
            programWaitsForAnswer = true;
        }
    }

    private void startOverIfGameFinished() {
        if (gameIsFinished()) {
            showScore();
            setInitialValues();
            GameStart.startGame();
        }
    }

    private boolean gameIsFinished() {
        int questionTotalCount = 10;
        return questionsAnswered == questionTotalCount;
    }

    private void getCurrentAndNextQuestion() {
        if (nextQuestionIsNotCreated()) {
            getNextQuestion();
            gameForm.wordIsLoading();
        } else {
            currentQuestion = nextQuestion;
            gameForm.showQuestion();
            if (nextQuestion.questionIsNotBeingCreated()) {
                getNextQuestion();
            }
        }
    }

    private boolean nextQuestionIsNotCreated() {
        return currentQuestion.getWordForQuestion()
                .equals(nextQuestion.getWordForQuestion());
    }

    private void getNextQuestion() {
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

    private void showScore() {
        String message = "Your score is " +
                rightAnswersGiven + "/" + questionsAnswered + "!\n" +
                "Press OK to try again.";
        JOptionPane.showMessageDialog(gameForm.getjFrame(), message);
    }

    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public void updateScore(boolean rightAnswerWasGiven) {
        if (rightAnswerWasGiven)
            rightAnswersGiven++;
        questionsAnswered++;
    }

    public boolean programWaitsForAnswer() {
        return programWaitsForAnswer;
    }

    public void setWaitsForAnswerFalse() {
        programWaitsForAnswer = false;
    }

    public int getQuestionsAnsweredCount() {
        return questionsAnswered;
    }

    public int getCountOfRightAnswersGiven() {
        return rightAnswersGiven;
    }

}