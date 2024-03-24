package main.UserInterface.Screens; 

import javax.swing.*;

import main.Session.Session;

import java.awt.*;
import java.awt.event.*;

public class LocationInputScreen extends JFrame {
    private JLabel headlineLabel;
    private JPanel inputPanel;
    private JTextField[] locationFields;
    private String[] enteredLocations;
    private Session sessionInstance;

    public LocationInputScreen(Session sessionInstance) {
        this.sessionInstance = sessionInstance;
        setTitle("Weather App - Location Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Set size to width 700 and height 500
        setLocationRelativeTo(null); // Center the frame on the screen

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Create headline label
        headlineLabel = new JLabel("Enter 3 location names for weather checking", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headlineLabel, BorderLayout.NORTH);

        // Create panel for input fields
        inputPanel = new JPanel(new GridLayout(3, 1, 10, 10)); // 3 rows, 1 column, with padding
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Create and add text fields for locations
        locationFields = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            locationFields[i] = new JTextField();
            locationFields[i].addActionListener(new EnterListener());
            inputPanel.add(new JLabel("Location " + (i + 1) + ":"));
            inputPanel.add(locationFields[i]);
        }

        // Add input panel to center of frame
        add(inputPanel, BorderLayout.CENTER);

        // Set frame visible
        setVisible(true);
    }

    // ActionListener to handle Enter key press
    private class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Return the data
            enteredLocations = new String[3];
            for (int i = 0; i < 3; i++) {
                enteredLocations[i] = locationFields[i].getText().trim();
            }
            dispose();
            sessionInstance.getWeatherLocationProcess(enteredLocations); // temporary test call
             // Close the current JFrame
             return;
        }
    }
}
