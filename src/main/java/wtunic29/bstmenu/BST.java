package wtunic29.bstmenu;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementa en el menú dinámico y como sería ese método para visualizar la altura del árbol?
 * @author poeta
 * @param <Key>
 * @param <Value>
 */


//Falta agregar un metodo para mostrar el arbol con nodos y aristas

public class BST <Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        
        private Key key;
        private Value value;
        private Node left, right;
        private int size;

        public Node(Key key, Value value, int size) {
            
            this.key = key;
            this.value = value;
            this.size = size;
            
        }
        
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.size;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return get(x.left, key);
        else if (cmp > 0) return get(x.right, key);
        else return x.value;
        
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value value) {
        
        if (x == null) return new Node(key, value, 1);
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value);
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value;
        
        x.size = 1 + size(x.left) + size(x.right);
        return x;
        
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        return x.left == null ? x : min(x.left);
    }

    public Key max() {
        return max(root).key;
    }

    private Node max(Node x) {
        return x.right == null ? x : max(x.right);
    }

    public Key floor(Key key) {
        
        Node x = floor(root, key);
        return x == null ? null : x.key;
        
    }

    private Node floor(Node x, Key key) {
        
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key);
        
        Node t = floor(x.right, key);
        
        return (t != null) ? t : x;
    }

    public Key ceiling(Key key) {
        
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
        
    }

    private Node ceiling(Node x, Key key) {
        
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        
        if (cmp == 0) return x;
        if (cmp > 0) return ceiling(x.right, key);
        
        Node t = ceiling(x.left, key);
        return (t != null) ? t : x;
        
    }

    public Key select(int k) {
        return select(root, k).key;
    }

    private Node select(Node x, int k) {
        
        if (x == null) return null;
        
        int t = size(x.left);
        
        if (t > k) return select(x.left, k);
        else if (t < k) return select(x.right, k - t - 1);
        else return x;
        
    }

    public int rank(Key key) {
        return rank(key, root);
    }

    private int rank(Key key, Node x) {
        
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(key, x.left);
        else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right);
        else return size(x.left);
        
    }

    public void deleteMin() {
        
        if (root != null) root = deleteMin(root);
        
    }

    private Node deleteMin(Node x) {
        
        if (x.left == null) return x.right;
        x.left = deleteMin(x.left);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
        
    }

    public void deleteMax() {
        if (root != null) root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        
        if (x.right == null) return x.left;
        
        x.right = deleteMax(x.right);
        x.size = 1 + size(x.left) + size(x.right);
        return x;
        
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        
        if (x == null) return null;
        
        int cmp = key.compareTo(x.key);
        
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
       
        else {
            
            if (x.right == null) return x.left;
            if (x.left == null) return x.right;
            
            Node t = x;
            
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
            
        }
        
        x.size = 1 + size(x.left) + size(x.right);
        return x;
        
    }

    public Iterable<Key> keys() {
        
        List<Key> list = new ArrayList<>();
        inorder(root, list);
        return list;
        
    }

    private void inorder(Node x, List<Key> list) {
        
        if (x == null) return;
        
        inorder(x.left, list);
        list.add(x.key);
        inorder(x.right, list);
        
    }

    public Iterable<Key> preorder() {
        
        List<Key> keys = new ArrayList<>();
        preorder(root, keys);
        return keys;
        
    }

    private void preorder(Node x, List<Key> keys) {
        
        if (x == null) {
            return;
        }
        
        keys.add(x.key);
        preorder(x.left, keys);
        preorder(x.right, keys);
        
    }

    public Iterable<Key> postorder() {
        
        List<Key> keys = new ArrayList<>();
        postorder(root, keys);
        return keys;
        
    }

    private void postorder(Node x, List<Key> keys) {
        
        if (x == null) {
            return;
        }
        
        postorder(x.left, keys);
        postorder(x.right, keys);
        keys.add(x.key);
    }
    
    public int altura() {
        return altura(root);
    }

    private int altura(Node nodo) {
        
        if (nodo == null) {
            return -1; // Altura de un árbol vacío es -1 (por convención)
        }
        return 1 + Math.max(altura(nodo.left), altura(nodo.right));
        
    }
    
    public Iterable<Key> porNiveles() {
        
        List<Key> resultado = new ArrayList<>();
        int h = altura();
        
        for (int nivel = 0; nivel <= h; nivel++) {
            agregarNivel(root, nivel, resultado);
        }
        
        return resultado;
        
    }

    private void agregarNivel(Node nodo, int nivel, List<Key> resultado) {
        
        if (nodo == null) {
            return;
        }
        
        if (nivel == 0) {
            
            resultado.add(nodo.key);
        } else {
            
            agregarNivel(nodo.left, nivel - 1, resultado);
            agregarNivel(nodo.right, nivel - 1, resultado);
            
        }
        
    }
    
}
