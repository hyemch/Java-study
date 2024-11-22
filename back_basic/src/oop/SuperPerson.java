package oop;

import java.util.Objects;

public class SuperPerson {
	private final String name;
	protected final int age;

	public SuperPerson(String name, int age) {
		System.out.println("super2");
		this.name = name;
		this.age = age;
	}

	public SuperPerson() {
		// 	this("",0);
		this.name = "";
		this.age = 0;
		System.out.println("super1");
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		SuperPerson that = (SuperPerson)obj;
		return age == that.age && Objects.equals(name, that.name);
	}

	@Override
	public int hashCode() {
		System.out.println("age=" + age);
		return Objects.hash(name, age);
	}

	@Override
	public String toString() {
		return "SuperPerson{" + "name=" + name + ", age=" + age + '}';
	}

	public static void main(String[] args) {
		// Person hong = new Person("Hong", 33);
		// System.out.println("hong = " + hong);
		
		SuperPerson ps = Math.random() > 0.5 ? new Student() : new Person();

		if (ps instanceof Person) {
			System.out.println("ps = " + ps.getClass().getSimpleName());
			((Person)ps).walk();
		} else {
			System.out.println("ps = " + ps.getClass().getSimpleName());
		}

		if (ps instanceof Person psPerson) {
			psPerson.walk();
		}

	}
}
