package bookstore;

public class Main {
	public static void main(String[] args) {

		BookstoreManagement bookstoreManagement = new BookstoreManagement();
		bookstoreManagement.inputCustomerInfo();
		// bookstoreManagement.startLibraryManagement();
		while (true) {
			bookstoreManagement.showDisplay();
			int choice = bookstoreManagement.getScanner().scanInt("메뉴 번호를 선택해주세요: ");
			switch (choice) {
				case 1 -> {
					System.out.println(bookstoreManagement.getCustomer().toString());
				}
				case 2 -> {
					bookstoreManagement.getCart().printCart();
				}
				case 3 -> {
					bookstoreManagement.getCart().addBook(bookstoreManagement.getBooks());
				}
				case 4 -> {
					bookstoreManagement.getCart().removeBook();
				}
				case 5 -> {
					bookstoreManagement.getCart().clearCart();
				}
				case 6 -> {
					bookstoreManagement.getCart().printReceipt(bookstoreManagement.getCustomer());
				}
				case 7 -> {
					System.out.println("프로그램이 종료되었습니다.");
					bookstoreManagement.getScanner().close();
					return;
				}
				default -> {
					System.out.println("잘못된 입력입니다. 메뉴 번호를 선택해주세요!");
				}
			}
		}
	}
}
