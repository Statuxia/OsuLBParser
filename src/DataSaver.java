import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class DataSaver {
    private static final Path FOLDER = Path.of(System.getProperty("user.dir"), "output");
    private Path currentPath;
    private String data;

    public DataSaver(String mode, String data) throws IOException {
        if (mode == null || mode.isEmpty() || data == null) {
            return;
        }
        currentPath = Path.of(FOLDER.toString(), mode + ".txt");
        this.data = data;

        create();
        upload();
    }

    private void create() throws IOException {
        if (!Files.isDirectory(currentPath.getParent())) {
            Files.createDirectories(currentPath.getParent());
        }
        if (Files.notExists(currentPath)) {
            Files.createFile(currentPath);
        }
    }

    private void upload() throws IOException {
        Files.writeString(currentPath, data, StandardCharsets.UTF_8);
    }
}
