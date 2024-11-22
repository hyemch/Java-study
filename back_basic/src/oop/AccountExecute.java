package oop;

import java.util.Scanner;

public class AccountExecute {
	public static void main(String[] args) {
		Account acc1 = new Account(1, "Min", 77000000);

		final int num = 20;
		acc1.display();
		// System.out.println("-----------");
		System.out.println("-".repeat(num));
		acc1.deposit(200000);
		System.out.println("-----------");
		acc1.checkBalance();
		System.out.println("-----------");
		acc1.withdraw(300000000);
		System.out.println("-----------");
		acc1.withdraw(50000);

		Scanner input = new Scanner(System.in);
		System.out.println("계좌번호를 설정해주세요");
		int accountNo = input.nextInt();
		input.nextLine();
		System.out.println("예금주 이름을 설정해주세요");
		String name = input.nextLine();
		System.out.println("잔액을 설정해주세요");
		double balance = input.nextDouble();

		acc1.insert(accountNo, name, balance);
		acc1.display();

		while (true) {
			System.out.println("입금을 원하시면 + 출금을 원하시면 -를 입력해주세요.");
			char symbol = input.next().charAt(0);
			System.out.println("원하는 거래 금액을 입력해주세요.");
			double amount = input.nextDouble();
			if (symbol != '+' && symbol != '-') {
				System.out.println("잘못입력하셨습니다.");
				continue;
			}
			if (symbol == '+') {
				acc1.deposit(amount);
			} else {
				acc1.withdraw(amount);
			}
			break;
		}
		input.close();
	}
}
