package oop;

import java.util.Objects;

public class Person extends SuperPerson {
	private double age;
	private final String addr;

	public Person() {
		this("엄마의 아이", 1);
		System.out.println("Person1");
	}

	public Person(String name, int age) {
		this(name, age, "");
		//super();
		System.out.println("Person2");
	}

	public Person(String name, int age, String addr) {
		super(name, age);
		this.addr = addr;
	}

	@Override
	public int getAge() {
		return (int)age;
	}

	public String getAddr() {
		return addr;
	}

	public void walk() {
		System.out.println("Walking!!");
	}

	@Override
	public boolean equals(Object person) {
		return super.equals(person) && ((Person)person).getAddr().equals(this.getAddr());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), addr);
	}

	@Override
	public String toString() {
		return "Person{ name='%s', age=%d, addr='%s'}".formatted(getName(), getAge(), getAddr());
	}
}
