package trythis;

import trythis.comp.Employee;
import trythis.shape.Circle;
import trythis.shape.Rectangle;

public class TryThis {
	public static void main(String[] args) {
		Circle circle1 = new Circle();
		Circle circle2 = new Circle(2);
		Rectangle rectangle1 = new Rectangle();
		Rectangle rectangle2 = new Rectangle(3, 4);
		Employee employee1 = new Employee(1, "코난", 25000000);
		Employee employee2 = new Employee(2, "장미", 30000000);
		Employee employee3 = new Employee(3, "미란", 40000000);

		System.out.println("circle = " + circle1);
		System.out.println("circle = " + circle2);
		System.out.println("rectangle = " + rectangle1);
		System.out.println("rectangle = " + rectangle2);

		System.out.println("employee= " + employee1);
		System.out.println("employee= " + employee2);
		System.out.println("employee= " + employee3);

	}
}
