import java.io.IOException;
import java.util.Map;

public class Main {
    private static final Map<String, String> urls = Map.of(
            "standart", "https://ameobea.me/osutrack/bestplays/",
            "taiko", "https://ameobea.me/osutrack/bestplays/taiko",
            "ctb", "https://ameobea.me/osutrack/bestplays/ctb",
            "mania", "https://ameobea.me/osutrack/bestplays/mania/"
    );
    public static void main(String[] args) throws IOException {
        for (String mode : urls.keySet()) {
            String data = DataParser.getByClass(urls.get(mode), "tbody");
            DataSaver.of(mode, data);
        }
    }
}