class NextQuestion extends Question {

    NextQuestion() {
        Runnable inputHandlerRunnable = this::createQuestion;
        new Thread(inputHandlerRunnable).start();
    }

}