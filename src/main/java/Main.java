import javax.swing.*;

public class Main {

    private static GameForm gameForm;
    private static Game game;

    public static void main(String[] args) {
        gameForm = new GameForm();
        game = new Game(gameForm);
        showInitialMessage();
        game.askFirstQuestion();
    }

    private static void showInitialMessage() {
        Runnable questionCreatorRunnable = () ->
                showMessage("In this game you have to guess the meaning of 10 words.\n" +
                        "Press ENTER to go to next question after answering.");
        new Thread(questionCreatorRunnable).start();
    }

    static void showMessage(String msg) {
        JOptionPane.showMessageDialog(gameForm.getjFrame(), msg);
    }

    static int getQuestionsQTY() {
        return game.getQUESTIONS_QTY();
    }

    static Question getQuestion() {
        return game.getQuestion();
    }

}