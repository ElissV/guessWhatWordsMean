import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameForm {

    private GameProcess game;

    private JFrame jFrame;
    private JPanel panel1;
    private JSplitPane splitPane;
    private JButton[] jButtons;
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

    void setupFormForStart() {
        jButtons = getButtonArray();
        setLabelText(questionLabel, "Loading...");
        setLabelText(scoreLabel, "0/0");
        Color defaultColor = new Color(230,232,226);
        for (JButton button : jButtons) {
            button.setText("option");
            button.setBackground(defaultColor);
            button.paintImmediately(button.getVisibleRect());
        }
    }

    private void setLabelText(JLabel label, String text) {
        label.setText(text);
        label.paintImmediately(label.getVisibleRect());
    }

    void showFirstQuestion() {
        addListeners();
        showQuestion();
    }

    void showQuestion() {
        setQuestionLabelText();
        setButtonsText();
    }

    private void setQuestionLabelText() {
        String word = getQuestion().getWordForQuestion();
        questionLabel.setText(word);
    }

    private void addListeners() {
        ButtonClickListener buttonListener =
                new ButtonClickListener(game, this);
        EnterKeyListener enterListener = new EnterKeyListener(game);
        for (JButton button : jButtons) {
            button.addActionListener(buttonListener);
            button.addKeyListener(enterListener);
        }
    }

    private void setButtonsText() {
        Question q = getQuestion();
        List<String> options = q.getOptionsForAnswer();
        int i = 0;
        Color defaultColor = new Color(230,232,226);
        for (JButton button : jButtons) {
            button.setText(options.get(i));
            button.setBackground(defaultColor);
            i++;
        }
    }

    JButton[] getButtonArray() {
        JButton[] jButtons = new JButton[4];
        jButtons[0] = button1;
        jButtons[1] = button2;
        jButtons[2] = button3;
        jButtons[3] = button4;
        return jButtons;
    }

    private Question getQuestion() {
        return game.getCurrentQuestion();
    }

    void setGame(GameProcess game) {
        this.game = game;
    }

    JFrame getjFrame() {
        return jFrame;
    }

    void setScoreLabel(String value) {
        scoreLabel.setText(value);
    }

    String getQuestionLabelText() {
        return questionLabel.getText();
    }

    void wordIsLoading() {
        questionLabel.setText("Loading...");
    }

}