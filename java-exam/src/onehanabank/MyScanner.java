package onehanabank;

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

	public String scanLine(String message) {
		System.out.println(message);
		return this.scanner.nextLine();
	}

	public void close() {
		this.scanner.close();
	}
}
