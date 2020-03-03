import java.util.Collections;
import java.util.List;

abstract class Question {

    private WebPageContentHandler reader;
    private WordForQuestion wordForQuestion;
    private List<String> optionsForAnswer;


    protected void createQuestion() {
        reader = new WebPageContentHandler();
        wordForQuestion = reader.getWordObject();
        optionsForAnswer = createOptionsForAnswer();
    }

    private List<String> createOptionsForAnswer() {
        List<String> options = reader.getWrongAnswerOptions();
        options.add(wordForQuestion.getRightDefinition());
        Collections.shuffle(options);
        return options;
    }

    boolean isRightAnswer(String answer) {
        String rightAnswer = wordForQuestion.getRightDefinition();
        return answer.equals(rightAnswer);
    }

    List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    String getWordForQuestion() {
        return wordForQuestion.getWord();
    }

}