class WordForQuestion {

    private String word;
    private String definition;


    WordForQuestion(String word, String definition) {
        this.word = word;
        this.definition = definition;
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

    boolean wordOrDescrIsEmpty() {
        if (word != null || definition != null)
            return word.isEmpty() || definition.isEmpty();
        else
            return true;
    }

    String getWord() {
        return word;
    }

    String getDefinition() {
        return definition;
    }
}
