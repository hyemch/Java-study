package onehanabank;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyScanner {
	private final Scanner scanner;

	public MyScanner() {
		this(System.in);
	}

	public MyScanner(InputStream inputStreams) {
		this.scanner = new Scanner(inputStreams);
	}

	public int scanInt(String message) {
		int value = 0;
		boolean valid = false;
		while (!valid) {
			System.out.print(message);
			try {
				value = this.scanner.nextInt();
				valid = true;
				this.scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\n잘못된 입력입니다. 정수를 입력하세요.");
				this.scanner.nextLine();
			}
		}
		return value;
	}

	public double scanDouble(String message) {
		double value = 0;
		boolean valid = false;
		while (!valid) {
			System.out.print(message);
			try {
				value = this.scanner.nextDouble();
				valid = true;
				this.scanner.nextLine();
			} catch (InputMismatchException e) {
				System.out.println("\n잘못된 입력입니다. 숫자를 입력하세요.");
				this.scanner.nextLine();
			}
		}
		return value;
	}

	// public String scan(String message) {
	// 	System.out.print(message);
	// 	return this.scanner.next();
	// }

	public String scanLine(String message) {
		System.out.println(message);
		// String input = this.scanner.nextLine();
		// if (input.isEmpty() || input.equals("0")) {
		// 	return null;
		// }
		return this.scanner.nextLine();
	}

	public void close() {
		this.scanner.close();
	}

}