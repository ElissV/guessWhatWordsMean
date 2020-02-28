import java.util.Collections;
import java.util.List;

class Question {

    static final int OTHER_ANSWER_OPTIONS = 3;
    PageReader pageReader;
    WordForQuestion question;
    List<String> optionsForAnswer;


    Question() {
        createQuestion();
    }

    void createQuestion() {
        pageReader = new PageReader();
        question = pageReader.getWordObject();
        optionsForAnswer = createOptions();
        System.out.println("Created");
    }

    private List<String> createOptions() {
        List<String> options = pageReader.getOtherOptionsForAnswer();
        options.add(question.getDefinition());
        Collections.shuffle(options);
        return options;
    }

    boolean isRightAnswer(String answer) {
        return answer.equals(question.getDefinition());
    }

    List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    public String getWord() {
        return question.getWord();
    }

    static int getAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }

    WordForQuestion getQuestion() {
        return question;
    }

}