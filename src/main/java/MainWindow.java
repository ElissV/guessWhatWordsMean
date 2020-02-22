import javax.swing.*;

public class MainWindow {

    private static GameForm gameForm;

    public static void main(String[] args) {
        gameForm = new GameForm();
        new Game().askQuestion();
    }

    static JFrame getGameFormFrame() {
        return gameForm.getjFrame();
    }

}
