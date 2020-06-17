import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class IncidentReport extends JPanel{
    private JPanel cardPanel;

    public IncidentReport(JPanel p) {
        cardPanel = p;
        Backend b = new Backend();
        final String[] chosenDate = new String[1];


        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JLabel attention = new JLabel("<html><div style = 'text-align: center;'>ATTENTION:<br>Please only submit reports for<br>customers who have tested positive.<br><br>If nothing shows up in the \"Select Date\" box,<br>there were no matches to your search.<br>Press the lower enter button to reset the page.</div></html>");
        attention.setBounds(Display.WIDTH/2 - 140, Display.HEIGHT/2 - 200, 500, 160);
        attention.setForeground(Color.RED);
        add(attention);

        JLabel emailLabel = new JLabel("Customer Email:");
        emailLabel.setBounds(Display.WIDTH/2 - 50, Display.HEIGHT/2 - 50, 500, 20);
        add(emailLabel);
        JTextField email = new JTextField("");
        email.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 25, 200, 20);
        email.setHorizontalAlignment(SwingConstants.CENTER);
        add(email);

        JLabel success = new JLabel("<html><div style = 'text-align: center;'>Success!</div></html>");
        success.setForeground(new Color(0, 153, 0));
        success.setFont(new Font("Helvetica", Font.BOLD, 26));
        success.setBounds(Display.WIDTH/2 - 55, Display.HEIGHT/2 + 60, 200, 40);
        add(success);
        success.setVisible(false);

        JLabel dateLabel = new JLabel("Date Visited (YYYY-MM-DD):");
        dateLabel.setBounds(205, Display.HEIGHT/2 + 30, 500, 20);
        add(dateLabel);
        dateLabel.setVisible(false);

        JComboBox dates = new JComboBox();
        dates.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 + 60, 200, 20);
        dates.addItem("Select Date");
        add(dates);
        dates.setVisible(false);
        dates.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    if(!dates.getSelectedItem().equals("Select Date")) {
                        chosenDate[0] = String.valueOf(dates.getSelectedItem());
                    }
                } catch (NullPointerException exception) {
                    System.out.print("");
                }
                return;
            }
        });

        JButton enter1 = new JButton("Enter");
        enter1.setBounds(Display.WIDTH/2 - 60, Display.HEIGHT/2 + 5, 120, 20);
        add(enter1);

        JButton enter2 = new JButton("Enter");
        enter2.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 + 90, 80, 20);
        add(enter2);
        enter2.setVisible(false);

        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //populates the combobox
                String query = email.getText().toLowerCase();
                enter1.setText("Searching...");
                for (String date : b.search(query)) {
                    dates.addItem(date);
                }
                dateLabel.setVisible(true);
                dates.setVisible(true);
                enter2.setVisible(true);
                enter1.setText("Enter");
                success.setVisible(false);
                return;
            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 80, 20);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Main Menu");
            }
        });

        enter2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((chosenDate[0] != null) && (!b.searchLog(chosenDate[0]))) {
                    //Takes selection from combobox, sends report based on data
                    try {
                        Scanner counter = new Scanner(new File(chosenDate[0]));
                        int ctr = 0;
                        while(counter.hasNextLine()) {
                            ctr++;
                            counter.nextLine();
                        }
                        String[] data;
                        String date = chosenDate[0].substring(0, chosenDate[0].length() - 4);
                        b.writeLog(chosenDate[0]);
                        MailBot bot = new MailBot();
                        Scanner emailer = new Scanner(new File(chosenDate[0]));
                        String[] addresses = new String[ctr];
                        int i = 0;
                        while(emailer.hasNextLine()) {
                            data = emailer.nextLine().split(",");
                            addresses[i] = data[0];
                            i++;
                        }
                        bot.sender(addresses, date);
                    } catch (IOException fileNotFoundException) {
                        fileNotFoundException.printStackTrace();
                    }
                    email.setText("");
                    dates.removeAllItems();
                    dates.addItem("Select Date");
                    dates.setVisible(false);
                    dateLabel.setVisible(false);
                    enter2.setVisible(false);
                    success.setVisible(true);
                    return;
                }
                else {
                    email.setText("");
                    dates.removeAllItems();
                    dates.addItem("Select Date");
                    dates.setVisible(false);
                    dateLabel.setVisible(false);
                    enter2.setVisible(false);
                    success.setVisible(true);
                    return;
                }
            }
        });
    }
}
