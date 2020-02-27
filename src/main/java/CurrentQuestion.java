import java.util.Collections;
import java.util.List;

class CurrentQuestion extends Question {

    CurrentQuestion() {
        createQuestion();
    }

    String getAllValues() {
        pageReader = new PageReader();
        return pageReader.getValues();
    }




}