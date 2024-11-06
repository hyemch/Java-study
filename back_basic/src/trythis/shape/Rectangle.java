package trythis.shape;

public class Rectangle extends Shape {
	private double height;
	private double width;

	public Rectangle() {
		height = 1.0;
		width = 1.0;
	}

	public Rectangle(double height, double width) {
		this.height = height;
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public double getWidth() {
		return width;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getArea() {
		return width * height;
	}

	public double getPerimeter() {
		return 2 * (width + height);
	}

	@Override
	public double calArea() {
		return width * height;
	}

	public String toString() {
		return "Rectangle[height=%.1f, width=%.1f]의 둘레는 %.1f, 면적은 %.1f".formatted(height, width, getPerimeter(),
			getArea());
	}

}
