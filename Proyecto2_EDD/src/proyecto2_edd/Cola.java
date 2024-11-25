
package proyecto2_edd;


public class Cola<T> {
    private ListaEnlazada<T> lista; 

    // Constructor
    public Cola() {
        this.lista = new ListaEnlazada<>();
    }

    // Método para añadir un elemento al final de la cola
    public void enqueue(T elemento) {
        lista.add(elemento); 
    }

    // Método para eliminar y devolver el primer elemento de la cola
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        T elemento = lista.get(0); 
        lista.remove(0); 
        return elemento;
    }

    // Método para ver el primer elemento sin eliminarlo
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("La cola está vacía.");
        }
        return lista.get(0); 
    }

    // Método para verificar si la cola está vacía
    public boolean isEmpty() {
        return lista.isEmpty(); 
    }

    // Método para obtener el tamaño de la cola
    public int size() {
        return lista.size(); 
    }

    // Método para limpiar la cola
    public void clear() {
        lista.clear(); 
    }

    // Método para representación en String
    
    public String toString() {
        return lista.toString(); 
    }
}
