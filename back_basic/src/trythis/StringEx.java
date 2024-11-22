package trythis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.StringTokenizer;

public class StringEx {
	public static void main(String[] args) {
		// StringBuffer sb = new StringBuffer("This");
		StringBuilder sb = new StringBuilder("This");

		System.out.println(sb.hashCode());
		sb.append(" is a pencil");
		System.out.println(sb);

		sb.delete(8, 10);
		System.out.println(sb);

		sb.insert(7, " my");
		System.out.println(sb);

		sb.replace(8, 10, "your");
		System.out.println(sb);

		sb.setLength(5);
		System.out.println(sb);
		sb.reverse();
		System.out.println(sb);
		System.out.println(sb.hashCode());
		System.out.printf("capacity = %d%n", sb.capacity());

		String query = "name=conan&addr=rans's&age=10";
		StringTokenizer st = new StringTokenizer(query, "&=");
		while (st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
		String st1 = "홍길동/장화/홍련/콩쥐/팥쥐";
		StringTokenizer st2 = new StringTokenizer(st1, "/");
		while (st2.hasMoreTokens()) {
			System.out.println(st2.nextToken());
		}
		Random ran1 = new Random();
		for (int i = 0; i < 10; i++) {
			System.out.println(ran1.nextInt(10) + 1);
		}

		StringTokenizer tk1 = new StringTokenizer(st1, "/");
		try {
			String tmp;
			while ((tmp = tk1.nextToken()) != null) {
				System.out.println("tmp = " + tmp);
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getMessage());
		}

		Date now = new Date();
		String strNow1 = now.toString();
		System.out.println(strNow1);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh시 mm분 ss초");
		String strNow2 = sdf.format(now);
		System.out.println(strNow2);

		Calendar nowCal = Calendar.getInstance();
		nowCal.set(Calendar.HOUR_OF_DAY, 13);
		nowCal.set(Calendar.MINUTE, 30);
		System.out.println(nowCal.getTime());

		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int date = cal.get(Calendar.DATE);
		System.out.printf("%d-%02d-%02d%n", year, month + 1, date);
		System.out.println(sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, -5);
		System.out.println(sdf.format(cal.getTime()));

	}
}
