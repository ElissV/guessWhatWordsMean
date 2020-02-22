class Question {

    private static final int OTHER_ANSWER_OPTIONS = 3;
    private String chosenWord;
    private String chosenWordDefeinition;
    private String[] otherOptionsForAnswer;


    Question() {
        String[] wordAndDefinition = JsoupReader.getWordAndDefinition();
        chosenWord = wordAndDefinition[0];
        chosenWordDefeinition = wordAndDefinition[1];
        otherOptionsForAnswer = JsoupReader.getOtherOptionsForAnswer();
    }

    static int getOtherAnswerOptions() {
        return OTHER_ANSWER_OPTIONS;
    }
}
