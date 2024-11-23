package proyecto2_edd;

public class Main {
    public static void main(String[] args) {
        // Set the file name first
        FileStorage.getInstance().setFilename("C:\\Users\\simon\\Dropbox\\PC\\Desktop\\Proyecto2_EDD\\Proyecto2_EDD\\src\\proyecto2_edd\\dataStructures\\resources\\Targaryen.json"); // Set this to your actual file path

        // Then create the Grafo instance and use it
        Grafo grafo = new Grafo();
        String fileName = FileStorage.getInstance().getFilename();
        if (fileName != null && !fileName.isEmpty()) {
            grafo.parseJsonFile(fileName);
            grafo.displayGraph();
        } else {
            System.out.println("File name is null or empty.");
        }
    }
}