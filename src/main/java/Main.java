import javax.swing.*;

public class Main {

    private static Game game;
    private static GameForm gameForm;


    public static void main(String[] args) {
        gameForm = new GameForm();
        game = new Game(gameForm);
    }

    void showErrorMessage(Exception e) {
        String message = e.getClass() + "\n" + e.getMessage();
        JOptionPane.showMessageDialog(gameForm.getjFrame(), message);
        e.printStackTrace();
    }

    static int getQuestionsQTY() {
        return game.getQUESTIONS_QTY();
    }

    static Question getQuestion() {
        return game.getQuestion();
    }

}