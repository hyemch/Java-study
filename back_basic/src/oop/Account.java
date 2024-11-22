package oop;

public class Account {
	private int accountNo;
	private String name;
	private double balance;

	public Account(int accountNo, String name, double balance) {
		this.accountNo = accountNo;
		this.name = name;
		this.balance = balance;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public void insert(int num, String name, double amount) {
		// this.accountNo = num;
		// this.name = name;
		// this.balance = amount;
		setAccountNo(num);
		setName(name);
		setBalance(amount);
	}

	public void deposit(double amount) {
		System.out.println(getName() + "님의 계좌로 " + amount + "원이 입금되었습니다.");
		// setBalance(getBalance() + amount);
		this.balance += amount;
		System.out.println(getName() + "님의 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");
	}

	public void withdraw(double amount) {
		if (amount > this.getBalance()) {
			System.out.println("잔액이 부족하여 출금할 수 없습니다.");
			return;
		}
		// setBalance(this.getBalance() - amount);
		this.balance -= amount;
		System.out.println(getName() + "님의 계좌에서 " + amount + "원이 출금되었습니다.");
		System.out.println(getName() + "님의 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");
	}

	public void checkBalance() {
		System.out.println(
			getName() + "님의" + getAccountNo() + " 계좌 현재 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");
	}

	public void display() {
		System.out.println("******************");
		System.out.println("계좌번호: " + getAccountNo());
		System.out.println("예금주: " + getName());
		System.out.println("잔액: " + String.format("%.1f", getBalance()));
		System.out.println("******************\n");
	}
}
