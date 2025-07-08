import java.util.List;

public class Test {
    public static void run() {
        System.out.println("==== Running BookStore Tests ====\n");

        testAddingBooks();
        testBuyingBooks();
        testRemovingBooks();
        testRemovingOutdatedBooks();

        System.out.println("\n==== All Tests Completed ====");
    }

    private static void testAddingBooks() {
        System.out.println("--- Testing Adding Books ---");
        BookStore store = new BookStore();

        Book book1 = new DemoBook("1234567890", "Demo Book 1", 19.99, 2023);
        Book book2 = new PaperBook("0987654321", "Paper Book 1", 29.99, 2022, 100);
        Book book3 = new EBook("1122334455", "E-Book 1", 9.99, 2021, "PDF");

        store.addBook(book1);
        store.addBook(book2);
        store.addBook(book3);
        System.out.println("Successfully added 3 different types of books");

        try {
            PaperBook duplicatePaper = new PaperBook("0987654321", "Paper Book 1", 29.99, 2022, 50);
            store.addBook(duplicatePaper);
        } catch (IllegalArgumentException e) {
            System.out.println("Handled duplicate book addition: " + e.getMessage());
        }

        try {
            EBook duplicateEBook = new EBook("1122334455", "E-Book 1", 9.99, 2021, "PDF");
            store.addBook(duplicateEBook);
        } catch (IllegalArgumentException e) {
            System.out.println("Handled duplicate EBook addition: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testBuyingBooks() {
        System.out.println("--- Testing Buying Books ---");
        BookStore store = new BookStore();

        Book demoBook = new DemoBook("DEMO001", "Demo Book 1", 19.99, 2023);
        Book paperBook = new PaperBook("PAPER001", "Paper Book 1", 29.99, 2022, 5);
        Book eBook = new EBook("EBOOK001", "E-Book 1", 9.99, 2021, "PDF");

        store.addBook(demoBook);
        store.addBook(paperBook);
        store.addBook(eBook);

        // Test successful purchases
        try {
            double cost1 = store.buyBook("PAPER001", 2, "customer@example.com", "123 Main St");
            System.out.println("Successfully bought paper book. Cost: $" + cost1);

            double cost2 = store.buyBook("EBOOK001", 1, "customer@example.com", "123 Main St");
            System.out.println("Successfully bought e-book. Cost: $" + cost2);
        } catch (Exception e) {
            System.out.println("Unexpected error in successful purchase: " + e.getMessage());
        }

        // Test buying demo book (should fail)
        try {
            store.buyBook("DEMO001", 1, "customer@example.com", "123 Main St");
            System.out.println("Should not be able to buy demo book");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly prevented demo book purchase: " + e.getMessage());
        }

        // Test buying non-existent book
        try {
            store.buyBook("NONEXISTENT", 1, "customer@example.com", "123 Main St");
            System.out.println("Should not be able to buy non-existent book");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled non-existent book: " + e.getMessage());
        }

        // Test buying more than available stock
        try {
            store.buyBook("PAPER001", 10, "customer@example.com", "123 Main St");
            System.out.println("Should not be able to buy more than available stock");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled insufficient stock: " + e.getMessage());
        }

        // Test invalid parameters
        testInvalidPurchaseParameters(store);

        System.out.println();
    }

    private static void testInvalidPurchaseParameters(BookStore store) {
        // Test invalid quantity
        try {
            store.buyBook("PAPER001", 0, "customer@example.com", "123 Main St");
            System.out.println("Should not allow zero quantity");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled zero quantity: " + e.getMessage());
        }

        try {
            store.buyBook("PAPER001", -1, "customer@example.com", "123 Main St");
            System.out.println("Should not allow negative quantity");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled negative quantity: " + e.getMessage());
        }

        // Test invalid email
        try {
            store.buyBook("PAPER001", 1, "", "123 Main St");
            System.out.println("Should not allow empty email");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled empty email: " + e.getMessage());
        }

        try {
            store.buyBook("PAPER001", 1, null, "123 Main St");
            System.out.println("Should not allow null email");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled null email: " + e.getMessage());
        }

        // Test invalid address
        try {
            store.buyBook("PAPER001", 1, "customer@example.com", "");
            System.out.println("Should not allow empty address");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled empty address: " + e.getMessage());
        }

        try {
            store.buyBook("PAPER001", 1, "customer@example.com", null);
            System.out.println("Should not allow null address");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled null address: " + e.getMessage());
        }
    }

    private static void testRemovingBooks() {
        System.out.println("--- Testing Removing Books ---");
        BookStore store = new BookStore();

        Book book1 = new PaperBook("REMOVE001", "Book to Remove", 19.99, 2023, 10);
        Book book2 = new EBook("KEEP001", "Book to Keep", 9.99, 2022, "https://example.com");

        store.addBook(book1);
        store.addBook(book2);

        // Test successful removal
        try {
            store.removeBook("REMOVE001");
            System.out.println("Successfully removed book");
        } catch (Exception e) {
            System.out.println("Unexpected error removing book: " + e.getMessage());
        }

        // Test removing non-existent book
        try {
            store.removeBook("NONEXISTENT");
            System.out.println("Should not be able to remove non-existent book");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled removing non-existent book: " + e.getMessage());
        }

        // Test removing already removed book
        try {
            store.removeBook("REMOVE001");
            System.out.println("Should not be able to remove already removed book");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled removing already removed book: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testRemovingOutdatedBooks() {
        System.out.println("--- Testing Removing Outdated Books ---");
        BookStore store = new BookStore();

        // Setup books with different years
        Book oldBook1 = new PaperBook("OLD001", "Old Book 1", 19.99, 2015, 10);
        Book oldBook2 = new EBook("OLD002", "Old Book 2", 9.99, 2016, "PDf");
        Book newBook = new PaperBook("NEW001", "New Book", 29.99, 2023, 5);

        store.addBook(oldBook1);
        store.addBook(oldBook2);
        store.addBook(newBook);

        // Test removing books older than 7 years
        try {
            List<Book> removedBooks = store.removeOutdated(7);
            System.out.println("Successfully removed " + removedBooks.size() + " outdated books");
        } catch (Exception e) {
            System.out.println("Unexpected error removing outdated books: " + e.getMessage());
        }

        // Test removing outdated books when none exist
        try {
            store.removeOutdated(20);
            System.out.println("Should not find any books older than 20 years");
        } catch (IllegalArgumentException e) {
            System.out.println("Correctly handled no outdated books found: " + e.getMessage());
        }

        System.out.println();
    }
}
