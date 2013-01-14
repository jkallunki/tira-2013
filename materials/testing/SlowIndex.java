import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class SlowIndex
{
  public static void main(String[] args)
  {
    if(args.length != 2)
    {
      System.err.println("Give the document and query file names!");
      System.exit(1);
    }
    try
    {
      BufferedReader doc_file = new BufferedReader(new FileReader(args[0]));
      BufferedReader query_file = new BufferedReader(new FileReader(args[1]));
      String document = doc_file.readLine();
      doc_file.close();
      for(String query = query_file.readLine(); query != null;
                                                query = query_file.readLine())
      {
        System.out.println("Positions where \"" + query + "\" occurs:");
        int pos = -1;
        while((pos = document.indexOf(query, pos+1)) >= 0)
        {
          if(pos == 0 || Character.isWhitespace(document.charAt(pos-1)))
          {
            System.out.print(" " + pos);
          }
        }
        System.out.println("\n");
      }
      query_file.close();
    }
    catch(FileNotFoundException e)
    {
      System.err.println("Could not open one of the files!");
      System.exit(1);
    }
    catch(IOException e)
    {
      System.err.println("Error reading one of the files!");
      System.exit(1);
    }
  }
}
