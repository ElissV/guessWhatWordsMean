import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PageReader {

    private String[] wordsAndDefinitions;


    WordForQuestion getWordObject() {
        System.out.println("DDDDDDD");
        wordsAndDefinitions = getWordsAndDefsArray();
        String word = wordsAndDefinitions[0];
        String definition = wordsAndDefinitions[1];
        return new WordForQuestion(word, definition);
    }

    private String[] getWordsAndDefsArray() {
        WebDriver driver = getDriver();
        return getValuesArray(driver);
    }

    String getValues() {
        WebDriver driver = getDriver();
        String[] stringArray = getValuesArray(driver);
        StringBuilder sb = new StringBuilder();
        for (String aStringArray : stringArray) {
            sb.append(aStringArray).append("/");
        }
        System.out.println(sb);
        driver.quit();
        return sb.toString();
    }

    private WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Software\\Chrome_download\\chromedriver\\chromedriver.exe");
        String url = "https://www.randomlists.com/random-vocabulary-words";
        ChromeOptions options = getChromeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    private ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        return options;
    }

    private String[] getValuesArray(WebDriver driver) {
        List<WebElement> elements = getPageContent(driver);
        int elementsTotal = 10;
        String[] elementStr = new String[elementsTotal];
        int i = 0;
        for (WebElement w : elements) {
            String str = firstCharToUpperCase(w.getText());
            elementStr[i] = str;
            i++;
        }
        driver.quit();
        return elementStr[0].split("\n");
    }

    private List<WebElement> getPageContent(WebDriver driver) {
        List<WebElement> elements = null;
        while (elements == null) {
            elements = driver.findElements(By.className("Rand-stage"));
        }
        return elements;
    }

    private String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    List<String> getOtherOptionsForAnswer() {
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