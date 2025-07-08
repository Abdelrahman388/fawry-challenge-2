public class MailingService {
    private String email;

    public MailingService(String email) {
        this.email = email;
    }

    public void send() {
        System.out.println("Sending e-book to email :" + email);
    }

}
