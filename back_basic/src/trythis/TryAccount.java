package trythis;

public class TryAccount {
	public static void main(String[] args) {
		Account acc1 = new Account("11-111-111", "코난", 20000);
		Account acc2 = new Account("22-222-222", "장미", 100000);
		Account acc3 = new Account("33-333-333", "미란", 50000);
		Account acc4 = new Account();
		Account acc5 = new Account("55-555-555", "클래스");

		//처음 계좌 정보
		System.out.println("acc1= " + acc1);
		System.out.println("acc2= " + acc2);
		System.out.println("acc3= " + acc3);
		System.out.println("acc4= " + acc4);
		System.out.println("acc5= " + acc5);

		// 업무
		System.out.println("잔액은 " + acc1.transferTo(acc2, 30000) + "입니다.");
		System.out.println("잔액은 " + acc1.deposit(50000) + "입니다.");
		acc1.transferTo(acc2, 30000);
		System.out.println("잔액은 " + acc3.withdraw(4500) + "입니다.");

		//업무 후 계좌 정보
		System.out.println("acc1= " + acc1);
		System.out.println("acc2= " + acc2);
		System.out.println("acc3= " + acc3);
	}
}
