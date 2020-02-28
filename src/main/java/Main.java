import javax.swing.*;

public class Main {

    private static GameForm gameForm;
    private static Game game;

    public static void main(String[] args) {
        gameForm = new GameForm();
        game = new Game(gameForm);
        game.askQuestion();
    }

    public static void showMessage(String msg) {
        JOptionPane.showMessageDialog(gameForm.getjFrame(), msg);
    }

    static int getQuestionsQTY() {
        return game.getQUESTIONS_QTY();
    }

    static Question getQuestion() {
        return game.getQuestion();
    }

}