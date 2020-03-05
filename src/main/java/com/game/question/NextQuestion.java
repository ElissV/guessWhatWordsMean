package com.game.question;

public class NextQuestion extends Question {

    private boolean questionIsBeingCreated;

    NextQuestion() {
        questionIsBeingCreated = true;
        createQuestion();
    }

    @Override
    protected void createQuestion() {
        super.createQuestion();
        questionIsBeingCreated = false;
    }

    boolean questionIsNotBeingCreated() {
        return !questionIsBeingCreated;
    }

}