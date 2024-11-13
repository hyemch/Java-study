package trythis;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ComparatorEx {
	public static void main(String[] args) {
		String[] strings = {"트랜스포머", "스타워즈", "매트릭스", "터미네이터", "아바타"};
		Arrays.sort(strings, new Comparator<String>() {
			public int compare(String first, String second) {
				return first.length() - second.length();
			}
		});
		for (String string : strings) {
			System.out.println(string);
		}

		Comparator<String> comp = (first, second) -> second.length() - first.length();
		;

		Arrays.sort(strings, comp);

		for (String string : strings) {
			System.out.println(string);
		}

		System.out.println("------------------------");

		List<String> list = Arrays.asList(strings);
		System.out.printf("list=%s%n", list.getClass().getName());
		list.stream().map(String::length).filter(len -> len > 3).forEach(System.out::println);

		Negative nR = x -> -x;
		Add addR = Integer::sum;
		System.out.println(nR);
		System.out.println(addR);
	}

	interface Add {
		int add(int a, int b);
	}

	interface Negative {
		int neg(int x);
	}
}
