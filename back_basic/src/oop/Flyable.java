package oop;

public interface Flyable {
	public static final String staticFly = "staticFly";

	public void fly();

	public default void landing() {
		System.out.println("Landing");
		run();
	}

	private void run() { //프라이빗은 여기서만 사용함.
		System.out.println("Flyable- Run!!");
	}

	interface Clickable {
		public void click();
	}
}
