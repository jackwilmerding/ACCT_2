import javax.swing.*;
import java.awt.*;
public class Display extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;

    JPanel cards;

    public Display() {
        super("ACCT 2");

        cards = new JPanel(new CardLayout());
        cards.add(new MainMenu(cards), "Main Menu");
        cards.add(new CustomerLogger(cards), "Customer Logger");
        cards.add(new IncidentReport(cards), "Incident Reporter");
        add(cards);

        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }
}
