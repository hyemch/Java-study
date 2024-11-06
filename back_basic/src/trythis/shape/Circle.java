package trythis;

public class Circle {
	private double radius;
	private String color;

	public Circle() {
		radius = 1.0;
		color = "red";
	}

	public Circle(double radius) {
		this.radius = radius;
		this.color = "red";
	}

	public double getRadius() {
		return radius;
	}

	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public double getCircumference() {
		return 2 * Math.PI * radius;
	}

	public String toString() {
		return "Circle[radius=%.1f]의 둘레는 %.2f, 면적은 %.2f".formatted(radius, this.getCircumference(), this.getArea());
	}
}
