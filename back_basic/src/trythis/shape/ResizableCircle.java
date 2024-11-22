package trythis.shape;

public class ResizableCircle extends Circle implements Resizeable {
	public ResizableCircle(double radius) {
		super(radius);
	}

	@Override
	public void resize(int percent) {
		setRadius(getRadius() + getRadius() * percent / 100);
	}

	@Override
	public String toString() {
		return "ResizableCircle[radius=%.2f]의 둘레는 %.1f의 면적은 %.1f".formatted(getRadius(), getPerimeter(), getArea());
	}

	public static void main(String[] args) {
		Circle circle1 = new Circle(2);
		System.out.println("circle1 = " + circle1);
		ResizableCircle circle2 = new ResizableCircle(3);
		System.out.println("circle2 = " + circle2);
		System.out.println("-------------------------");

		circle2.resize(10);
		System.out.println("circle2 = " + circle2);

		Resizeable circle3 = Math.random() > 0.5 ? new ColorCircle(1, "red") : new ResizableCircle(2);
		Circle circle4 = Math.random() > 0.5 ? new Circle() : new ColorCircle(2, "blue");

		if (circle4 instanceof Resizeable x) {
			x.resize(20);
			System.out.println("x = " + x);
		}
	}
}
