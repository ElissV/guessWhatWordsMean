import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

class NextQuestion extends Question {

    NextQuestion() {
        Runnable inputHandlerRunnable = this::createQuestion;
        new Thread(inputHandlerRunnable).start();
    }

    //@Override
    //String getAllValues() {
        /*pageReader = new PageReader();
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> task = service.submit(new NextQuestionReader());
        return task.toString();*/



    //}

    /*class NextQuestionReader implements Callable<String> {
        @Override
        public String call() {
            String values = pageReader.getValues();
            return values;
        }
    }*/

}