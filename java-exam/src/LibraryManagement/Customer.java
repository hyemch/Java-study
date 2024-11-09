package LibraryManagement;

import static LibraryManagement.LibraryManagement.*;

public class Customer {
	private String name;
	private String contact;

	public Customer(String name, String contact) {
		this.name = name;
		this.contact = contact;
	}

	@Override
	public String toString() {
		return PINK_LINE + "\n[현재 고객 정보] \n" + COLOR_RESET + "이름: %s\n연락처: %s".formatted(name, contact);
	}
}
