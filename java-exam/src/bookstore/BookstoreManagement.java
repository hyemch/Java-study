package bookstore;

import java.util.ArrayList;

public class BookstoreManagement {
	public static final String COLOR_RESET = "\u001B[0m";
	public static final String PINK_LINE = "\u001B[38;5;218m";
	public static final String BLUE = "\u001B[38;5;39m";

	private final MyScanner scanner;
	private Customer customer;
	private final ArrayList<Book> books;
	private final Cart cart;

	public MyScanner getScanner() {
		return scanner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public ArrayList<Book> getBooks() {
		return books;
	}

	public Cart getCart() {
		return cart;
	}

	public BookstoreManagement() {
		scanner = new MyScanner();
		books = new ArrayList<>();
		cart = new Cart();
		books.add(new Book(1, "셜록홈즈", 20000, "코난도일", "그 누구도 뛰어넘지 못했던 추리 소설의 고전", "추리소설", "2018/10/08"));
		books.add(new Book(2, "도리안 그레이의 초상", 16000, "오스카 와일드", "예술을 위한 예술", "고전소설", "2022/01/22"));
		books.add(
			new Book(3, "쥐덫", 27000, "애거서크리스티", "폭설 속에 갇힌 몽스웰 여관 - 네 명의 손님과 주인 부부, 그리고 한 명의 형사", "추리소설", "2019/11/11"));
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
}
