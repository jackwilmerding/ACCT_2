/*
Active COVID Contact Tracing (ACCT) Gen 2 / A program designed for small businesses to prevent the spread of COVID-19
    Copyright (C) 2020 John Wilmerding

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
public class MailBot {
    public void sender(String[] to, String date){
        //SOME CODE FROM: https://pepipost.com/tutorials/send-email-in-java-using-gmail-smtp/
        InternetAddress[] addresses = new InternetAddress[to.length];
        for(int i = 0; i < to.length; i++) {
            try {
                addresses[i] = new InternetAddress(to[i]);
            } catch (AddressException e) {
                e.printStackTrace();
            }
        }
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

                return new PasswordAuthentication("acct2mailbot@gmail.com", "$$IHATEORACLE$$");

            }

        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress("acct2mailbot@gmail.com"));

            // Set To: header field of the header.
            message.addRecipients(Message.RecipientType.BCC, addresses);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("acct2mailbot@gmail.com"));

            // Set Subject: header field
            message.setSubject("Contact Tracing Notification");

            // Now set the actual message
            message.setContent("<body><p>Dear Patron,<br><br>You are receiving this email notification because a business you have visited suspects there may have been a COVID-19 exposure event on " + date + ". While this is only a suspicion, we'd like to remind you to stay safe and stay home when viable. Because of your possible contact with the virus, we advise maintaining stricter-than-usual social distancing. If you start feeling any symptoms (such as fever, coughing, and/or shortness of breath), please immediately reach out to your local medical professionals/COVID-19 hotline for assistance and advice. Finally, if your case is confirmed, if you remember giving your information to businesses for contact tracing purposes in the previous two weeks, please contact them and inform them of your condition.<br><br>Working to keep your community safe and healthy,<br>The Team at ACCT<br><em>I am a bot, any replies to this message will not be read.</em></p></body>", "text/html");

            // Send message
            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
