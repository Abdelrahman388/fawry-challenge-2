public class MailingService {
    private String email;

    public MailingService(String email) {
        this.email = email;
    }

    public void send(EBook book,int quantity) {
        System.out.println("Sending " + quantity + " copies of " + book.getTitle() + " to email :" + email);
    }

}
