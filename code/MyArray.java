import java.util.Arrays;

// Generic array/list to store any objects that can be compared with each other
public class MyArray<E extends Comparable<E>> {
	protected E[] contents;

	@SuppressWarnings("unchecked")
	public MyArray() {
		this.contents = (E[]) new Comparable[0];
	}

	// Add an item to the contents array
	@SuppressWarnings("unchecked")
	public void add(E o) {
		// Increase the new contents array size by one
		E newContents[] = (E[]) new Comparable[this.contents.length + 1];
                
                // Loop through the old contents and insert them into the new array
                for(int i = 0; i < this.contents.length; i++) {
                        newContents[i] = this.contents[i];
		}
                // Insert the new item at the end of the array
                newContents[newContents.length - 1] = o;
                
                // Replace the old contents array with the new, appended one
		this.contents = newContents;
	}
        
        // Bubble sort, not so elegant but it works
        public void sort() {
            int n = this.contents.length;
            int i, j;
            E t;
            for(i = 0; i < n; i++){
                for(j = 1; j < (n-i); j++){
                    if(this.contents[j-1].compareTo(this.contents[j]) > 0){
                        t = this.contents[j-1];
                        this.contents[j-1] = this.contents[j];
                        this.contents[j] = t;
                    }
                }
            }
        }

	// Return object by index
	public E get(int i) {
		return this.contents[i];
	}

	// Return the contents array
	public E[] getContents() {
		return this.contents;
	}
}