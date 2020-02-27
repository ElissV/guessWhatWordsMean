import java.util.Collections;
import java.util.List;

abstract class Question {

    static final int OTHER_ANSWER_OPTIONS = 3;
    PageReader pageReader;
    WordForQuestion questionWord;
    List<String> optionsForAnswer;


    void createQuestion() {
        pageReader = new PageReader();
        questionWord = pageReader.getWordObject();
        optionsForAnswer = getOptions();
    }

    private List<String> getOptions() {
        List<String> options = pageReader.getOtherOptionsForAnswer();
        options.add(questionWord.getDefinition());
        Collections.shuffle(options);
        return options;
    }

   /* void createQuestion() {
        String[] valuesArray = getValuesArray();
        createWord(valuesArray);
        System.out.println(this.getClass() + " created!!!");
    }*/

    /*void createWord(String[] values) {
        String word = values[0];
        //String description = values[1];
        questionWord = new WordForQuestion(word, "d");
        System.out.println(this.getClass() + " " + word + " ");
    }

    String[] getValuesArray() {
        String values = getAllValues();
        System.out.println(this.getClass() + " values " + values);
        return values.split("/");
    }*/

    //abstract String getAllValues();

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