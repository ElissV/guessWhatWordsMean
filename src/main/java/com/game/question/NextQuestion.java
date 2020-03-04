package com.game.question;

public class NextQuestion extends Question {

    private boolean questionIsBeingCreated;

    public NextQuestion() {
        questionIsBeingCreated = true;
        createQuestion();
    }

    @Override
    protected void createQuestion() {
        super.createQuestion();
        questionIsBeingCreated = false;
    }

    public boolean questionIsNotBeingCreated() {
        return !questionIsBeingCreated;
    }

}