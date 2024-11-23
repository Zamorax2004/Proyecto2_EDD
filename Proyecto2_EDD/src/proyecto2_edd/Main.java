package proyecto2_edd;

public class Main {
    public static void main(String[] args) {
        FileStorage.getInstance().setFilename("C:\\Users\\simon\\Dropbox\\PC\\Desktop\\Proyecto2_EDD\\Proyecto2_EDD\\src\\proyecto2_edd\\dataStructures\\resources\\Targaryen.json"); // Set this to your actual file path

        Grafo grafo = new Grafo();
        String fileName = FileStorage.getInstance().getFilename();
        ;//Falta logica del json, esto es para probar el display del grafo, borrar esta clase al final
    }
}