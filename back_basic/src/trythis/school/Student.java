package trythis.school;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Student {
	private String name = "";
	private int id = 0;
	private String phoneNo = "";
	private int score = 0;

	public Student(StringTokenizer tokenizer) {
		List<String> tokens = new ArrayList<>();
		while (tokenizer.hasMoreTokens()) {
			tokens.add(tokenizer.nextToken());
		}
		if (!tokens.isEmpty()) {
			this.name = tokens.getFirst();
		}
		try {
			// this.name = tokens.get(0);
			this.id = Integer.parseInt(tokens.get(1));
			this.phoneNo = tokens.get(2);
			this.score = Integer.parseInt(tokens.get(3));
		} catch (IndexOutOfBoundsException ioe) {
			System.out.println(ioe.getMessage());

		}
	}

	public Student(String name, int id, String phoneNo) {
		this.name = name;
		this.id = id;
		this.phoneNo = phoneNo;
	}

	public Student(String name, int id, int score) {
		this.name = name;
		this.id = id;
		this.score = score;
	}

	public Student(String name, int id, String phoneNo, int score) {
		// this.name = name;
		// this.id = id;
		// this.phoneNo = phoneNo;
		this(name, id, phoneNo);
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGrade(Integer score) {
		String grade = "F";
		if (score >= 90) {
			grade = "A";
		} else if (score >= 80) {
			grade = "B";
		} else if (score >= 70) {
			grade = "C";
		} else if (score >= 60) {
			grade = "D";
		}
		return grade;
	}

	@Override
	public String toString() {
		return "%s(%d) : %s %d %s".formatted(name, id, phoneNo, score, getGrade(score));
	}

	// public static void main(String[] args) {
	// 	Map<String, Student> map = new HashMap<>();
	// 	MyScanner scanner = new MyScanner();
	// 	System.out.println("이름 아이디 전화번호 점수 순으로 입력해주세요. \n");
	// 	while (true) {
	// 		StringTokenizer token = new StringTokenizer(scanner.scanLine(""), " ");
	// 		if (token.countTokens() == 0) {
	// 			break;
	// 		}
	// 		Student student = new Student(token);
	// 		map.put(student.getName(), student);
	// 	}
	// 	System.out.println("등록된 학생 수 = " + map.size());
	// 	for (String name : map.keySet()) {
	// 		Student student = map.get(name);
	// 		System.out.println(student);
	// 	}
	// }
}
