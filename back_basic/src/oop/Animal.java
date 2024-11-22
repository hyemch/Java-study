package oop;

public abstract class Animal {
	public static String staticName = "staticAnimal";
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	private String name;

	public void bark() {
		System.out.println("BARK!!");
	}

	@Override
	public String toString() {
		return "Animal{age=%d, name='%s'}".formatted(age, name);
	}

	abstract void walk();

}
