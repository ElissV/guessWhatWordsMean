package com.game.gui;

import com.game.GameProcess;
import com.game.question.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonClickListener implements ActionListener {

    private GameForm form;
    private GameProcess game;

    ButtonClickListener(GameProcess game, GameForm form) {
        this.game = game;
        this.form = form;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (game.programWaitsForAnswer()) {
            checkAnswerAndShowResult(e);
        }
    }

    private void checkAnswerAndShowResult(ActionEvent e) {
        boolean answerIsRight = answerIsRight(e);
        showResult(answerIsRight, e);
    }

    private boolean answerIsRight(ActionEvent e) {
        String answer = e.getActionCommand();
        Question question = game.getCreator().getCurrentQuestion();
        return question.isRightAnswer(answer);
    }

    private void showResult(boolean answerIsRight, ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (answerIsRight) {
            showRightAnswer(clicked);
        } else {
            showThatAnswerIsWrongAndShowRightAnswer(clicked);
        }
        game.updateScore(answerIsRight);
        setScoreLabelText();
        game.setWaitsForAnswerFalse();
    }

    private void showRightAnswer(JButton buttonWithRightAnswer) {
        Color green = Color.decode("#64DF58");
        buttonWithRightAnswer.setBackground(green);
    }

    private void showThatAnswerIsWrongAndShowRightAnswer(JButton clicked) {
        showThatAnswerIsWrong(clicked);
        JButton rightButton = getButtonWithRightAnswer();
        if (rightButton != null)
            showRightAnswer(rightButton);
    }

    private void showThatAnswerIsWrong(JButton wrongButton) {
        Color red = Color.decode("#DC3232");
        wrongButton.setBackground(red);
    }

    private void setScoreLabelText() {
        int rightAnswers = game.getCountOfRightAnswersGiven();
        int questionsShown = game.getQuestionsAnsweredCount();
        form.setScoreLabel(rightAnswers + "/" + questionsShown);
    }

    private JButton getButtonWithRightAnswer() {
        JButton[] jButtons = form.getButtonArray();
        Question q = game.getCreator().getCurrentQuestion();
        for (JButton j : jButtons) {
            String buttonText = j.getText();
            if (q.isRightAnswer(buttonText))
                return j;
        }
        return null;
    }

}