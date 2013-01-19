public class Occurrences implements Comparable<Occurrences> {
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

	public String indexesToString() {
		String s = "";
		for(int i = 0; i < this.indexes.getContents().length; i++) {
			s += " " + indexes.get(i);
		}
		return s;
	}

	public String getWord() {
		return this.word;
	}

	@Override
	public int compareTo(Occurrences o) {
		return this.word.compareTo(o.getWord());
	}
}