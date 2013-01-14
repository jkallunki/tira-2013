import java.util.List;

public class Occurrences {
	protected String word;
	protected MyArray indexes;

	public Occurrences(String word) {
		this.word = word;
		indexes = new MyArray();
	}

	@SuppressWarnings("unchecked")
	public void addIndex(int i) {
		indexes.add(i);
	}

	public String toString() {
		String s = "";
		for(int i = 0; i < this.indexes.count(); i++) {
			s += " " + indexes.get(i);
		}
		return s;
	}
}