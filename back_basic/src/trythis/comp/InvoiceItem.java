package trythis;

public class InvoiceItem {
	private final String id;
	private final String description;
	private int quantity; //양
	private double unitPrice;

	public InvoiceItem(String id, String description, int quantity, double unitPrice) {
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}

	public String getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTotalPrice() {
		return quantity * unitPrice;
	}

	public String toString() {
		return "InvoiceItem=[id=%s, description=%s, quantity=%d, unitPrice=%.1f]의 구매 총액은 %.1f".formatted(getId(),
			getDescription(), getQuantity(), getUnitPrice(), getTotalPrice());
	}
}
