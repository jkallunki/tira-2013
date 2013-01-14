import java.util.Comparator;

public class Test24
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
    TwoFourTree<Integer> tft =
                            new TwoFourTree<Integer>(new Compare<Integer>());
    for(int i = 0; i < 20; ++i)
    {
      tft.insert(23*i % 100);
      System.out.println("Nodes and their items in preorder after inserting "
                              + (23*i % 100));
      tft.printTree();
    }
    
    System.out.println("The tree contains " + tft.n() + " items and "
                                    + tft.size() + " nodes");
    System.out.println("The smallest and largest items in the tree are "
                                  + tft.smallest() + " and " + tft.largest());
    
    if(tft.find(55) != null)
    {
      System.out.println("Item 55 was found from the tree");
    }
    else
    {
      System.out.println("Item 55 was not found from the tree");
    }
    if(tft.find(76) != null)
    {
      System.out.println("Item 76 was found from the tree");
    }
    else
    {
      System.out.println("Item 76 was not found from the tree");
    }
    if(tft.largerEq(93) != null)
    {
      System.out.println("The smallest item >= 93 was found and it is "
                                                          + tft.largerEq(93));
    }
    else
    {
      System.out.println("An item >= 93 was not found");
    }
    if(tft.smallerEq(40) != null)
    {
      System.out.println("The largest item <= 40 was found and it is "
                                + tft.smallerEq(40));
    }
    else
    {
      System.out.println("An item <= 40 was not found");
    }
    
    System.out.println("The last returned item is " + tft.curr());
    System.out.println("Walking 10 steps forward with next(), printing items");
    for(int i = 0; i < 10; ++i)
    {
      System.out.print(" " + tft.next());
    }
    System.out.println("\nNow 10 steps backward with prev(), printing items");
    for(int i = 0; i < 10; ++i)
    {
      System.out.print(" " + tft.prev());
    }
    
    System.out.println("\nAll items of the tree in ascending order");
    tft.printItems();
    
    tft.remove(30);
    tft.remove(22);
    System.out.println("Nodes and their items in preorder after removing "
                        + "items 30 and 22");
    tft.printTree();
    tft.remove(76);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 76");
    tft.printTree();
    tft.remove(22);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 22");
    tft.printTree();
    tft.remove(38);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 38");
    tft.printTree();
    tft.remove(46);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 46");
    tft.printTree();
    tft.remove(69);
    tft.remove(84);
    System.out.println("Nodes and their items in preorder after removing "
                        + "items 69 and 84");
    tft.printTree();
    tft.remove(92);
    System.out.println("Nodes and their items in preorder after removing "
                        + "item 92");
    tft.printTree();
  }
}
