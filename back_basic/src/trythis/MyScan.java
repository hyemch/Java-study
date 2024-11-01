package trythis;

import java.util.Scanner;

public class MyScan {
	public static void main(String[] args) {
		scanProfile();
		scanTemp();
	}

	private static void scanTemp() {
		Scanner scan = new Scanner(System.in);
		final int currTemp = 20;
		System.out.print("수심을 입력해주세요: ");
		int depth = scan.nextInt();
		double result = currTemp - (Math.floor((double)depth / 10)) * 0.7;
		System.out.println("수온: " + result);
	}

	private static void scanProfile() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your name: ");
		String name = scan.nextLine();
		System.out.println("Enter your address: ");
		String addr = scan.nextLine();
		System.out.println("Enter your age: ");
		int age = scan.nextInt();
		System.out.println("Enter your height: ");
		float height = scan.nextFloat();

		System.out.printf("이름: %s\n주소: %s\n나이: %d\n키: %4.1f\n", name, addr, age, height);

		System.out.println("이름: " + name);
		System.out.println("주소: " + addr);
		System.out.println("나이: " + age);
		System.out.println("키: " + height);
	}
}
