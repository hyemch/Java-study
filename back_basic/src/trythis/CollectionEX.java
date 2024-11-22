package trythis;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionEX {
	private static final String[] MOVIES = {"트랜스포머", "스타워즈", "매트릭스", "터미네이터", "아바타"};

	public static void main(String[] args) {
		List<String> list = new LinkedList<>(Arrays.asList(MOVIES));
		System.out.println(list);
		Collections.sort(list);
		System.out.println(list);
		for (String str : list) {
			System.out.println(str);
		}
		Collections.reverse(list);
		System.out.println(list);
	}
	
}
