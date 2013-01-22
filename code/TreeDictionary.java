import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public final class TreeDictionary {

    private MyArray<Occurrences> occurrencesList;
    private File docFile;
    private File queryFile;
    
    @SuppressWarnings("unchecked")
    public TreeDictionary(String docFileName, String queryFileName) {

        // Create a storage for objects containing the occurrences
        occurrencesList = new MyArray<Occurrences>(Occurrences.class);

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
                        this.storeOccurrences(word, index);
                        word = "";
                        index = i+1;
                    }
                }
            }
            scanner.close();

            this.occurrencesList.sort();

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
                    int i = this.searchOccurrencesByPrefix(this.occurrencesList.getContents(), words[a]);
                    
                    // If a word is found with the requested prefix, let's find other words with matchin prefix
                    if(i >= 0) {
                        // New occurrences object, where the occurrence indexes will be stored
                        Occurrences o = new Occurrences(words[a]);
                        
                        // Loop backwards from the found index
                        int j = i - 1;
                        while(j >= 0 && this.occurrencesList.getContents()[j].getWord().indexOf(words[a]) == 0) {
                            MyArray<Integer> indexes = this.occurrencesList.getContents()[j].getIndexes();
                            for(int b = 0; b < indexes.getContents().length; b++) {
                                o.addIndex(indexes.getContents()[b]);
                            }
                            j--;
                        }
                        
                        // Loop forwards from the found index
                        j = i;
                        while(j < this.occurrencesList.getContents().length && this.occurrencesList.getContents()[j].getWord().indexOf(words[a]) == 0) {
                            MyArray<Integer> indexes = this.occurrencesList.getContents()[j].getIndexes();
                            for(int b = 0; b < indexes.getContents().length; b++) {
                                o.addIndex(indexes.getContents()[b]);
                            }
                            j++;
                        }
                        
                        // Sort the index array before printing
                        o.getIndexes().sort();
                        
                        // Print the results for this query
                        this.printOccurrences(o);
                    }
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
    private void storeOccurrences(String word, int index) {
        // Check if word is already stored
        boolean found = false;
        for(int i = 0; i < this.occurrencesList.getContents().length; i++) {
            Occurrences o = (Occurrences) this.occurrencesList.get(i);
            if(o.getWord().compareTo(word) == 0) {
                o.addIndex(index);
                found = true;
                break;
            }
        }
        if(!found) {
            Occurrences o = new Occurrences(word);
            o.addIndex(index);
            this.occurrencesList.add(o);
        }
    }
    
    private Occurrences[] findOccurrences(String query) {
        MyArray o = new MyArray<Occurrences>(Occurrences.class);
        for(int i = 0; i < this.occurrencesList.getContents().length; i++) {
            Occurrences o2 = (Occurrences) this.occurrencesList.getContents()[i];
            if(o2.getWord().equals(query)) {
                o.add(o2);
            }
        }
        return (Occurrences[]) o.getContents();
    }
    
    private int searchOccurrencesByPrefix(Occurrences[] occs, String query) {
        int start = 0;
        int end = occs.length - 1;
        int middle;
        while (start <= end) {
            middle = (start + end) / 2;
            if (occs[middle].getWord().indexOf(query) == 0) {
                return middle;
            }
            else if (occs[middle].getWord().compareTo(query) < 0) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return -1;
    }

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