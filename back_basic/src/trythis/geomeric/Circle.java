package trythis.geomeric;

public class Circle implements GeomericObject {
	private double radius;

	public Circle() {
		this.radius = 1.0;
	}

	public Circle(double radius) {
		this.radius = radius;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@Override
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double getArea() {
		return Math.PI * radius * radius;
	}

	@Override
	public String toString() {
		return "Circle[radius=%s]의 둘레는 %.1f, 면적은 %.1f\n".formatted(getRadius(), getPerimeter(), getArea());
	}

	public static void main(String[] args) {
		Circle circle = new Circle(2);
		System.out.println(circle.toString());
	}
}
