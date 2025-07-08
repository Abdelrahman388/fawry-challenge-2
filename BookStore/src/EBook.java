public class EBook extends Book {
    private String fileType;

    public EBook(String isbn, String title, double price, int publishYear, String fileType) {
        super(isbn, title, publishYear, price);
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    @Override
    public boolean isAvailableForSale() {
        return true;
    }

    @Override
    public void buy(String isbn, int quantity, String email, String address) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }

        sendToEmail(email, quantity);
        System.out.println("Purchased " + quantity + " copies of " + getTitle() + " to be sent to "
                + email );
    }
        
    private void sendToEmail(String email, int quantity) {
        MailingService mailingService = new MailingService(email);
        mailingService.send(this, quantity);
    }

}
