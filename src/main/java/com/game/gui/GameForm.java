package com.game.gui;

import com.game.GameProcess;
import com.game.gui.listeners.ButtonClickListener;
import com.game.gui.listeners.EnterKeyListener;
import com.game.question.Question;

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

    public GameForm() {
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

    public void setupFormForStart() {
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

    public void showFirstQuestion() {
        addListeners();
        showQuestion();
    }

    public void showQuestion() {
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
        Question question = getQuestion();
        List<String> options = question.getOptionsForAnswer();
        int i = 0;
        Color defaultColor = new Color(230,232,226);
        for (JButton button : jButtons) {
            button.setText(options.get(i));
            button.setBackground(defaultColor);
            i++;
        }
    }

    public JButton[] getButtonArray() {
        JButton[] jButtons = new JButton[4];
        jButtons[0] = button1;
        jButtons[1] = button2;
        jButtons[2] = button3;
        jButtons[3] = button4;
        return jButtons;
    }

    private Question getQuestion() {
        return game.getCreator().getCurrentQuestion();
    }

    public void setGame(GameProcess game) {
        this.game = game;
    }

    public JFrame getjFrame() {
        return jFrame;
    }

    public void setScoreLabel(String value) {
        scoreLabel.setText(value);
    }

    public String getQuestionLabelText() {
        return questionLabel.getText();
    }

    public void wordIsLoading() {
        questionLabel.setText("Loading...");
    }

}