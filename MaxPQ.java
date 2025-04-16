package co.edu.unipiloto.estdatos.tallerheap.estructuras;
/**
 * Interfaz de un heap binario genérico.
 * @author poeta
 * @param <T>
 */

public class MaxPQ<T extends Comparable<T>> implements IHeap<T> {

    

    private class NodePQ {

        T item;
        NodePQ parent, left, right;

        NodePQ(T item) {
            this.item = item;
        }
    }

    private NodePQ root;
    private int size;

    public MaxPQ() {
        root = null;
        size = 0;
    }

    @Override
    public void add(T item) {
        NodePQ nuevo = new NodePQ(item);

        if (root == null) {
            root = nuevo;
        } else {
            NodePQ padre = getParent(size + 1);
            if (padre.left == null) {
                padre.left = nuevo;
            } else {
                padre.right = nuevo;
            }
            nuevo.parent = padre;
            siftUp(nuevo);
        }
        size++;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Heap vacío");
        }
        return root.item;
    }

    @Override
    public T poll() {
        if (isEmpty()) {
            throw new RuntimeException("Heap vacío");
        }

        T maxItem = root.item;

        if (size == 1) {
            root = null;
        } else {
            NodePQ last = getNode(size);
            exch(root, last);
            if (last.parent.left == last) {
                last.parent.left = null;
            } else {
                last.parent.right = null;
            }
            siftDown(root);
        }

        size--;
        return maxItem;
    }

    @Override
    public void siftUp() {  
    }
    
    private void siftUp(NodePQ nodo) {
        
        while (nodo.parent != null && nodo.item.compareTo(nodo.parent.item) > 0) {
        
            exch(nodo, nodo.parent);
            nodo = nodo.parent;
        
        }
    
    }

    @Override
    public void siftDown() {
    
        siftDown(root);
    
    }

    private void siftDown(NodePQ nodo) {
        
        while (nodo.left != null) {
            
            NodePQ mayor = nodo.left;
            if (nodo.right != null && nodo.right.item.compareTo(mayor.item) > 0) {
                mayor = nodo.right;
            
            }

            if (nodo.item.compareTo(mayor.item) >= 0) {
            
                break;
            }
            
            exch(nodo, mayor);
            nodo = mayor;
        
        }
    
    }

    @Override
    public int size() {
        
        return size;
        
    }

    @Override
    public boolean isEmpty() {
        
        return size == 0;
        
    }

    private NodePQ getNode(int pos) {
        
        String path = Integer.toBinaryString(pos).substring(1);
        NodePQ actual = root;

        for (char c : path.toCharArray()) {
            
            if (actual == null) {
                
                return null;
                
            }
            
            actual = (c == '0') ? actual.left : actual.right;
        }

        return actual;
    }

    private NodePQ getParent(int pos) {
        
        return getNode(pos / 2);
        
    }

    private void exch(NodePQ a, NodePQ b) {
        
        T tmp = a.item;
        a.item = b.item;
        b.item = tmp;
        
    }
    
}
