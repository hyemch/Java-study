package trythis.shape;

import java.math.BigDecimal;

public abstract class Shape {
	public abstract double calArea();

	public static void main(String[] args) {
		Shape[] shapes = {new Circle(5.0), new Rectangle(3, 4), new Circle((1))};
		double sum = 0;
		for (Shape shape : shapes) {
			sum += shape.calArea();
		}
		System.out.printf("면적읜 합: %f\n", sum);
		System.out.println(0.1 + 0.2 + "\n");
		BigDecimal bd = BigDecimal.valueOf(0.1).add(new BigDecimal("0.2"));
		System.out.println(bd);
	}

}
