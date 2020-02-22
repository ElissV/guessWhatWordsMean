import javax.swing.*;

public class MainWindow {

    private static GameForm gameForm;
    private static Question question;
    private static final int QUESTIONS_QTY = 10;
    private static int questionsAnswered = 0;
    private static int rightAnswersGiven = 0;

    public static void main(String[] args) {
        gameForm = new GameForm();
        askQuestion();
    }

    private static void askQuestion() {
        question = new Question();
    }

    static JFrame getGameFormFrame() { // Move ErrorMessage to Main ???
        return gameForm.getjFrame();
    }

    public static int getQuestionsQty() {
        return QUESTIONS_QTY;
    }
}
