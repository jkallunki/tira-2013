public class Occurrences implements Comparable<Occurrences> {
	protected String word;
	protected MyArray<Integer> indexes;

	public Occurrences(String word) {
		this.word = word;
		indexes = new MyArray(Integer.class);
	}

	@SuppressWarnings("unchecked")
	public void addIndex(int i) {
		indexes.add(new Integer(i));
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
        
        public MyArray getIndexes() {
            return this.indexes;
        }

	@Override
	public int compareTo(Occurrences o) {
		return this.word.compareTo(o.getWord());
	}
}