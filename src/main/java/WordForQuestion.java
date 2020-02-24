class WordForQuestion {

    private String word;
    private String definition;


    WordForQuestion(String word, String definition) {
        this.word = word;
        this.definition = definition;
        System.out.println("> " + definition);
    }

    boolean equalsWordsDefinition(String definition) {
        return this.definition.equals(definition);
    }

    void setWord(String word) {
        this.word = word;
    }

    void setDefinition(String definition) {
        this.definition = definition;
    }

    boolean wordOrDescriptionNotCorrect() {
        if (word != null || definition != null)
            if (!(word.isEmpty()) || !(definition.isEmpty()))
                return definitionNotShort();
        return true;
    }

    private boolean definitionNotShort() {
        int minDefinitionLen = 8;
        return definition.length() >= minDefinitionLen;
    }

    String getWord() {
        return word;
    }

    String getDefinition() {
        return definition;
    }

}