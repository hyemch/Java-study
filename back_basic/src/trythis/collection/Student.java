package trythis.collection;

import java.util.Objects;

public class Student implements GradeCalculator {
	private int score;
	private String grade;

	public Student(int score) {
		this.score = score;
		this.grade = getGrade();
	}

	public Student() {
		this(0);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Student student = (Student)obj;
		return score == student.score;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(score);
	}

	@Override
	public void calGrade() {
		if (this.score >= 90)
			this.grade = "A";
		else if (this.score >= 80)
			this.grade = "B";
		else if (this.score >= 70)
			this.grade = "C";
		else
			this.grade = "D";
	}

	@Override
	public String toString() {
		return "학생의 성적은 %d점이며, 학점은 %s이다.".formatted(score, grade);
	}
}
