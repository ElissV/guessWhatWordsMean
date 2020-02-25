import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Game {

    private GameForm gameForm;
    private Question question;
    private Question nextQuestion;
    private final int QUESTIONS_QTY = 10;
    private int questionsAnswered = 0;
    private int rightAnswersGiven = 0;

    Game (GameForm gameForm) {
        this.gameForm = gameForm;
        askQuestion();
    }

    private void askQuestion() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> task    = service.submit(new NextQuestionReader());
        System.out.println(task);
        question = new Question();
        question.createQuestion();
        gameForm.showQuestion(question);
    }

    class NextQuestionReader implements Callable<String> {
        @Override
        public String call() {
            PageReader reader = new PageReader();
            return reader.getArray();
        }
    }

    Question getQuestion() {
        return question;
    }

    int getQUESTIONS_QTY() {
        return QUESTIONS_QTY;
    }

}