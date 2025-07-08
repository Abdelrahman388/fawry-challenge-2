public class ShippingService {
    private String address;

    public ShippingService(String address) {
        this.address = address;
    }

    public void ship() {
        System.out.println("Shipping paper book to address: " + address);
    }
}
