import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public final class TableDictionary {

    protected MyArray<Occurrences> occurrencesList;
    protected File docFile;
    protected File queryFile;
    
    @SuppressWarnings("unchecked")
    public TableDictionary(String docFileName, String queryFileName) {

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
                        this.storeOccurrences(word, index);
                    }
                    else {
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

                for(int i = 0; i < words.length; i++) {
                    Occurrences[] o = this.findOccurrences(words[i]);
                    for(int j = 0; j < o.length; j++) {
                        this.printOccurrences(o[i]);
                    }
                }
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("The query file was not found.");
        }
    }

    // Prints occurrences of a single query in correct format
    protected void printOccurrences(Occurrences o) {
        System.out.println("Positions where \"" + o.getWord() + "\" occurs:");
        System.out.println(o.indexesToString());
        System.out.println();
    }

    // Stores a single occurrence of a word
    @SuppressWarnings("unchecked")
    protected void storeOccurrences(String word, int index) {
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
    
    protected Occurrences[] findOccurrences(String query) {
        MyArray o = new MyArray<Occurrences>(Occurrences.class);
        for(int i = 0; i < this.occurrencesList.getContents().length; i++) {
            Occurrences o2 = (Occurrences) this.occurrencesList.getContents()[i];
            if(o2.getWord().equals(query)) {
                o.add(o2);
            }
        }
        return (Occurrences[]) o.getContents();
    }

    // Main method
    public static void main(String args[]){
        try {
            // Instantiate this class
            TableDictionary td = new TableDictionary(args[0], args[1]); 
        }
        catch (ArrayIndexOutOfBoundsException e) {
            // Two arguments are required
            e.printStackTrace();
            //System.out.println("Missing required arguments!");
        }
    }
}