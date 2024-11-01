package trythis;

public class Countdown {
	public static void main(String[] args) {
		countdown(5);
		countdown(1);

	}

	private static void countdown(int num) {
		System.out.println("카운드다운 시작!");
		for (int i = num; i > 0; i--) {
			System.out.println(i + "..");
		}
		System.out.println("발사!!");

	}
}
