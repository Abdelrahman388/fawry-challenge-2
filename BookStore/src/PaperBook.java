public class PaperBook extends Book {
    private int stock;

    public PaperBook(String isbn, String title, double price, int publishYear, int stock) {
        super(isbn, title, publishYear, price);
        this.stock = stock;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }


    @Override
    public boolean isAvailableForSale() {
        return stock > 0;
    }

    @Override
    public void buy(String isbn, int quantity, String email, String address) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        if (quantity > stock) {
            throw new IllegalArgumentException("Not enough stock available.");
        }

        stock -= quantity;
        shipToAddress(address, quantity);
        System.out.println("Purchased " + quantity + " copies of " + getTitle() + " to be shipped to "
        + address);
    }
    
    private void shipToAddress(String address, int quantity) {
        ShippingService shippingService = new ShippingService(address);
        shippingService.ship(this, quantity);
    }

}
