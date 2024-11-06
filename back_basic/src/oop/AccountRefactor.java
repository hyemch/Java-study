package trythis;

import java.util.Scanner;

public class AccountRefactor {
	private String id;
	private String name;
	private double balance;
	private Scanner scan;

	public AccountRefactor(String id, String name) {
		this(id, name, 0);
	}

	public AccountRefactor(String id, String name, double balance) {
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public static void showSelectAccounts(AccountRefactor[] accounts) {
		showSelectAccounts(accounts, null);
	}

	public static void showSelectAccounts(AccountRefactor[] accounts, AccountRefactor me) {
		StringBuilder msg = new StringBuilder();
		for (AccountRefactor acc : accounts) {
			String id = acc.getId();
			if (me != null && id.equals(me.getId())) {
				continue;
			}
			String name = acc.getName();
			if (msg.isEmpty()) {
				msg = new StringBuilder("(%s:%s".formatted(id, name));

			} else {
				msg.append(", (%s:%s".formatted(id, name));
			}
		}
		msg.append("): ");
		System.out.println(msg);
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

	public double getBalance() {
		return balance;
	}

	private void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit() {
		System.out.println("입금할 금액을 입력해주세요: ");
		double amount = scan.nextDouble();
		setBalance(balance + amount);
		System.out.printf("%.1f원이 입금되었습니다!\n", amount);
		this.checkBalance();
		// System.out.println(name + "님의 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");

	}

	public void withdraw() {
		System.out.println("출금할 금액을 입력해주세요: ");
		double amount = scan.nextDouble();
		if (amount > balance) {
			System.out.println("잔액이 부족하여 출금할 수 없습니다.");
		} else {
			setBalance(balance - amount);
			System.out.println(name + "님의 계좌에서 " + amount + "원이 출금되었습니다.");
		}
		checkBalance();
		// System.out.println(name + "님의 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");
	}

	public void checkBalance() {
		System.out.println(name + "님의 잔액은 " + String.format("%.1f", getBalance()) + "원 입니다.");
	}

	public void display() {
		String output = """
			------------------------------
			계좌번호: %s
			예금주: %s
			잔액: %.1f원
			------------------------------
			""".formatted(this.id, this.name, this.balance);
		System.out.println(output);
	}

	public void transfer(AccountRefactor[] accounts) {
		System.out.println("누구에게 송금하시겠습니까? ");
		AccountRefactor.showSelectAccounts(accounts, this);
		int selectedId = this.scan.nextInt();
		AccountRefactor toAccount = accounts[selectedId - 1];

		System.out.println("송금할 금액을 입력해주세요: ");
		double transAmount = this.transferTo(toAccount, this.scan.nextDouble());
		System.out.printf("transAmount=%.1f\n ", transAmount);
	}

	public double transferTo(AccountRefactor toAccount, double amount) {
		System.out.printf("%s님이 %s님 에게 %,.1f 송금을 시도합니다.\n", this.getName(), toAccount.getName(), amount);
		if (this.balance < amount) {
			System.out.println("잔액이 부족합니다.");
			return 0;
		}
		this.balance -= amount;
		toAccount.setBalance(this.balance);
		System.out.printf("%s님이 %s님에게 %,.1f원 송금을 완료하였습니다!\n", this.getName(), toAccount.getName(), amount);
		return this.balance;
	}

	public void login() {
		this.login(null);
	}

	public void login(Scanner scan) {
		if (this.scan == null) {
			if (scan != null) {
				this.scan = scan;
			} else {
				this.scan = new Scanner(System.in);
			}
		}
		this.display();
	}

	public void action() {
		if (isNotValidScan()) {
			return;
		}
		while (true) {
			System.out.println("\nCommand(+:입금, -:출금, q:종료): ");
			String cmd = scan.next();
			switch (cmd) {
				case "+" -> this.deposit();
				case "-" -> this.withdraw();
				case "q" -> {
					return;
				}
				default -> System.out.println("잘 못된 명령입니다.");
			}
		}
	}

	private boolean isNotValidScan() {
		if (this.scan == null) {
			System.out.println("먼저 로그인 하세요.");
			return true;
		}
		return false;
	}

	public void logout() {
		if (this.scan != null) {
			this.scan.close();
		}
		this.display();
	}

	public String toString() {
		return "Account [id=%s, name=%s, balance=%s]".formatted(getId(), getName(), getBalance());
	}
}

class T {
	public static void main(String[] args) throws Exception {
		AccountRefactor accConan = new AccountRefactor("1", "코난", 10000);
		AccountRefactor accRose = new AccountRefactor("2", "장미", 10000);
		AccountRefactor accMiran = new AccountRefactor("3", "미란", 10000);

		AccountRefactor[] accounts = {accConan, accRose, accMiran};

		Scanner scan = new Scanner(System.in);
		System.out.println("계좌를 선택하세요.");
		AccountRefactor.showSelectAccounts(accounts);
		int selectedId = scan.nextInt();
		AccountRefactor workingAccount = accounts[selectedId - 1];
		workingAccount.login(scan);
		workingAccount.transfer(accounts);
		workingAccount.login();

		// acc.login();
		// acc.action();
		// acc.logout();
	}
}
