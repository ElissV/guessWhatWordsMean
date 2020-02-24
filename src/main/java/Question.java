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
        options.add(word.getDefinition());
        Collections.shuffle(options);
        return options;
    }

    class NextQuestion extends Question {
        NextQuestion() {
            word = PageReader.getWordObject();
            optionsForAnswer = getOptions();
        }
    }

    boolean isRightAnswer(String answer) {
        return answer.equals(word.getDefinition());
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