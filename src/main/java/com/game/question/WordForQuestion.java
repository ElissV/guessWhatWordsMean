package com.game.question;

public class WordForQuestion {

    private String word;
    private String definition;


    public WordForQuestion(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    String getWord() {
        return word;
    }

    String getRightDefinition() {
        return definition;
    }

}