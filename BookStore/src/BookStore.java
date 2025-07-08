import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookStore {
    List<Book> inventory = new ArrayList<>();

    public Book getBook(String isbn) {
        return inventory.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst()
                .orElse(null);
    }

    public void addBook(Book book) {
        Book existingBook = getBook(book.isbn);
        if (existingBook != null) {
            if (book instanceof PaperBook) {
                ((PaperBook) existingBook)
                        .setStock(((PaperBook) existingBook).getStock() + ((PaperBook) book).getStock());
                System.out.println("Updated stock for existing paper book: " + existingBook.getTitle()
                        + " to " + ((PaperBook) existingBook).getStock());
            }
            throw new IllegalArgumentException("Book with this ISBN already exists.");
        } else {
            inventory.add(book);
        }
    }

    public Book removeBook(String isbn) {
        Book book = inventory.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        inventory.remove(book);
        System.out.println("Removed book named: " + book.getTitle());
        return book;
    }

    public List<Book> removeOutdated(int passedYears) {
        List<Book> outdatedBooks = inventory.stream()
                .filter(b -> (LocalDate.now().getYear() - b.getPublishYear()) > passedYears)
                .collect(Collectors.toList());
        if (outdatedBooks.isEmpty()) {
            throw new IllegalArgumentException("No outdated books found.");
        }
        inventory.removeAll(outdatedBooks);
        System.out.println("Removed outdated books: " + outdatedBooks.stream()
                .map(Book::getTitle).collect(Collectors.joining(", ")));
        return outdatedBooks;
    }

    public double buyBook(String isbn, int quantity, String email, String address) {
        Book book = inventory.stream().filter(b -> b.getIsbn().equals(isbn)).findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Book not found."));
        if (!book.isAvailableForSale()) {
            throw new IllegalArgumentException("This book is not available for sale.");
        }
        book.buy(isbn, quantity, email, address);

        return quantity * book.getPrice();
    }
}
