package trythis;

public class Account {
	private String id;
	private String name;
	private int balance;

	public Account() {
		this("44-444-444", "자바");
	}

	public Account(String id, String name) {
		this.id = id;
		this.name = name;
		this.balance = 0;
	}

	public Account(String id, String name, int balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int deposit(int amount) {
		System.out.println(name + "님의 계좌로 " + amount + "원이 입금되었습니다.");
		this.balance += amount;
		// System.out.println(name + "님의 잔액은 " + String.format("%d", getBalance()) + "원 입니다.");
		return this.balance;
	}

	public int withdraw(int amount) {
		System.out.println("출금을 시도합니다.");
		if (amount > balance) {
			System.out.println("잔액이 부족하여 출금할 수 없습니다.");
		} else {
			System.out.println(name + "님의 계좌에서 " + amount + "원이 출금되었습니다.");
			this.balance -= amount;
			// System.out.println(name + "님의 잔액은 " + String.format("%d", getBalance()) + "원 입니다.");
		}
		return balance;
	}

	public int transferTo(Account another, int amount) {
		System.out.println(another.name + "님에게 " + amount + "원 송금 시도");
		if (amount > balance) {
			System.out.println("잔액이 부족하여 출금할 수 없습니다.");
		} else {
			System.out.println(another.name + "님에게 " + amount + "원 을 송금을 시도하였습니다..");
			this.balance -= amount;
			another.balance += amount;
			System.out.println(this.name + "님이 " + another.name + "님의 계좌로 " + amount + "원 을 송금하였습니다.");
		}
		return balance;
	}

	public String toString() {
		return "Account [id=" + getId() + ", name=" + getName() + ", balance=" + getBalance() + "]";
	}
}
