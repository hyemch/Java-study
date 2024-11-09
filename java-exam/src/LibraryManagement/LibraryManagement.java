package LibraryManagement;

import java.util.ArrayList;
import java.util.List;

public class LibraryManagement {
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String PINK_LINE = "\u001B[38;5;218m";


	private Customer customer;
	private MyScanner scanner;

	public LibraryManagement() {
		scanner = new MyScanner();
		// 장바구니 생성
		// 책 정보 추가
	}

	public void inputCustomerInfo() {
		String name = this.scanner.scanLine("당신의 이름을 입력하세요: ");
		String contact = this.scanner.scan("연락처를 입력하세요: ");
		customer = new Customer(name, contact);
		System.out.println("\n고객 정보가 저장되었습니다.");
	}

	public void showDisplay() {
		String menu = PINK_LINE +
			"\n*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*\n" + COLOR_RESET +
			"""
			오늘의 선택, 코난문고
			영원한 스테디셀러, 명탐정 코난 시리즈를 만나보세요~
			""" + PINK_LINE +
			"*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*\n" + COLOR_RESET +
			"""
			1. 고객정보 확인하기
			2. 장바구니 상품목록보기
			3. 장바구니에 항목추가하기
			4. 장바구니의 항목 삭제하기
			5. 장바구니 비우기
			6. 영수증 표시하기
			7. 종료
			""" + PINK_LINE +
			"*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*" + COLOR_RESET;
		System.out.println(menu);
	}

	public void startLibraryManagement() {
		inputCustomerInfo();
		while (true) {
			showDisplay();
			int selectMenu = scanner.scanInt("메뉴 번호를 선택해주세요: ");
			// scanner.scanLine("");
			switch (selectMenu) {

				case 1 -> System.out.println(customer.toString());
				default -> System.out.println("잘못된 입력입니다.");
			}
		}
	}

	public static void main(String[] args) {

		LibraryManagement libraryManagement = new LibraryManagement();
		libraryManagement.startLibraryManagement();
		// libraryManagement.inputCustomerInfo();
		// System.out.println(libraryManagement.customer.toString());
		// libraryManagement.showDisplay();
	}
}
