package trythis.book;

import java.util.Arrays;

public class ArrayedGeneralBook implements GeneralBook {
	private String[] names;
	private String[] records;

	ArrayedGeneralBook(String[] names, String[] records) {
		this.names = names;
		this.records = records;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public String[] getRecords() {
		return records;
	}

	public void setRecodes(String[] records) {
		this.records = records;
	}

	@Override
	public int size() {
		return names.length;
	}

	@Override
	public String names() {
		// StringBuilder str = new StringBuilder();
		// for (String name : names) {
		// 	str.append(name);
		// }
		// return String.valueOf(str);
		return String.join(" ", names);
	}

	@Override
	public String records() {
		StringBuilder str = new StringBuilder();
		for (String record : records) {
			if (!str.isEmpty()) {
				str.append(" ");
			}
			str.append(record);
		}
		// return String.valueOf(str);
		return str.toString();
	}

	@Override
	public boolean nameExists(String name) {
		for (String arrName : names) {
			if (arrName.equals(name)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void add(String name, String record) {
		if (this.nameExists(name)) {
			System.out.println(name + " : 이미 존재하는 이름입니다.");
			return;
		}

		String[] newNames = Arrays.copyOf(names, names.length + 1);
		newNames[newNames.length - 1] = name;
		String[] newRecords = Arrays.copyOf(records, records.length + 1);
		newRecords[newRecords.length - 1] = record;
		setNames(newNames);
		setRecodes(newRecords);
		sort();

	}

	@Override
	public void remove(String name, String record) {
		int arrSize = names.length;
		for (int i = 0; i < arrSize; i++) {
			if (names[i].equals(name)) {
				for (int j = i; j < arrSize - 1; j++) {
					names[j] = names[j + 1];
					records[j] = records[j + 1];
				}
				String[] newNames = Arrays.copyOf(names, names.length - 1);
				String[] newRecords = Arrays.copyOf(records, records.length - 1);
				setNames(newNames);
				setRecodes(newRecords);
				sort();
				return;
			}
		}
		System.out.println("삭제할 값이 없습니다.");
	}

	@Override
	public String get(String name) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name)) {
				sb.append(records[i]);
			}
		}
		if (sb.isEmpty())
			return ("일치하는 기록이 없습니다.\n");
		return (sb.toString());
	}

	@Override
	public void sort() {
		int nameSize = names.length;
		for (int i = 0; i < nameSize; i++) {
			for (int j = i + 1; j < nameSize; j++) {
				if (names[i].compareTo(names[j]) > 0) {
					String temp = names[i];
					names[i] = names[j];
					names[j] = temp;
					temp = records[i];
					records[i] = records[j];
					records[j] = temp;
				}
			}
		}
	}

	@Override
	public void print() {
		StringBuilder sb = new StringBuilder();
		int nameSize = names.length;
		for (int i = 0; i < nameSize; i++) {
			if (!sb.isEmpty()) {
				sb.append('\n');
			}
			sb.append(names[i]).append(records[i]);
		}
		System.out.println(sb.toString());

	}

	public static void main(String[] args) {
		String[] names = {"Sam", "Rhee", "Kim"};
		String[] records = {"1111", "2222", "3333"};

		ArrayedGeneralBook gb = new ArrayedGeneralBook(names, records);
		gb.add("Allan", "4444");
		for (String name : gb.getNames()) {
			System.out.println(name);
		}
		for (String record : gb.getRecords()) {
			System.out.println(record);
		}
		gb.add("Allan", "4444");
		gb.print();
		gb.remove("Allan", "4444");
		gb.add("Allan", "4444");

		gb.print();
		// System.out.println(gb.names());
		//Allan4444\nKim3333\nRhee2222\nSam1111
		System.out.println("현재 저장된 데이터의 크기 : " + gb.size()); //4
		gb.add("Alex", "5555");
		System.out.println("현재 저장된 데이터의 크기 : " + gb.size()); //5
		gb.print();    //Alex5555\nAllan4444\nKim3333\nRhee2222\nSam1111\n
		System.out.println(gb.nameExists("Alex")); //true
		gb.remove("Alex", "5555");
		gb.remove("Sam", "1111");
		gb.print();    //Allan4444\nKim3333\nRhee2222
		String foundRecord = gb.get("Abcd");
		System.out.println(foundRecord); //일치 x
		foundRecord = gb.get("Allan");
		System.out.println(foundRecord); //4444
	}
}
