import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailBot {
    public void sender(String to, String firstName, String lastName, String date){
        //CODE FROM: https://pepipost.com/tutorials/send-email-in-java-using-gmail-smtp/

        // Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("acct2mailbot@gmail.com", "$IHateOracle$");

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("acct2mailbot@gmail.com"));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("Test");

            // Now set the actual message
            message.setContent("<body><p>Dear Patron,<br><br>You are receiving this email notification because a business you have visited suspects there may have been a COVID-19 exposure event on the day you were there. While this is only a suspicion, we'd like to remind you to stay safe and stay home if possible. Because of your possible contact with the virus, we advise maintaining stricter-than-usual social distancing. If you start feeling any symptoms (such as fever, coughing, and/or shortness of breath), please immediately reach out to your local medical professionals/COVID-19 hotline for assistance and advice. Finally, if your case is confirmed, if you remember giving your information to businesses for contact tracing purposes in the previous two weeks, please contact them and inform them of your condition.<br><br>Working to keep your community safe and healthy,<br>The Team at ACCT<br><em>I am a bot, any replies to this message will not be read.</em></p></body>", "text/html");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
