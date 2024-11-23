/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2_edd;

/**
 *
 * @author yilup
 */
public class Nodo<T> {
    private T data;          // Dato almacenado
    private Nodo<T> next;    // Referencia al siguiente nodo

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
