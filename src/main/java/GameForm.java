import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GameForm {

    private Question currentQuestion;

    private JFrame jFrame;
    private JPanel panel1;
    private JSplitPane splitPane;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel scoreLabel;
    private JLabel questionLabel;
    private JSplitPane answers1;
    private JSplitPane answers2;

    GameForm() {
        int frameWidth = 700, frameHeight = 450;
        jFrame = new JFrame();
        jFrame.setSize(frameWidth, frameHeight);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setTitle("Word Game");
        jFrameCentralize();
        placeElements();
        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    private void jFrameCentralize() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (dim.width/2)-(jFrame.getSize().width/2);
        int y = (dim.height/2)-(jFrame.getSize().height/2);
        jFrame.setLocation(x, y);
    }

    private void placeElements() {
        setElementsSize();
        Container contentPane = jFrame.getContentPane();
        contentPane.add(splitPane, BorderLayout.NORTH);
        contentPane.add(answers1, BorderLayout.CENTER);
        contentPane.add(answers2, BorderLayout.SOUTH);
    }

    private void setElementsSize() {
        scoreLabel.setPreferredSize(new Dimension(100, 70));
        Dimension buttonDimension = new Dimension(320, 10);
        button1.setPreferredSize(buttonDimension);
        button2.setPreferredSize(buttonDimension);
        button3.setMinimumSize(buttonDimension);
        button4.setMinimumSize(buttonDimension);
        answers1.setPreferredSize(new Dimension(jFrame.getWidth(), 80));
        answers2.setPreferredSize(new Dimension(jFrame.getWidth(), 180));
    }

    void showQuestion(Question question) {
        currentQuestion = question;
        setWordAndScore();
        setButtonsText(question.getOptionsForAnswer());
    }

    private void setWordAndScore() {
        setScoreLabelInitialValue();
        questionLabel.setText(currentQuestion.getWord());
    }

    private void setScoreLabelInitialValue() {
        int initialScore = 0;
        int totalQuestions = Main.getQuestionsQTY();
        scoreLabel.setText(initialScore + "/" + totalQuestions);
    }

    private void setButtonsText(List<String> text) {
        Question q = Main.getQuestion();
        JButton[] jButtons = getJButtonArray();
        int i = 0;
        for (JButton button : jButtons) {
            button.setText(text.get(i));
            button.addActionListener(listener);
            i++;
        }
    }

    private JButton[] getJButtonArray() {
        JButton[] jButtons = new JButton[4];
        jButtons[0] = button1;
        jButtons[1] = button2;
        jButtons[2] = button3;
        jButtons[3] = button4;
        return jButtons;
    }

    private ActionListener listener = e -> {
        String answer = e.getActionCommand();
        JButton clicked = (JButton) e.getSource();
        if (currentQuestion.isRightAnswer(answer)) {
            Color green = Color.decode("#64DF58");
            clicked.setBackground(green);
        } else {
            Color red = Color.decode("#DC3232");
            clicked.setBackground(red);
            showRightAnswer();
        }
    };

    private void showRightAnswer() {
        JButton button = getButtonWithRightAnswer();
        if (button != null) {
            Color green = Color.decode("#64DF58");
            button.setBackground(green);
        } else
            Main.showMessage("Error.\nRight answer not found.");
    }

    private JButton getButtonWithRightAnswer() {
        JButton[] jButtons = getJButtonArray();
        for (JButton j : jButtons) {
            String buttonText = j.getText();
            if (currentQuestion.isRightAnswer(buttonText))
                return j;
        }
        return null;
    }

    JFrame getjFrame() {
        return jFrame;
    }

}