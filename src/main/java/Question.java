import java.util.Collections;
import java.util.List;

class Question {

    private WordForQuestion word;
    private static final int OTHER_ANSWER_OPTIONS = 3;
    private List<String> optionsForAnswer;


    Question() {
        word = PageReader.getWordObject();
        optionsForAnswer = getOptions();
    }

    private List<String> getOptions() {
        List<String> options = PageReader.getOtherOptionsForAnswer();
        optionsForAnswer.add(word.getDefinition());
        Collections.shuffle(optionsForAnswer);
        return options;
    }

    List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    WordForQuestion getWord() {
        return word;
    }

    static int getAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }

}