
public abstract class Book {
    String isbn;
    String title;
    int publishYear;
    Double price;

    public Book(String isbn, String title, int publishYear, Double price) {
        this.isbn = isbn;
        this.title = title;
        this.publishYear = publishYear;
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public int getPublishYear() {
        return publishYear;
    }
    public Double getPrice() {
        return price;
    }


    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

    public abstract boolean isAvailableForSale();

    public abstract void buy(String isbn, int quantity, String email, String address) throws IllegalArgumentException;

}
