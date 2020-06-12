import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IncidentReport extends JPanel{
    private JPanel cardPanel;

    public IncidentReport(JPanel p) {
        cardPanel = p;



        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JLabel phoneLabel = new JLabel("Customer Phone Number (No dashes or spaces, include the 1):");
        phoneLabel.setBounds(110, Display.HEIGHT/2 - 70, 500, 20);
        add(phoneLabel);
        JTextField phone = new JTextField("");
        phone.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 - 45, 200, 20);
        phone.setHorizontalAlignment(SwingConstants.CENTER);
        add(phone);

        JLabel dateLabel = new JLabel("Date Visted (YYYY-MM-DD):");
        dateLabel.setBounds(205, Display.HEIGHT/2 + 10, 500, 20);
        add(dateLabel);

        JComboBox dates = new JComboBox();
        dates.setBounds(Display.WIDTH/2 - 105, Display.HEIGHT/2 + 40, 200, 20);
        add(dates);

        JButton enter1 = new JButton("Enter");
        enter1.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 - 15, 80, 20);
        add(enter1);
        enter1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //populates the combobox
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

        JButton enter2 = new JButton("Enter");
        enter2.setBounds(Display.WIDTH/2 - 40, Display.HEIGHT/2 + 70, 80, 20);
        add(enter2);
        enter2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Takes selection from combobox, sends report based on data
            }
        });
    }
}
