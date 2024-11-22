package trythis.shape;

public class Figure<T extends Shape & Resizeable> {
	T figure;
	static int sid;

	public Figure(T figure) {
		this.figure = figure;

	}

	public void print() {
		System.out.printf("perimeter=%.1f, Area=%.1f", 0.0, 0.0);
		double perimeter = 0;
		if (this.figure instanceof Circle circle) {
			perimeter = circle.getPerimeter();
		} else if (this.figure instanceof Rectangle rectangle) {
			perimeter = rectangle.getPerimeter();
		}
		System.out.printf("perimeter=%.1f, Area=%.1f%n", perimeter, this.figure.calArea());
	}
}
