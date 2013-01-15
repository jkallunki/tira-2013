import java.util.Arrays;

class MyArray<E> {
	protected E[] contents;
	protected int count;

	@SuppressWarnings("unchecked")
	public MyArray() {
		this.count = 0;
		this.contents = (E[]) new Object[count];
	}

	public void add(E o) {
		this.count++;
		if(this.contents.length < this.count) {
			this.contents = Arrays.copyOf(this.contents, this.count);
		}
		this.contents[this.count-1] = o;
	}

	// Return object by index
	public E get(int i) {
		return this.contents[i];
	}

	// Return the contents array
	public E[] getContents() {
		return this.contents;
	}

	public int count() {
		return this.count;
	}


}