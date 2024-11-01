package com.hana.greetings;

public class Hi {
	public static void main(String[] args) {
		playSwitch(95);
		playSwitch(70);

	}

	private static void playSwitch(int score) {
		final String grade = score > 90 ? "A" : "B";
		switch (grade) {
			case "A" -> System.out.println("Excellent!");
			case "B" -> System.out.println("Great!");
			default -> System.out.println("Not a valid grade");
		}
	}
}
