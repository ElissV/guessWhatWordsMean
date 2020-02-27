import java.util.List;

abstract class Question {

    static final int OTHER_ANSWER_OPTIONS = 3;
    PageReader pageReader;
    WordForQuestion word;
    List<String> optionsForAnswer;

    boolean isRightAnswer(String answer) {
        return answer.equals(word.getDefinition());
    }

    List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    static int getAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }

    WordForQuestion getWord() {
        return word;
    }

}