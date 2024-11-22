package bookstore;

public class Book {
	private int id = 0;
	private String title = "";
	private int price = 0;
	private String author = "";
	private String description = "";
	private String type = "";
	private String releaseDate = "";

	public Book(int id, String title, int price, String author, String description, String type, String releaseDate) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.author = author;
		this.description = description;
		this.type = type;
		this.releaseDate = releaseDate;
	}

	public int getId() {
		return this.id;
	}

	public int getPrice() {
		return this.price;
	}

	@Override
	public String toString() {
		return "%d | %s | %,d원 | %s | %s | %s | %s"
			.formatted(this.id, this.title, this.price, this.author,
				this.description, this.type, this.releaseDate);
	}
}
