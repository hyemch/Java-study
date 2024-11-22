package trythis.school;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class School {
	private static final boolean isDeg = true;
	private List<Student> students;

	public School() {
		this.students = new ArrayList<>();
		if (isDeg) {
			for (int i = 0; i < 5; i++) {
				int score = (int)(Math.random() * 10 * 10);
				students.add(new Student("hong" + i, i, score));
			}
		}
		Collections.shuffle(students);
	}

	@FunctionalInterface
	interface Add {
		int add(int a, int b);
	}

	interface NegativeX {
		int neg(int x);
	}

	public School(List<Student> students) {
		this.students = students;
	}

	public void addStudent(Student student) {
		this.students.add(student);
	}

	// private static String getGrade(Integer score) {
	// 	String grade = "F";
	// 	if (score >= 90) {
	// 		grade = "A";
	// 	} else if (score >= 80) {
	// 		grade = "B";
	// 	} else if (score >= 70) {
	// 		grade = "C";
	// 	} else if (score >= 60) {
	// 		grade = "D";
	// 	}
	// 	return grade;
	// }

	public void studentsSorted() {
		System.out.println("이름순: ");
		students.stream().sorted(Comparator.comparing(Student::getName)).forEach(System.out::println);

		System.out.println("학번순: ");
		students.stream().sorted(Comparator.comparing(Student::getId)).forEach(System.out::println);

		System.out.println("점수순: ");
		students.stream().sorted(Comparator.comparing(Student::getScore)).forEach(System.out::println);
	}

	public void printScroesAndGrades() {
		System.out.println("[성적과 학점]");
		students.stream()
			.map(student -> String.format("학생:%s, 점수: %d, 학점: %s", student.getName(), student.getScore(),
				student.getGrade(student.getScore())))
			.forEach(System.out::println);
	}

	public void printStudentHigherB() {
		System.out.println("[B학점 이상인 학생]");
		students.stream()
			.filter(student -> Objects.equals(student.getGrade(student.getScore()), "B") || Objects.equals(
				student.getGrade(student.getScore()), "A"))
			.forEach(System.out::println);
	}

	@Override
	public String toString() {
		return super.toString();
	}

	public static void main(String[] args) {
		School school = new School();
		// school.students.add(new Student("홍길동", 1, "010-1234-5555", 55));
		// school.students.add(new Student("김철수", 2, "010-1234-6666", 85));
		// school.students.add(new Student("이영희", 3, "010-1234-7777", 75));
		// school.students.add(new Student("박동수", 4, "010-1234-8888", 60));
		// school.students.add(new Student("최선미", 5, "010-1234-9999", 90));

		school.printStudentHigherB();
		school.printScroesAndGrades();
		for (Student student : school.students) {
			System.out.println(student);
		}
	}
	//
	// public static final int STUDENT_COUNT = 10;
	//
	// public static void main(String[] args) {
	// 	List<Integer> scores = new ArrayList<>(STUDENT_COUNT);
	//
	// 	Scanner scanner = new Scanner(System.in);
	// 	int min = 101;
	// 	int max = -1;
	// 	System.out.println("점수를 입력해주세요 (음수 입력 시 종료): ");
	// 	while (true) {
	// 		int score = scanner.nextInt();
	// 		if (score < 0) {
	// 			System.out.println("점수 입력이 종료되었습니다.");
	// 			break;
	// 		}
	// 		scores.add(score);
	// 		if (score < min)
	// 			min = score;
	// 		if (score > max)
	// 			max = score;
	// 	}
	// 	System.out.println("학생들의 성적: " + scores);
	//
	// 	int sum = 0;
	// 	int size = scores.size();
	//
	// 	Iterator<Integer> itScores = scores.iterator();
	// 	while (itScores.hasNext()) {
	// 		sum += itScores.next();
	// 		System.out.println("iter sum: " + sum);
	// 	}
	// 	sum = 0;
	//
	// 	for (int i = 0; i < size; i++) {
	// 		int score = scores.get(i);
	// 		sum += score;
	// 		System.out.printf("%d 학생의 성적은 %d이며 학점은 %s 이다.\n", (i + 1), score, getGrade(score));
	// 	}
	// 	System.out.printf("평균은 %.1f이며 최고점수는 %d 최저점수는 %d이다.\n", (double)sum / size, max, min);
	// }
}
