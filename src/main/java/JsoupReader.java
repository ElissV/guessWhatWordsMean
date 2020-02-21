import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.net.URL;

public class JsoupReader {

    private static final String CLASS_WITH_WORD;
    private static final String CLASS_WITH_DESCRIPTION;

    static {
        CLASS_WITH_WORD = "support";
        CLASS_WITH_DESCRIPTION = "subtle";
    }

    String[] getWordAndDefinition() {
        String url = "https://www.dictionary.com/browse/dog";
        Document page = Jsoup.parse(new URL(url), 3000);
    }

}
