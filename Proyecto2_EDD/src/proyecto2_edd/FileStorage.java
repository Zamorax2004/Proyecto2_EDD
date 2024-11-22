package proyecto2_edd;

public class FileStorage {
    private static FileStorage instance;
    private String filename;

    private FileStorage() {}

    public static FileStorage getInstance() {
        if (instance == null) {
            instance = new FileStorage();
        }
        return instance;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}