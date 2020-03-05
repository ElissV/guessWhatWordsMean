package com.game;

import javax.swing.*;

import com.game.gui.GameForm;
import com.game.question.QuestionCreator;

public class GameProcess {

    private GameForm gameForm;
    private QuestionCreator creator;
    private boolean programWaitsForAnswer;
    private int questionsAnswered;
    private int rightAnswersGiven;


    GameProcess(GameForm gameForm) {
        creator = new QuestionCreator(gameForm);
        this.gameForm = gameForm;
        setInitialValues();
    }

    private void setInitialValues() {
        questionsAnswered = 0;
        rightAnswersGiven = 0;
        programWaitsForAnswer = true;
    }

    void askFirstQuestion() {
        gameForm.setGame(this);
        creator.createQuestions();
    }

    public void askQuestion() {
        startOverIfGameFinished();
        if (!programWaitsForAnswer) {
            creator.getCurrentAndNextQuestion();
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

    private void showScore() {
        String message = "Your score is " +
                rightAnswersGiven + "/" + questionsAnswered + "!\n" +
                "Press OK to try again.";
        JOptionPane.showMessageDialog(gameForm.getjFrame(), message);
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

    public QuestionCreator getCreator() {
        return creator;
    }

    public int getQuestionsAnsweredCount() {
        return questionsAnswered;
    }

    public int getCountOfRightAnswersGiven() {
        return rightAnswersGiven;
    }

}