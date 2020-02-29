import java.util.Collections;
import java.util.List;

class Question {

    static final int OTHER_ANSWER_OPTIONS = 3;
    PageReader pageReader;
    WordForQuestion question;
    List<String> optionsForAnswer;
    private boolean questionIsBeingCreated = false;


    Question() {
        questionIsBeingCreated = true;
        System.out.println("Creating question");
        createQuestion();
    }

    private void createQuestion() {
        pageReader = new PageReader();
        question = pageReader.getWordObject();
        optionsForAnswer = createOptions();
        System.out.println("Created");
        questionIsBeingCreated = false;
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

    public boolean questionIsBeingCreated() {
        return questionIsBeingCreated;
    }

    public void set_isBeingCreated(boolean questionIsBeingCreated) {
        this.questionIsBeingCreated = questionIsBeingCreated;
    }

    static int getAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }

    WordForQuestion getQuestion() {
        return question;
    }

}