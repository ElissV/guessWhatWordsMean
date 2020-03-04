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
            String answer = e.getActionCommand();
            JButton clicked = (JButton) e.getSource();
            Question q = game.getCurrentQuestion();
            if (q.isRightAnswer(answer)) {
                Color green = Color.decode("#64DF58");
                clicked.setBackground(green);
            } else {
                Color red = Color.decode("#DC3232");
                clicked.setBackground(red);
                showRightAnswer();
            }
            game.checkAnswerAndUpdateScore(answer);
            setScoreLabelText();
            game.setWaitsForAnswerFalse();
        }
    }

    private void setScoreLabelText() {
        int rightAnswers = game.getCountOfRightAnswersGiven();
        int questionsShown = game.getQuestionsAnsweredCount();
        form.setScoreLabel(rightAnswers + "/" + questionsShown);
    }

    private void showRightAnswer() {
        JButton button = getButtonWithRightAnswer();
        if (button != null) {
            Color green = Color.decode("#64DF58");
            button.setBackground(green);
        } else
            showErrorMessage();
    }

    private void showErrorMessage() {
        JOptionPane.showMessageDialog(form.getjFrame(),
                "Error.\nRight answer not found.");
    }

    private JButton getButtonWithRightAnswer() {
        JButton[] jButtons = form.getButtonArray();
        Question q = game.getCurrentQuestion();
        for (JButton j : jButtons) {
            String buttonText = j.getText();
            if (q.isRightAnswer(buttonText))
                return j;
        }
        return null;
    }

}