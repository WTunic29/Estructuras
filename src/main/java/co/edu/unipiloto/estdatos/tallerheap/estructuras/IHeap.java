package co.edu.unipiloto.estdatos.tallerheap.estructuras;

/**
 *
 * @author poeta
 */
public interface IHeap<T> {
    
    void add(T elemento);
    T peek();
    T poll();
    int size();
    boolean isEmpty();
    void siftUp();
    void siftDown();
}
