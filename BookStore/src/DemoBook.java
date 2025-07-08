public class DemoBook extends Book {

    public DemoBook(String isbn, String title, double price, int publishYear) {
        super(isbn, title, publishYear, price);
    }

    @Override
    public boolean isAvailableForSale() {
        return false;
    }

    @Override
    public void buy(String isbn, int quantity, String email, String address) {
            throw new IllegalArgumentException("Cannot buy Demo Books");
    }   

}
