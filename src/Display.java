import javax.swing.*;
import java.awt.*;
public class Display extends JFrame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 400;
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
