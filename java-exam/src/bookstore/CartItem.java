package bookstore;

public class CartItem {
	private final Book book;
	private int quantity;

	public CartItem(Book book, int quantity) {
		this.book = book;
		this.quantity = quantity;
	}

	public Book getBook() {
		return book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void increaseQuantity(int quantity) {
		this.quantity += quantity;
	}

	public void decreaseQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public int calAmount() {
		return book.getPrice() * quantity;
	}

	@Override
	public String toString() {
		return " %12s | %12d | %,10d원 ".formatted(book.getId(), this.quantity, calAmount());
	}
}
