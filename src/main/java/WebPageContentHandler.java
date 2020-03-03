import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class WebPageContentHandler {

    private String[] wordsAndDefinitions;


    WordForQuestion getWordObject() {
        wordsAndDefinitions = WebPageReader.getValuesArray();
        String word = wordsAndDefinitions[0];
        String definition = wordsAndDefinitions[1];
        return new WordForQuestion(word, definition);
    }

    List<String> getWrongOptionsForAnswer() {
        List<String> array = removeWordAndDefinitionForQuestion();
        return leaveOnlyThreeDefinitions(array);
    }

    private List<String> removeWordAndDefinitionForQuestion() {
        List<String> list = new ArrayList<>(Arrays.asList(wordsAndDefinitions));
        int wordForQuestionIndex = 0, definitionIndex = 1;
        list.remove(definitionIndex);
        list.remove(wordForQuestionIndex);
        return list;
    }

    private static List<String> leaveOnlyThreeDefinitions(List<String> list) {
        List<String> definitions = new ArrayList<>();
        int index = 0;
        for (String s : list) {
            if (index%2 != 0)
                definitions.add(s);
            index++;
        }
        int forthDefinitionIndex = definitions.size()-1;
        definitions.remove(forthDefinitionIndex);
        return definitions;
    }

}