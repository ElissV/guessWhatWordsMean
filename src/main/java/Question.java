import java.util.Collections;
import java.util.List;

class Question {

    static final int OTHER_ANSWER_OPTIONS = 3;
    PageReader pageReader;
    WordForQuestion questionWord;
    List<String> optionsForAnswer;


    Question() {
        createQuestion();
    }

    void createQuestion() {
        pageReader = new PageReader();
        questionWord = pageReader.getWordObject();
        optionsForAnswer = getOptions();
        System.out.println("Created");
    }

    private List<String> getOptions() {
        List<String> options = pageReader.getOtherOptionsForAnswer();
        options.add(questionWord.getDefinition());
        Collections.shuffle(options);
        return options;
    }

    boolean isRightAnswer(String answer) {
        return answer.equals(questionWord.getDefinition());
    }

    List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    static int getAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }

    WordForQuestion getQuestionWord() {
        return questionWord;
    }

}