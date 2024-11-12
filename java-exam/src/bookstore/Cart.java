package bookstore;

import static bookstore.BookstoreManagement.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Cart {
	MyScanner scanner = new MyScanner();
	private final ArrayList<CartItem> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public void addBook(ArrayList<Book> books) {
		books.forEach(book -> System.out.println(book.toString()));
		int bookId = scanner.scanInt("추가할 도서의 ID를 입력하세요: ");
		for (Book book : books) {
			if (book.getId() == bookId) {
				String check = scanner.scanLine("장바구니에 추가하시겠습니까? [Y | N]: ");
				if (check.equalsIgnoreCase("Y")) {
					for (CartItem item : items) {
						if (item.getBook().getId() == bookId) {
							System.out.println("장바구니에 [ " + bookId + " ] 도서의 수량이 증가되었습니다.");
							item.increaseQuantity(1);
							return;
						}
					}
					items.add(new CartItem(book, 1));
					System.out.println("장바구니에 [ " + bookId + " ] 도서가 추가되었습니다.");
				} else {
					System.out.println("장바구니에 상품을 추가하지 않았습니다.");
				}
				return;
			}
		}
		System.out.println("입력한 ID에 해당하는 도서를 찾을 수 없습니다.");
	}

	public void removeBook() {
		printCart();
		int bookId = scanner.scanInt("삭제할 도서의 ID를 입력하세요: ");
		for (CartItem item : items) {
			if (item.getBook().getId() == bookId) {
				if (item.getQuantity() > 1) {
					System.out.println("장바구니에 [ " + bookId + " ] 도서의 수량이 감소되었습니다.");

					item.decreaseQuantity(1);
				} else {
					System.out.println("장바구니에 [ " + bookId + " ] 도서가 삭제되었습니다.");
					items.remove(item);
				}
				return;
			}
		}
		System.out.println("입력한 ID에 해당하는 도서를 찾을 수 없습니다.");
	}

	public void clearCart() {
		items.clear();
		System.out.println("\n장바구니를 비웠습니다.");
	}

	public int totalAmount() {
		int total = 0;
		for (CartItem item : items) {
			total += item.calAmount();
		}
		return total;
	}

	public void printCart() {
		if (items.isEmpty()) {
			System.out.println("\n장바구니가 비어있습니다!");
		} else {
			System.out.println(PINK_LINE
				+ "✧··········*⋆⁺₊⋆장바구니 상품 목록⋆⁺₊⋆*··········✧\n"
				+ COLOR_RESET
				+ "    도서 ID    |      수량      |      가격     \n"
				+ "✧··········*⋆⁺₊⋆ ⋆⁺₊⋆ ❤ ⋆⁺₊⋆ ⋆⁺₊⋆*··········✧");

			for (CartItem item : items) {
				System.out.println(item.toString());
			}
			System.out.println("✧··········*⋆⁺₊⋆ ⋆⁺₊⋆ ❤ ⋆⁺₊⋆ ⋆⁺₊⋆*··········✧");
		}
	}

	public void printReceipt(Customer customer) {
		String deliveryName = customer.getName();
		String deliveryContract = customer.getContract();
		String check = scanner.scanLine("배송받을 분은 고객정보와 같습니까? [Y | N]: ");
		if (check.equalsIgnoreCase("N")) {
			deliveryName = scanner.scanLine("배송받을 고객의 이름을 입력해주세요: ");
			deliveryContract = scanner.scanLine("배송받을 고객의 연락처를 입력해주세요: ");
		}
		String address = scanner.scanLine("배송지를 입력해주세요: ");
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(PINK_LINE + "✧··········*⋆⁺₊⋆배송받을 고객 정보⋆⁺₊⋆*··········✧\n"
			+ COLOR_RESET
			+ """
			고객명 : %s   연락처 : %S
			배송지 : %s
			발송일 : %s""".formatted(deliveryName, deliveryContract, address, formatter.format(now)));
		printCart();
		System.out.printf("✧··········*⋆⁺₊⋆" + BLUE + " 총액 %,d원 " + COLOR_RESET + "⋆⁺₊⋆*··········✧%n",
			totalAmount());
	}
}
