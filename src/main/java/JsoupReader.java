import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;

class JsoupReader {

    private static final String[] URLS;
    private static final String[] ELEMENT_WITH_WORD;
    private static final String[] ELEMENT_WITH_DEFINITION;

    static {
        URLS = new String[]{"https://www.generatormix.com/random-noun-generator",
                "https://www.merriam-webster.com/dictionary/"};
        ELEMENT_WITH_WORD = new String[] {"text-center", "h3"};
        ELEMENT_WITH_DEFINITION = new String[]{"dtText", "span"};
    }

    static String[] getWordAndDefinition() {
        String[] valuesArray = new String[2];
        valuesArray[0] = getWord();
        valuesArray[1] = getDefinition(valuesArray[0]);
        while (someValueIsEmpty(valuesArray)) {
            valuesArray = getWordAndDefinition();
        }
        return valuesArray;
    }

    private static boolean someValueIsEmpty(String[] wordAndDefinition) {
        return wordAndDefinition[0].isEmpty() ||
                wordAndDefinition[1].isEmpty();
    }

    private static String getWord() {
        Document doc = readPage(URLS[0]);
        String word = "";
        if (doc != null) {
            Elements elements = doc.getElementsByClass(ELEMENT_WITH_WORD[0]);
            Element element = elements.select(ELEMENT_WITH_WORD[1]).first();
            word = element.ownText();
        }
        return firstCharToUpperCase(word);
    }

    private static Document readPage(String url) {
        try {
            return Jsoup.parse(new URL(url), 7000);
        } catch (IOException e) {
            ErrorMessage.showErrorMessage(e);
        }
        return null;
    }

    private static String firstCharToUpperCase(String word) {
        int lastCharIndex = word.length();
        String upperCaseChar = word.substring(0,1).toUpperCase();
        return upperCaseChar + word.substring(1, lastCharIndex);
    }

    private static String getDefinition(String word) {
        String url = URLS[1] + word;
        Document doc = readPage(url);
        String result = "";
        if (doc != null) {
            Elements elements = doc.getElementsByClass(ELEMENT_WITH_DEFINITION[0]);
            Element element = elements.select(ELEMENT_WITH_DEFINITION[1]).first();
            result = element.ownText();
        }
        return result;
    }

    static String[] getOtherOptionsForAnswer() {
        int optionsNeeded = Question.getOtherAnswerOptions();
        String[] options = new String[optionsNeeded];
        for (int i=0; i<optionsNeeded; i++) {
            String word = getWord();
            options[i] = getDefinition(word);
        }
        return options;
    }

}