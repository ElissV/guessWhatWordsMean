import javax.swing.*;

class GameStart {

    private static GameForm gameForm;
    private static GameProcess game;

    static void startFirstGame() {
        gameForm = new GameForm();
        showInitialMessage();
        startGame();
    }

    static void startGame() {
        gameForm.setupFormForStart();
        game = new GameProcess(gameForm);
        game.askFirstQuestion();
    }

    private static void showInitialMessage() {
        Runnable questionCreatorRunnable =
                GameStart::showOptionPaneWithDescription;
        new Thread(questionCreatorRunnable).start();
    }

    private static void showOptionPaneWithDescription() {
        String message = "In this game you have to guess the meaning of 10 words.\n" +
                            "Press ENTER to go to next question after answering.";
        JOptionPane.showMessageDialog(gameForm.getjFrame(), message);
    }

}