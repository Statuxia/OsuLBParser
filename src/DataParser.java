import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DataParser {

    public static String getByClass(String url, String tag) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements table = doc.select(tag);
        Elements rows = table.select("tr");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 100; i++) {
            Elements columns = rows.get(i).select("td");
            if (builder.length() == 0) {
                builder.append("|-\n| ");
            } else {
                builder.append("\n|-\n| ");
            }
            for (int i2 = 0; i2 < columns.size(); i2++) {
                Element column = columns.get(i2);
                switch (i2) {
                    case 2 -> {
                        builder.append(column.select("a").get(0).firstChild());
                        builder.append(" || ");
                    }
                    case 4 -> {
                        String s = column.select("abbr").attr("title").replace("T", " ").substring(0, 19);
                        builder.append(s);
                    }
                    default -> {
                        builder.append(column.firstChild());
                        builder.append(" || ");
                    }
                }
            }
        }
        return builder.toString();
    }
}
