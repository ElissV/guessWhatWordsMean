class Question {

    private WordForQuestion word;
    private static final int OTHER_ANSWER_OPTIONS = 3;
    private String[] otherOptionsForAnswer;


    Question() {
        word = JsoupReader.getWordObject();
        otherOptionsForAnswer = JsoupReader.getOtherOptionsForAnswer();
    }

    String[] getOtherOptionsForAnswer() {
        return otherOptionsForAnswer;
    }

    WordForQuestion getWord() {
        return word;
    }

    static int getOtherAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }
}