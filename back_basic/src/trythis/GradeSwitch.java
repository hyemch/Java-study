package trythis;

import java.util.Scanner;

public class GradeSwitch {
	public static void main(String[] args) {
		gradeSwitch();
		commentSwitch();
	}

	private static void commentSwitch() {
		System.out.print("학점을 입력하세요 -->> ");
		Scanner scan = new Scanner(System.in);
		String grade = scan.next();

		switch (grade) {
			case ("A"), ("B") -> System.out.println("참 잘했어요!");
			case ("C"), ("D") -> System.out.println("좀 더 노력하세요!");
			case ("F") -> System.out.println("다음 학기에 다시 만나요!");
		}
	}

	private static void gradeSwitch() {
		System.out.print("점수를 입력하세요 -->> ");
		Scanner scan = new Scanner(System.in);
		int score = scan.nextInt();

		switch ((score / 10)) {
			// case 10: case:9 System.out.printIn("A") 이런식으로도 사용 가능
			case (10), (9) -> System.out.println("A");
			case (8) -> System.out.println("B");
			case (7) -> System.out.println("C");
			case (6) -> System.out.println("D");
			default -> System.out.println("F");
		}
	}
}
