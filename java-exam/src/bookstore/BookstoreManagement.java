package bookstore;

import java.util.ArrayList;

public class BookstoreManagement {
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String PINK_LINE = "\u001B[38;5;218m";
	public static final String BLUE = "\u001B[38;5;39m";

	private MyScanner scanner;
	private Customer customer;
	private ArrayList<Book> books;
	private Cart cart;

	public BookstoreManagement() {
		scanner = new MyScanner();
		books = new ArrayList<>();
		cart = new Cart();
		books.add(new Book(1, "셜록홈즈", 20000, "코난도일", "그 누구도 뛰어넘지 못했던 추리 소설의 고전", "추리소설", "2018/10/08"));
		books.add(new Book(2, "도리안 그레이의 초상", 16000, "오스카 와일드", "예술을 위한 예술", "고전소설", "2022/01/22"));
		books.add(
			new Book(3, "쥐덫", 27000, "애거서크리스티", "폭설 속에 갇힌 몽스웰 여관 - 네 명의 손님과 주인 부부, 그리고 한 명의 형사", "추리소설", "2019/11/11"));

		// 장바구니 생성
		// 책 정보 추가
	}

	public void inputCustomerInfo() {
		String name = this.scanner.scanLine("당신의 이름을 입력하세요: ");
		String contact = this.scanner.scanLine("연락처를 입력하세요: ");
		customer = new Customer(name, contact);
		System.out.println("\n고객 정보가 저장되었습니다.");
	}

	public void showDisplay() {
		String menu = PINK_LINE
			+ "\n*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*\n" + COLOR_RESET
			+ """
			오늘의 선택, 코난문고
			영원한 스테디셀러, 명탐정 코난 시리즈를 만나보세요~
			""" + PINK_LINE
			+ "*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*\n" + COLOR_RESET
			+ """
			1. 고객정보 확인하기       2. 장바구니 상품목록보기
			3. 장바구니에 항목추가하기   4. 장바구니의 항목 삭제하기
			5. 장바구니 비우기         6. 영수증 표시하기
			7. 종료
			""" + PINK_LINE
			+ "*‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧˚♡˚‧｡♥｡‧*" + COLOR_RESET;
		System.out.println(menu);
	}

	public void startLibraryManagement() {
		while (true) {
			showDisplay();
			int choice = scanner.scanInt("메뉴 번호를 선택해주세요: ");
			switch (choice) {
				case 1 -> {
					System.out.println(customer.toString());
				}
				case 2 -> {
					cart.printCart();
				}
				case 3 -> {
					cart.addBook(books);
				}
				case 4 -> {
					cart.removeBook();
				}
				case 5 -> {
					cart.clearCart();
				}
				case 6 -> {
					cart.printReceipt(customer);
				}
				case 7 -> {
					System.out.println("프로그램이 종료되었습니다.");
					scanner.close();
					return;
				}
				default -> {
					System.out.println("잘못된 입력입니다. 메뉴 번호를 선택해주세요!");
				}
			}
		}
	}

	public static void main(String[] args) {

		BookstoreManagement bookstoreManagement = new BookstoreManagement();
		bookstoreManagement.inputCustomerInfo();
		// bookstoreManagement.startLibraryManagement();
		while (true) {
			bookstoreManagement.showDisplay();
			int choice = bookstoreManagement.scanner.scanInt("메뉴 번호를 선택해주세요: ");
			switch (choice) {
				case 1 -> {
					System.out.println(bookstoreManagement.customer.toString());
				}
				case 2 -> {
					bookstoreManagement.cart.printCart();
				}
				case 3 -> {
					bookstoreManagement.cart.addBook(bookstoreManagement.books);
				}
				case 4 -> {
					bookstoreManagement.cart.removeBook();
				}
				case 5 -> {
					bookstoreManagement.cart.clearCart();
				}
				case 6 -> {
					bookstoreManagement.cart.printReceipt(bookstoreManagement.customer);
				}
				case 7 -> {
					System.out.println("프로그램이 종료되었습니다.");
					bookstoreManagement.scanner.close();
					return;
				}
				default -> {
					System.out.println("잘못된 입력입니다. 메뉴 번호를 선택해주세요!");
				}
			}
		}
	}
}
