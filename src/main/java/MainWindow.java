import javax.swing.*;

public class MainWindow {

    private static GameForm gameForm;
    private Question question;
    private static final int QUESTIONS_QTY = 10;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    public static void main(String[] args) {
        gameForm = new GameForm();
        askQuestion();
    }

    void askQuestion() {
        question = new Question();
    }

    static JFrame getGameFormFrame() {
        return gameForm.getjFrame();
    }

}
