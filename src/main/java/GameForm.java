import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class GameForm {

    private Game game;
    //private boolean waitsForQuestion = false;

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

    void showQuestion() {
        if (game.getQuestionsAnswered() == 0)
            addListeners();
        setQuestionLabelText();
        setButtonsText();
    }

    private void setQuestionLabelText() {
        //setScoreLabelText();
        String word = getQuestion().getWord();
        questionLabel.setText(word);
    }

    private void setScoreLabelText() {
        int rightAnswers = game.getRightAnswersGiven();
        int questionsShown = game.getQuestionsAnswered();
        scoreLabel.setText(rightAnswers + "/" + questionsShown);
    }

    private void addListeners() {
        JButton[] jButtons = getJButtonArray();
        for (JButton button : jButtons) {
            button.addActionListener(listener);
            button.addKeyListener(keyListener);
        }
    }

    private void setButtonsText() {
        Question q = getQuestion();
        List<String> options = q.getOptionsForAnswer();
        JButton[] jButtons = getJButtonArray();
        int i = 0;
        Color defaultColor = new Color(230,232,226);
        for (JButton button : jButtons) {
            button.setText(options.get(i));
            button.setBackground(defaultColor);
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
        if (game.programWaitsForAnswer()) {
            System.out.println("SSSS");
            String answer = e.getActionCommand();
            JButton clicked = (JButton) e.getSource();
            Question q = getQuestion();
            if (q.isRightAnswer(answer)) {
                Color green = Color.decode("#64DF58");
                clicked.setBackground(green);
            } else {
                Color red = Color.decode("#DC3232");
                clicked.setBackground(red);
                showRightAnswer();
            }
            game.gotAnswer(answer);
            setScoreLabelText();
            game.setWaitsForAnswerFalse();
        }
    };

    private KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode()==KeyEvent.VK_ENTER){
                game.askQuestion();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
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
        Question q = getQuestion();
        for (JButton j : jButtons) {
            String buttonText = j.getText();
            if (q.isRightAnswer(buttonText))
                return j;
        }
        return null;
    }

    private Question getQuestion() {
        return game.getCurrentQuestion();
    }

    void setGameAndQuestion(Game game) {
        this.game = game;
    }

    JFrame getjFrame() {
        return jFrame;
    }

    String getQuestionLabelText() {
        return questionLabel.getText();
    }

    void wordIsLoading() {
        questionLabel.setText("Loading...");
    }

    /*public void setWaitsForQuestion(boolean waitsForQuestion) {
        this.waitsForQuestion = waitsForQuestion;
    }

    public boolean waitsForQuestion() {
        return waitsForQuestion;
    }*/
}