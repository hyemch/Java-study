package trythis.comp;

public class Employee {
	private int id;
	private String name;
	private int salary;

	public Employee(int id, String name, int salary) {
		this.id = id;
		this.name = name;
		this.salary = salary;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public int getAnnualSalary() {
		return salary * 12;
	}

	public int raiseSalary(int percent) {
		return (int)(salary * percent * 0.01);
	}

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", salary=%,d]의 연봉은 %,d 월급 인상 분은 %,d".formatted(
			getSalary(), getAnnualSalary(), raiseSalary(id * 10));
	}
}
