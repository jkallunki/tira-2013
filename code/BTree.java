
import java.util.Comparator;

public class BTree<E> {
    private int t;
    private Comparator comp;
    private Node root;
    private E lastReturn;
    
    
    BTree(int t, Comparator<E> comp) {
        this.t = t;
        this.comp = comp;
        this.root = null;
    }
    
    public void insert(E item) {
        
    }
    
    public void remove(E item) {
        
    }
    
    public E smallest() {
        return null;
    }
    
    public E largest() {
        return null;
    }
    
    public E find(E item) {
        E i = findRecursive(item, this.root);
        return i;
    }
    
    private E findRecursive(E item, Node node) {
        E items[] = node.getItems();
        for(int i = 0; i < items.length; i++) {
            if(this.comp.compare(item, items[i]) == 0) {
                return items[i];
            }
            if(items[i] == null) {
                return null;
            }
        }
        return null;
    }
    
    public E next() {
        return null;
    }
    
    public E prev() {
        return null;
    }
    
    public E curr() {
        return null;
    }
    
    public E largerEq(E item) {
        return null;
    }
    
    public E smallerEq(E item) {
        return null;
    }
    
    public int n() {
        return 0;
    }
    
    public int size() {
        return 0;
    }
    
    public void printTree() {
        
    }
    
    public void printItems() {
        
    }
    
    private class Node {
        private Node[] children;
        private E[] items;
        private int t;
        
        Node(int t) {
            this.t = t;
            children = new BTree.Node[2*t];
            items = (E[]) new Object[2*t-1];
        }
        
        public Node[] getChildren() {
            return this.children;
        }
        
        public E[] getItems() {
            return this.items;
        }
    }
}
