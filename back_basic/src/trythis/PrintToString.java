package trythis;

import java.util.Scanner;

public class PrintToString {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		args = scan.nextLine().split(" ");
		if (args.length != 2) {
			System.out.println("문자열과 숫자열을 입력해주세요.");
		}
		int num = Integer.parseInt(args[1]);
		for (int i = 0; i < num; i++) {
			System.out.println(args[0]);
		}
		scan.close();
	}
}
