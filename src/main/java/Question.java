import java.util.Collections;
import java.util.List;

class Question {

    private PageReader pageReader;
    private WordForQuestion word;
    private static final int OTHER_ANSWER_OPTIONS = 3;
    private List<String> optionsForAnswer;


    void createQuestion() {
        pageReader = new PageReader();
        word = pageReader.getWordObject();
        optionsForAnswer = getOptions();
        System.out.println(word.getWord() + " | " + word.getDefinition());
    }

    private List<String> getOptions() {
        List<String> options = pageReader.getOtherOptionsForAnswer();
        options.add(word.getDefinition());
        Collections.shuffle(options);
        return options;
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