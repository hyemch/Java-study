package trythis.shape;

public class Circle extends Shape {
	private double radius;
	private final String color;

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

	protected void setRadius(double radius) {
		this.radius = radius;
	}

	public double getArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public double getCircumference() {
		return 2 * Math.PI * radius;
	}

	public double getPerimeter() {
		return Math.PI * 2 * radius;
	}

	@Override
	public double calArea() {
		return Math.PI * Math.pow(radius, 2);
	}

	public String toString() {
		return "Circle[radius=%.1f]의 둘레는 %.2f, 면적은 %.2f".formatted(radius, this.getPerimeter(), this.getArea());
	}

}
