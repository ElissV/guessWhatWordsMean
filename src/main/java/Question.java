import java.util.List;

class Question {

    private WordForQuestion word;
    private static final int OTHER_ANSWER_OPTIONS = 3;
    private List<String> optionsForAnswer;


    Question() {
        word = JsoupReader.getWordObject();
        optionsForAnswer = JsoupReader.getOtherOptionsForAnswer();
        optionsForAnswer.add(word.getDefinition());

        for (String g : optionsForAnswer) {
            System.out.println(g);
        }
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