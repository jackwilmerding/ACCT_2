import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class MainMenu extends JPanel {

    private JPanel cardPanel;

    public MainMenu(JPanel p) {
        cardPanel = p;

        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JLabel title = new JLabel("ACCT 2\nContact Tracing");
        title.setBounds(150, 0, 500, 150);
        title.setForeground(Color.RED);
        title.setFont(new Font("Helvetica", Font.BOLD, 28));
        add(title);

        JButton logCustomerButton = new JButton("Log Customer");
        logCustomerButton.setBounds(20, Display.HEIGHT/2 - 40, Display.WIDTH-40, 30);
        logCustomerButton.setHorizontalAlignment(SwingConstants.CENTER);
        logCustomerButton.setVerticalAlignment(SwingConstants.CENTER);
        logCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Customer Logger");
            }
        });
        JButton reportIncidentButton = new JButton("Report Incident");
        reportIncidentButton.setBounds(20, Display.HEIGHT/2 + 40, Display.WIDTH - 40, 30);
        reportIncidentButton.setHorizontalAlignment(SwingConstants.CENTER);
        reportIncidentButton.setVerticalAlignment(SwingConstants.CENTER);
        reportIncidentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) cardPanel.getLayout();
                cl.show(cardPanel, "Incident Reporter");
            }
        });

        add(logCustomerButton);
        add(reportIncidentButton);
    }
}
