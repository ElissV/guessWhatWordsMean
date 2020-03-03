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

    private List<String> wordsAndDefinitions;


    WordForQuestion getWordObject() {
        wordsAndDefinitions = WebPageReader.getValuesArray();
        String word = wordsAndDefinitions.get(0);
        String definition = wordsAndDefinitions.get(1);
        return new WordForQuestion(word, definition);
    }

    List<String> getWrongAnswerOptions() {
        List<String> array = removeWordAndDefinitionForQuestion();
        return leaveOnlyThreeDefinitions(array);
    }

    private List<String> removeWordAndDefinitionForQuestion() {
        List<String> list = new ArrayList<>();
        for (String s : wordsAndDefinitions) {
            if (isNotWordOrRightAnswerForQuestion(s))
                list.add(s);
        }
        return list;
    }

    private boolean isNotWordOrRightAnswerForQuestion(String s) {
        return wordsAndDefinitions.indexOf(s) != 0 &&
                wordsAndDefinitions.indexOf(s) != 1;
    }

    private static List<String> leaveOnlyThreeDefinitions(List<String> list) {
        List<String> definitions = new ArrayList<>();
        int index = 0;
        for (String s : list) {
            if (isDefinition(index))
                definitions.add(s);
            index++;
        }
        return removeForthDefinition(definitions);
    }

    private static boolean isDefinition(int index) {
        return index%2 != 0;
    }

    private static List<String> removeForthDefinition(List<String> definitions) {
        int forthDefinitionIndex = definitions.size()-1;
        definitions.remove(forthDefinitionIndex);
        return definitions;
    }

}