package com.game.question;

import com.game.webpage.WebPageContentHandler;

import java.util.Collections;
import java.util.List;

public abstract class Question {

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

    public boolean isRightAnswer(String answer) {
        String rightAnswer = wordForQuestion.getRightDefinition();
        return answer.equals(rightAnswer);
    }

    public List<String> getOptionsForAnswer() {
        return optionsForAnswer;
    }

    public String getWordForQuestion() {
        return wordForQuestion.getWord();
    }

}