package oop;

public class Cat extends Animal implements Swimable {
	@Override
	void walk() {
		System.out.println("cat walk");
	}

	@Override
	public void swim() {
		System.out.println("cat swims!!");
	}
}
