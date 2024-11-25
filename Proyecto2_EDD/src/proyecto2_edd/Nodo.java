
package proyecto2_edd;


public class Nodo<T> {
    private T data;          
    private Nodo<T> next;    

    public Nodo(T data) {
        this.data = data;
        this.next = null;
    }

    public T getData() {
        return data;
    }

    public Nodo<T> getNext() {
        return next;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
}
