import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class CustomerLogger extends JPanel {
    private JPanel cardPanel;

    public CustomerLogger(JPanel p) {
        cardPanel = p;
        Backend b = new Backend();


        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 20);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Main Menu");
            }
        });
        add(backButton);
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 - 150, 80, 20);
        add(firstNameLabel);
        JTextField firstName = new JTextField("");
        firstName.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 125, 200, 20);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        add(firstName);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 - 90, 80, 20);
        add(lastNameLabel);
        JTextField lastName = new JTextField("");
        lastName.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 65, 200, 20);
        lastName.setHorizontalAlignment(SwingConstants.CENTER);
        add(lastName);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(Display.HEIGHT/2, Display.HEIGHT/2 - 30, 80, 20);
        add(emailLabel);
        JTextField email = new JTextField("");
        email.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 5, 200, 20);
        email.setHorizontalAlignment(SwingConstants.CENTER);
        add(email);

        JButton enter = new JButton("Enter");
        enter.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 + 60, 80, 20);
        add(enter);
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((firstName.getText() != "") && (lastName.getText() != "") && (email.getText() != "")) {
                    try {
                        b.writer(firstName.getText(), lastName.getText(), email.getText());
                        firstName.setText("");
                        lastName.setText("");
                        email.setText("");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    return;
                }
                else {
                    return;
                }
            }
        });
    }

}
