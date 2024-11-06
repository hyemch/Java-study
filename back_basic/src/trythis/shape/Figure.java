package trythis.shape;

public class Figure<T> {
	T shape;

	public Figure(T shape) {
		this.shape = shape;

	}

	public void print() {
		System.out.printf("perimeter=%.1f, Area=%.1f", 0.0, 0.0);
	}
}
