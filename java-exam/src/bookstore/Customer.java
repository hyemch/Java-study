package bookstore;

import static bookstore.BookstoreManagement.*;

public class Customer {
	private String name;
	private String contract;

	public Customer(String name, String contract) {
		this.name = name;
		this.contract = contract;
	}

	public String getName() {
		return name;
	}

	public String getContract() {
		return contract;
	}

	@Override
	public String toString() {
		return PINK_LINE + "\n[현재 고객 정보] \n" + COLOR_RESET + "이름: %s\n연락처: %s".formatted(name, contract);
	}
}
