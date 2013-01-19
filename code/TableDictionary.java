import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public final class TableDictionary {

    protected MyArray occurrencesList;
    protected File docFile;
    protected File queryFile;
    
    @SuppressWarnings("unchecked")
    public TableDictionary(String docFileName, String queryFileName) {

        // Create a storage for objects containing the occurrences
        occurrencesList = new MyArray();

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
                        this.storeOccurrence(word, index);
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

        //DEBUG
        printOccurrences((Occurrences) this.occurrencesList.get(0));
        printOccurrences((Occurrences) this.occurrencesList.get(1));
        printOccurrences((Occurrences) this.occurrencesList.get(2));
        printOccurrences((Occurrences) this.occurrencesList.get(3));
        printOccurrences((Occurrences) this.occurrencesList.get(4));
        printOccurrences((Occurrences) this.occurrencesList.get(5));
        printOccurrences((Occurrences) this.occurrencesList.get(6));
        printOccurrences((Occurrences) this.occurrencesList.get(7));
        printOccurrences((Occurrences) this.occurrencesList.get(8));
    }

    // Prints occurrences of a single query in correct format
    protected void printOccurrences(Occurrences o) {
        System.out.println("Positions where \"" + o.getWord() + "\" occurs:");
        System.out.println(o.indexesToString());
        System.out.println();
    }

    // Stores a single occurrence of a word
    @SuppressWarnings("unchecked")
    protected void storeOccurrence(String word, int index) {
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