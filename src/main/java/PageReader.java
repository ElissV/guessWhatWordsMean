import org.jsoup.Connection;
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

    private static final String URL = "https://www.randomlists.com/random-vocabulary-words";;
    private static final String[] ELEMENT_WITH_WORD;
    private static final String[] ELEMENT_WITH_DEFINITION;
    private static Connection connectionURL1;
    private static Connection connectionURL2;
    private static String[] wordsAndDefinitions =getPageContent();

    static {
        ELEMENT_WITH_WORD = new String[] {"text-center", "h3"};
        ELEMENT_WITH_DEFINITION = new String[]{"dtText", "span"};
    }

    static WordForQuestion getWordObject() {
        //String word = getWord();
        //String definition = getDefinition(word);
        /*WordForQuestion wordObj = new WordForQuestion("ddddddddd", "ddddddddd");
        while (wordObj.wordOrDescriptionNotCorrect()) {
            wordObj = getWordObject();
            System.out.println("getWord");
        }*/
        String word = wordsAndDefinitions[0];
        String definition = wordsAndDefinitions[1];
        return new WordForQuestion(word, definition);
    }

    private static String[] getPageContent() {
        /*Document doc = readPage(URLS[0]);
        if (doc != null) {
            Elements elements = doc.getElementsByClass(ELEMENT_WITH_WORD[0]);
            Element element = elements.select(ELEMENT_WITH_WORD[1]).first();
            String word = element.ownText();
            return firstCharToUpperCase(word);
        }*/
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

    /*private static Document readPage(String url) {
        try {
            Connection con = Jsoup.connect(url).timeout(10000);
            return con.get();
            /*Connection.Response resp = con.execute();
            if (resp.statusCode() == 200) {
                return con.get();
            }
        } catch(IOException e){
            ErrorMessage.showErrorMessage(e);
        }
        return null;
    }*/

    private static String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    /*private static String getDefinition(String word) {
        String url = URLS[1] + word;
        Document doc = readPage(url);
        String result = "";
        if (doc != null) {
            Elements elements = doc.getElementsByClass(ELEMENT_WITH_DEFINITION[0]);
            Element element = elements.select(ELEMENT_WITH_DEFINITION[1]).first();
            result = element.ownText();
        }
        return result;
    }*/

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