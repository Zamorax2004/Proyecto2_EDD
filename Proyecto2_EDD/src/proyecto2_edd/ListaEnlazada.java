
package proyecto2_edd;

public class ListaEnlazada<T> {
     private Nodo<T> head;
     private int size =0;

    // Añadir un elemento al inicio de la lista
    public void add(T data) {
        Nodo<T> nuevo = new Nodo<>(data);
        nuevo.setNext(head);
        head = nuevo;
    }

    // Eliminar un elemento
    public T remove(int index) {
        if (head == null) throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());

        if (index == 0) {
            T data = head.getData();
            head = head.getNext();
            return data;
        }

        Nodo<T> current = head;
        int count = 0;
        while (current.getNext() != null) {
            if (count == index - 1) {
                T data = current.getNext().getData();
                current.setNext(current.getNext().getNext());
                return data;
            }
            count++;
            current = current.getNext();
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    // Obtener un iterador para recorrer la lista
    public ListaIterator iterator() {
        return new ListaIterator();
    }

    // Clase iteradora interna
    public class ListaIterator {
        private Nodo<T> current;

        public ListaIterator() {
            this.current = head;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new IllegalStateException("No hay más elementos en la lista.");
            }
            T data = current.getData();
            current = current.getNext();
            return data;
        }
    }
    
    public boolean isEmpty(){
        return head == null;
    }
    
    public T get(int index) {
        Nodo<T> current = head;
        int count = 0;
        while (current != null) {
            if (count == index) {
                return current.getData();
            }
            count++;
            current = current.getNext();
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
    }

    public int size() {
        int count = 0;
        Nodo<T> current = head;
        while (current != null) {
            count++;
            current = current.getNext();
        }
        return count;
    }

    public Nodo<T> getHead() {
        return head;
    }

    public void setHead(Nodo<T> head) {
        this.head = head;
    }
    
    public void remove(T data) {
        if (head == null) return;
        if (head.getData().equals(data)) {
            head = head.getNext();
            return;
        }
        Nodo<T> current = head;
        while (current.getNext() != null) {
            if (current.getNext().getData().equals(data)) {
                current.setNext(current.getNext().getNext());
                return;
            }
            current = current.getNext();
        }
    }
    
    public void clear() {
    head = null; 
    size = 0;    
}
}
