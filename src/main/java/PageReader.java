import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.ArrayList;
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
        List<String> options = new ArrayList<String>();
        int optionsNeeded = Question.getAnswerOptions();
        for (int i=0; i<optionsNeeded; i++) {
            WordForQuestion word = getWordObject();
            options.add(word.getDefinition());
        }
        return options;
    }

}