package main.UserInterface.Screens;

import main.Session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class airPollutionDisplayScreen extends JFrame {
    Session sessionInstance;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton returnButton;
    private JButton nextLocationButton;
    private int currentLocationIndex = 0;

    public airPollutionDisplayScreen(Session Si, String[][] pollutionData, int noOfLocations) {
        this.sessionInstance = Si;
        setTitle("Air Pollution Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the card panel and set layout to CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        // Add return button at the top right
        returnButton = new JButton("Return to Home");
        returnButton.addActionListener(new ReturnButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Populate card panel with air pollution data panels
        for (int locIndex = 0; locIndex < noOfLocations; locIndex++) {
            JPanel pollutionPanel = createPollutionPanel(pollutionData[locIndex]);
            cardPanel.add(pollutionPanel, "Location " + (locIndex + 1));
        }

        // Add next location button if noOfLocations is greater than 1
        if (noOfLocations > 1) {
            nextLocationButton = new JButton("Next Location");
            nextLocationButton.addActionListener(new NextLocationButtonListener());
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.add(nextLocationButton);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        setVisible(true);
    }

    // Create an air pollution panel for a location
private JPanel createPollutionPanel(String[] locationData) {
    JPanel pollutionPanel = new JPanel(new GridLayout(14, 2)); // Adjust as per your data structure
    pollutionPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    String[] labels = {"Latitude:", "Longitude:", "Local Time:", "AQI Value:", "CO Value:", "NO Value:", "NO2 Value:",
            "O3 Value:", "SO2 Value:", "PM2.5 Value:", "PM10 Value:", "NH3 Value:"}; // Field names from air pollution class
    for (int i = 0; i < labels.length; i++) {
        JLabel label = new JLabel(labels[i]);
        JLabel value = new JLabel(parseNumericValue(locationData[i])); // Parse the numeric value
        pollutionPanel.add(label);
        pollutionPanel.add(value);
    }
    return pollutionPanel;
}

// Parse numeric value from string and return as string
private String parseNumericValue(String value) {
    try {
        // Try parsing as Double first
        Double doubleValue = Double.parseDouble(value);
        return String.valueOf(doubleValue);
    } catch (NumberFormatException e) {
        // If parsing as Double fails, try parsing as Long
        try {
            Long longValue = Long.parseLong(value);
            return String.valueOf(longValue);
        } catch (NumberFormatException ex) {
            // If parsing as Long also fails, return the original string
            return value;
        }
    }
}


    // ActionListener for return button
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionInstance.getWelcomeScreen();
            dispose();
        }
    }

    // ActionListener for next location button
    private class NextLocationButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            currentLocationIndex = (currentLocationIndex + 1) % cardPanel.getComponentCount();
            cardLayout.show(cardPanel, "Location " + (currentLocationIndex + 1));
        }
    }
}
