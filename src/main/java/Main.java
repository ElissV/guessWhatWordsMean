import javax.swing.*;

public class Main {

    private static GameForm gameForm;
    private static Question question;
    private static Question nextQuestion;
    private static final int QUESTIONS_QTY = 10;
    private static int questionsAnswered = 0;
    private static int rightAnswersGiven = 0;

    public static void main(String[] args) {
        gameForm = new GameForm();
        askQuestion();
    }

    void showErrorMessage(Exception e) {
        String message = e.getClass() + "\n" + e.getMessage();
        JOptionPane.showMessageDialog(gameForm.getjFrame(), message);
        e.printStackTrace();
    }

    private static void askQuestion() {
        question = new Question();
        gameForm.showQuestion(question);
    }

    static int getQuestionsQty() {
        return QUESTIONS_QTY;
    }

    static Question getQuestion() {
        return question;
    }
}
