public class ShippingService {
    private String address;

    public ShippingService(String address) {
        this.address = address;
    }

    public void ship(PaperBook book, int quantity) {
        System.out.println("Shipping " + quantity + " copies of " + book.getTitle() + " to address: " + address);
    }
}
