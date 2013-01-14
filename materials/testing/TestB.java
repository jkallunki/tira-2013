import java.util.Comparator;

public class TestB
{
  private static class Compare<E extends Comparable<E> >
                                              implements Comparator<E>
  {
    public int compare(E a, E b)
    {
      return a.compareTo(b);
    }
  }
  
  public static void main(String[] args)
  {
    BTree<Integer> bt = new BTree<Integer>(4, new Compare<Integer>());
    for(int i = 0; i < 20; ++i)
    {
      bt.insert(23*i % 100);
      System.out.println("Nodes and their items in preorder after inserting "
                              + (23*i % 100));
      bt.printTree();
    }
    
    System.out.println("The tree contains " + bt.n() + " items and "
                                    + bt.size() + " nodes");
    System.out.println("The smallest and largest items in the tree are "
                                  + bt.smallest() + " and " + bt.largest());
    
    if(bt.find(55) != null)
    {
      System.out.println("Item 55 was found from the tree");
    }
    else
    {
      System.out.println("Item 55 was not found from the tree");
    }
    if(bt.find(76) != null)
    {
      System.out.println("Item 76 was found from the tree");
    }
    else
    {
      System.out.println("Item 76 was not found from the tree");
    }
    if(bt.largerEq(93) != null)
    {
      System.out.println("The smallest item >= 93 was found and it is "
                                                          + bt.largerEq(93));
    }
    else
    {
      System.out.println("An item >= 93 was not found");
    }
    if(bt.smallerEq(40) != null)
    {
      System.out.println("The largest item <= 40 was found and it is "
                                + bt.smallerEq(40));
    }
    else
    {
      System.out.println("An item <= 40 was not found");
    }
    
    System.out.println("The last returned item is " + bt.curr());
    System.out.println("Walking 10 steps forward with next(), printing items");
    for(int i = 0; i < 10; ++i)
    {
      System.out.print(" " + bt.next());
    }
    System.out.println("\nNow 10 steps backward with prev(), printing items");
    for(int i = 0; i < 10; ++i)
    {
      System.out.print(" " + bt.prev());
    }
    
    System.out.println("\nAll items of the tree in ascending order");
    bt.printItems();
    
    bt.remove(30);
    bt.remove(22);
    System.out.println("Nodes and their items in preorder after removing "
                        + "items 30 and 22");
    bt.printTree();
    bt.remove(76);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 76");
    bt.printTree();
    bt.remove(22);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 22");
    bt.printTree();
    bt.remove(38);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 38");
    bt.printTree();
    bt.remove(46);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 46");
    bt.printTree();
    bt.remove(69);
    bt.remove(84);
    System.out.println("Nodes and their items in preorder after removing "
                        + "items 69 and 84");
    bt.printTree();
    bt.remove(92);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 92");
    bt.printTree();
  }
}
