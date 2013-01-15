import java.util.List;

public class Occurrences implements Comparable<Occurrences> {
	protected String query;
	protected MyArray indexes;

	public Occurrences(String query) {
		this.query = query;
		indexes = new MyArray();
	}

	@SuppressWarnings("unchecked")
	public void addIndex(int i) {
		indexes.add(i);
	}

	public String indexesToString() {
		String s = "";
		for(int i = 0; i < this.indexes.count(); i++) {
			s += " " + indexes.get(i);
		}
		return s;
	}

	public String getQuery() {
		return this.query;
	}

	@Override
	public int compareTo(Occurrences o) {
		return this.query.compareTo(o.getQuery());
	}
}