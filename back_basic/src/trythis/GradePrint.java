package trythis;

import java.util.Arrays;
import java.util.Scanner;

public class GradePrint {
	public static void main(String[] args) {
		// gradePrint();

		int[] num1 = {1, 2, 3, 4, 5};
		int[] num2 = new int[3];
		System.arraycopy(num1, 2, num2, 0, 3);
		System.out.println("Arrays.toNum2 = " + Arrays.toString(num2));
	}

	private static void gradePrint() {
		Scanner scan = new Scanner(System.in);
		System.out.println("학생수를 입력하세요: ");
		int num = scan.nextInt();
		int[] scores = new int[num];
		for (int i = 0; i < num; i++) {
			System.out.println((i + 1) + "번 학생의 점수를 입력하세요: ");
			scores[i] = scan.nextInt();
		}
		System.out.println(num + "명의 학생 성적은 다음과 같습니다.");
		// System.out.println(Arrays.toString(scores));

		for (int i = 0; i < num; i++) {
			System.out.print(scores[i] + " ");
		}
		System.out.println();

		for (int i = 0; i < num; i++) {
			String grade = calGrade(scores[i]);
			System.out.println((i + 1) + "번 학생의 등급은 " + grade + "입니다.");
		}
		scan.close();
	}

	private static String calGrade(int num) {
		if (num >= 90) {
			return "A";
		} else if (num >= 80) {
			return "B";
		} else if (num >= 70) {
			return "C";
		} else {
			return "D";
		}
	}
}
