import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

class PageReader {

    private static final String URL = "https://www.randomlists.com/random-vocabulary-words";
    private static String[] wordsAndDefinitions =getPageContent();


    static WordForQuestion getWordObject() {
        String word = wordsAndDefinitions[0];
        String definition = wordsAndDefinitions[1];
        return new WordForQuestion(word, definition);
    }

    private static String[] getPageContent() {
        System.setProperty("webdriver.chrome.driver","D:\\Software\\Chrome_download\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("headless");
        WebDriver driver = new ChromeDriver(options);
        driver.get(URL);
        List<WebElement> elements = null;
        while (elements == null) {
            elements = driver.findElements(By.className("Rand-stage"));
        }
        List<String> str = new ArrayList<String>();

        for (WebElement w : elements) {
            str.add(w.getText());
        }
        driver.quit();

        return str.get(0).split("\n");
    }

    private static String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    static List<String> getOtherOptionsForAnswer() {
        List<String> options = new ArrayList<String>();
        int optionsNeeded = Question.getAnswerOptions();
        for (int i=0; i<optionsNeeded; i++) {
            WordForQuestion word = getWordObject();
            options.add(word.getDefinition());
        }
        return options;
    }

}