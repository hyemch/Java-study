package trythis;

public class TryInvoiceItem {
	public static void main(String[] args) {
		InvoiceItem item1 = new InvoiceItem("No1", "coke", 400, 400);
		InvoiceItem item2 = new InvoiceItem("No2", "sprite", 500, 400);
		InvoiceItem item3 = new InvoiceItem("No3", "redBull", 1000, 200);

		System.out.println("item1: " + item1);
		System.out.println("item2: " + item2);
		System.out.println("item3: " + item3);
	}
}
