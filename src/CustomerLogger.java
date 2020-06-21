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
        emailLabel.setBounds(Display.WIDTH/2 - 20, Display.HEIGHT/2 - 30, 80, 20);
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
                if((!firstName.getText().equals("")) && (!lastName.getText().equals("")) && (!email.getText().equals(""))) {
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
