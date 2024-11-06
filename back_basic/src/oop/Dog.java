package oop;

public class Dog extends Animal implements Flyable, Swimable {

	@Override
	void walk() {
		System.out.println("Dog walk");
	}

	@Override
	public void fly() {
		System.out.println("Dog fly by ears!!");
	}

	@Override
	public void landing() {
		Flyable.super.landing();
	}

	@Override
	public void swim() {
		System.out.println("Dog swims!!");
	}

	public static void main(String[] args) {
		Dog dog = new Dog();
		dog.fly();
		dog.landing();
		System.out.println("Animal.staticName= " + Animal.staticName);
		System.out.println("Flyable staticFly= " + Flyable.staticFly);

		Swimable animal = Math.random() > 0.5 ? new Dog() : new Cat();
		animal.swim();
	}
}
