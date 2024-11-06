package trythis.book;

interface GeneralBook {
	public int size();

	public String names();

	public String records();

	public boolean nameExists(String name);

	public void add(String name, String record);

	public void remove(String name, String record);

	public String get(String name);

	public void sort();

	public void print();
}
