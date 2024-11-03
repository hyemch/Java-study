package oop;

import java.util.Objects;

// public class SuperPerson {
// 	public static void main(String[] args) {
// 		Person hong = new Person("Hong", 18);
// 		Person yun = new Person("Yun", 18);
// 		System.out.println("hong = " + hong);
// 	}
// }

public class Person {
	private String name;

	{
		name = "Hong";
	}

	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override //오버라이드 표시해주면 부모에서 찾지 않는다.
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		Person person = (Person)obj;
		return age == person.age && Objects.equals(name, person.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, age);
	}

	@Override
	public String toString() {
		return "Person{" + "name='" + name + '\'' + ", age=" + age + '}';
	}

	public static void main(String[] args) {
		Person hong = new Person("Hong", 18);
		Person yun = new Person("Yun", 18);
		System.out.println("hong = " + hong);
	}
}
