import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncidentReport extends JPanel{
    private JPanel cardPanel;

    public IncidentReport(JPanel p) {
        cardPanel = p;
        Backend b = new Backend();


        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JLabel emailLabel = new JLabel("Customer Email:");
        emailLabel.setBounds(200, Display.HEIGHT/2 - 70, 500, 20);
        add(emailLabel);
        JTextField email = new JTextField("");
        email.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 45, 200, 20);
        email.setHorizontalAlignment(SwingConstants.CENTER);
        add(email);

        JLabel dateLabel = new JLabel("Date Visited (YYYY-MM-DD):");
        dateLabel.setBounds(205, Display.HEIGHT/2 + 10, 500, 20);
        add(dateLabel);

        JComboBox dates = new JComboBox();
        dates.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 + 40, 200, 20);

        JButton enter1 = new JButton("Enter");
        enter1.setBounds(Display.WIDTH/2 - 60, Display.HEIGHT/2 - 15, 120, 20);
        add(enter1);
        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //populates the combobox
                String query = email.getText().toLowerCase();
                enter1.setText("Searching...");
                for (String date : b.search(query)) {
                    dates.addItem(new ComboItem(date));
                }
                add(dates);
                enter1.setText("Enter");
                return;
            }
        });

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

        JLabel loading = new JLabel("Processing...");
        loading.setBounds(Display.WIDTH/2 - 60, Display.HEIGHT/2 + 100, 120, 20);

        JLabel success = new JLabel("Success!");
        success.setBounds(Display.WIDTH/2 - 60, Display.HEIGHT/2 + 100, 120, 20);
        success.setForeground(Color.GREEN);

        JButton enter2 = new JButton("Enter");
        enter2.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 + 70, 80, 20);
        add(enter2);
        enter2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                add(loading);
                remove(loading);
                //Takes selection from combobox, sends report based on data
                //TODO
                add(success);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                remove(success);
                remove(dates);
                email.setText("");
                return;
            }
        });
    }
}
