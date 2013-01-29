
import java.util.Comparator;

public class BTree<E> {
    private int t;              // Minimum amount of child nodes
    private int n;              // Number of the stored entries
    private int size;           // Number of nodes
    
    private Comparator comp;    // Used to compare entries
    private Node root;          // Root node
    private E lastReturn;       // Last returned 
    
    
    BTree(int t, Comparator<E> comp) throws Exception {
        // t has to be 2 or more
        if(t < 2) {
            throw new Exception();
        }
        this.t = t;
        this.comp = comp;
        this.root = new BTree.Node(t);
    }
    
    public void insert(E item) {
        this.insertInto(this.root, item);
    }
    
    private void insertInto(Node node, E item) {
        E[] items = (E[]) node.getItems();
        // There is room for an item
        if(items[items.length-1] == null) {
            for(int i = items.length-1; i >= 0; i--) {
                if(i == 0) {
                    items[i] = item;
                    System.out.println("Inserted!");
                    
                }
                else {
                    if(items[i-1] != null && this.comp.compare(item, items[i-1]) > 0) {
                        items[i] = item;
                        System.out.println("Inserted!");
                        break;
                    }
                    else {
                        items[i] = items[i-1];
                    }
                }
            }
        }
        // Overflow!
        else {
            System.out.println("Overflow!");
        }
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
    
    // Recursive find algorithm, for internal use only
    private E findRecursive(E item, Node node) {
        E items[] = node.getItems();
        // Loop through the entries
        for(int i = 0; i < items.length; i++) {
            // Match
            if(this.comp.compare(item, items[i]) == 0) {
                return items[i];
            }
            // We must go deeper
            else if(this.comp.compare(item, items[i]) < 0 && node.getChildren()[i] != null) {
                return findRecursive(item, node.getChildren()[i]);
            }
        }
        // Search the child
        if(this.comp.compare(item, items[items.length-1]) > 0 && node.getChildren()[items.length] != null) {
            return findRecursive(item, node.getChildren()[items.length]);
        }
        // Nothing found
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
    
    
    // DEBUG METHODS
    public E[] getRootItems() {
        return this.root.getItems();
    }
}
