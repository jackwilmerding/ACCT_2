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
import java.awt.Font;

public class MainMenu extends JPanel {

    private JPanel cardPanel;

    public MainMenu(JPanel p) {
        cardPanel = p;

        setSize(Display.WIDTH, Display.HEIGHT);
        setLayout(null);

        JLabel title = new JLabel("ACCT 2 Contact Tracing");
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
