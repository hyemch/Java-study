package trythis.collection;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeManager {
	private static ArrayList<Student> students;

	public GradeManager() {
		students = new ArrayList<>();
	}

	public void inputScore() {
		Scanner scan = new Scanner(System.in);
		System.out.println("학생의 점수를 입력하세요.\n입력을 멈추려면 음수를 입력해주세요.");
		while (true) {
			System.out.println("점수 입력: ");
			int score = scan.nextInt();
			if (score < 0) {
				System.out.println("학생들의 점수입력이 종료되었습니다.");
				break;
			}
			students.add(new Student(score
			));
		}
	}

	public static ArrayList<Student> getStudents() {
		return students;
	}

	@Override
	public String toString() {
		return "학생들의 성적: %s".formatted(getStudents());
	}

	public static void main(String[] args) {
		GradeManager manager = new GradeManager();
		manager.inputScore();
		System.out.println(manager.toString());
	}
}
