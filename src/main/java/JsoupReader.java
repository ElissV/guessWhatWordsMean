import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    static WordForQuestion getWordObject() {
        String word = getWord();
        String definition = getDefinition(word);
        WordForQuestion wordObj = new WordForQuestion(word, definition);
        while (wordObj.wordOrDescrIsEmpty()) {
            wordObj = getWordObject();
        }
        return wordObj;
    }

    private static String getWord() {
        Document doc = readPage(URLS[0]);
        if (doc != null) {
            Elements elements = doc.getElementsByClass(ELEMENT_WITH_WORD[0]);
            Element element = elements.select(ELEMENT_WITH_WORD[1]).first();
            String word = element.ownText();
            return firstCharToUpperCase(word);
        }
        return null;
    }

    private static Document readPage(String url) {
        try {
            Connection con = Jsoup.connect(url).timeout(2000);
            Connection.Response resp = con.execute();
            if (resp.statusCode() == 200) {
                return con.get();
            }
        } catch(IOException e){
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

    static List<String> getOtherOptionsForAnswer() {
        int optionsNeeded = Question.getAnswerOptions();
        List<String> options = new ArrayList<String>();
        for (int i=0; i<optionsNeeded; i++) {
            String word = getWord();
            options.add(getDefinition(word));
        }
        return options;
    }

}