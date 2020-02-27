import java.util.Collections;
import java.util.List;

class CurrentQuestion extends Question {

    /*@Override
    protected void getQuestion() {
        pageReader = new PageReader();
        word = pageReader.getWordObject();
        optionsForAnswer = getOptions();
    }*/

    private List<String> getOptions() {
        List<String> options = pageReader.getOtherOptionsForAnswer();
        options.add(word.getDefinition());
        Collections.shuffle(options);
        return options;
    }

    
}