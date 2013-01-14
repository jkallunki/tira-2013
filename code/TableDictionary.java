import java.util.List;

public class TableDictionary {

	protected static MyArray occurrencesList;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args){
		occurrencesList = new MyArray();

		Occurrences o = new Occurrences("test");
		o.addIndex(0);
		o.addIndex(2);
		o.addIndex(5);
		occurrencesList.add(o);

		System.out.println(o.toString());
	}
}