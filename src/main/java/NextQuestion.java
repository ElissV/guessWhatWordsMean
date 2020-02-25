import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class NextQuestion extends Question {

    private PageReader pageReader;
    private WordForQuestion word;
    private List<String> optionsForAnswer;

    NextQuestion() {
        pageReader = new PageReader();
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> task = service.submit(new NextQuestionReader());
    }

    class NextQuestionReader implements Callable<String> {
        @Override
        public String call() {
            return pageReader.getArray();
        }
    }

}
