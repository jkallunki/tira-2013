import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class TreeDictionary {

    private BTree<Occurrences> occurrencesTree;
    private File docFile;
    private File queryFile;
    
    private class OccurrencesComparator implements Comparator<Occurrences> {
        @Override
        public int compare(Occurrences o1, Occurrences o2) {
            return o1.getWord().compareTo(o2.getWord());
        }
    }
    
    @SuppressWarnings("unchecked")
    public TreeDictionary(String docFileName, String queryFileName) {
        try {
            occurrencesTree = new BTree<Occurrences>(2, new OccurrencesComparator());
        } catch (Exception e) {
            Logger.getLogger(TreeDictionary.class.getName()).log(Level.SEVERE, null, e);
        }

        this.docFile = new File(docFileName);
        this.queryFile = new File(queryFileName);

        try {
            Scanner scanner = new Scanner(this.docFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                String word = "";
                int index = 0;

                for(int i = 0; i < line.length(); i++) {
                    if(line.charAt(i) != ' ') {
                        word += line.charAt(i);
                    }
                    else {
                        this.occurrencesTree.insert(new Occurrences(word));
                        word = "";
                        index = i+1;
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("The document file was not found.");
        }
        
        try {
            Scanner scanner = new Scanner(this.queryFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] words = line.split(" ");
                
                // Loop through all the query words found in the document
                for(int a = 0; a < words.length; a++) {
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("The query file was not found.");
        }
    }

    // Prints occurrences of a single query in correct format
    private void printOccurrences(Occurrences o) {
        System.out.println("Positions where \"" + o.getWord() + "\" occurs:");
        System.out.println(o.indexesToString());
        System.out.println();
    }

    // Stores a single occurrence of a word
    @SuppressWarnings("unchecked")
    
    
    // Main method
    public static void main(String args[]){
        try {
            // Instantiate this class
            TreeDictionary td = new TreeDictionary(args[0], args[1]); 
        }
        catch (ArrayIndexOutOfBoundsException e) {
            // Two arguments are required
            e.printStackTrace();
            //System.out.println("Missing required arguments!");
        }
    }
}