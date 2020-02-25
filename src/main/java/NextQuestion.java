import java.util.List;

public class NextQuestion extends Question {

    private PageReader pageReader;
    private WordForQuestion word;
    private List<String> optionsForAnswer;

    NextQuestion() {
        pageReader = new PageReader();
        //word = pageReader.getNextWordObject();
        System.out.println(word.getWord() + " 2|2 " + word.getDefinition());
    }

}
