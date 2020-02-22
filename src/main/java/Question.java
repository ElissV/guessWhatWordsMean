public class Question {

    private static final int OTHER_ANSWER_OPTIONS = 3;
    private String chosenWord;
    private String chosenWordDefeinition;
    private String[] otherOptionsForAnswer;


    Question() {
        String[] wordAndDefinition =
                JsoupReader.getWordAndDefinition();
        System.out.println("|" + wordAndDefinition[0] + "| A |" + wordAndDefinition[1] + "|");
    }

}
