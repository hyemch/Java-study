package trythis.geomeric;

public class ResizableCircle extends Circle implements Resizable {
	public ResizableCircle(double radius) {
		super(radius);
	}

	@Override
	public void resize(int percent) {
		double value = this.getRadius() + getRadius() * (double)percent / 100;
		this.setRadius(value);
	}

	@Override
	public String toString() {
		return "Resize" + (super.toString());
	}

	public static void main(String[] args) {
		// ResizableCircle circle = new ResizableCircle(3);
		// circle.resize(10);
		// System.out.println(circle.toString());
		Circle circle2 = new ResizableCircle(3);
		((ResizableCircle)circle2).resize(10);
		System.out.println("circle2" + circle2.toString());
	}
}
