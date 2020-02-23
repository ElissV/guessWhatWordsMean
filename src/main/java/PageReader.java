import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PageReader {

    private static String[] wordsAndDefinitions = getWordsAndDefsArray();


    static WordForQuestion getWordObject() {
        String word = wordsAndDefinitions[0];
        String definition = wordsAndDefinitions[1];
        return new WordForQuestion(word, definition);
    }

    private static String[] getWordsAndDefsArray() {
        WebDriver driver = getDriver();
        return getValuesArray(driver);
    }

    private static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver",
                "D:\\Software\\Chrome_download\\chromedriver_win32\\chromedriver.exe");
        String url = "https://www.randomlists.com/random-vocabulary-words";
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(url);
        return driver;
    }

    private static String[] getValuesArray(WebDriver driver) {
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

    private static List<WebElement> getPageContent(WebDriver driver) {
        List<WebElement> elements = null;
        while (elements == null) {
            elements = driver.findElements(By.className("Rand-stage"));
        }
        return elements;
    }

    private static String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    static List<String> getOtherOptionsForAnswer() {
        List<String> array = getArrayWithOtherAnswers();
        return leaveOnlyDefinitions(array);
    }

    private static List<String> getArrayWithOtherAnswers() {
        List<String> list = new ArrayList<>(Arrays.asList(wordsAndDefinitions));
        int wordForQuestionIndex = 0, definitionIndex = 1;
        list.remove(wordForQuestionIndex);
        list.remove(definitionIndex);
        return list;
    }

    private static List<String> leaveOnlyDefinitions(List<String> list) {
        for (int i=0; i<list.size(); i++) {
            String str = list.get(i);
            if (isNotDefinition(list, str))
                list.remove(str);
        }
        return list;
    }

    private static boolean isNotDefinition(List<String> list, String s) {
        return list.indexOf(s)%2 != 0;
    }

}