package trythis.school;

import java.io.InputStream;
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
		System.out.print(message);
		return this.scanner.nextInt();
	}

	public double scanDouble(String message) {
		System.out.print(message);
		return this.scanner.nextDouble();
	}

	public String scan(String message) {
		System.out.print(message);
		return this.scanner.next();
	}

	public String scanLine(String message) {
		System.out.println(message);
		return this.scanner.nextLine();
	}

	public void close() {
		this.scanner.close();
	}

	public static void main(String[] args) {
		MyScanner myscan = new MyScanner();
		int intr = myscan.scanInt("xxx: ");
		System.out.println("intr = " + intr);
	}
}
