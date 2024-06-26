package main.UserInterface.Screens;

import main.Session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class weatherForcastDisplayScreen extends JFrame {
    private final Session sessionInstance;
    private final JPanel cardPanel;
    private final CardLayout cardLayout;
    private final JButton returnButton;
    private final JLabel locationInfoLabel;
    private final JButton nextLocationButton;
    private int currentLocationIndex = 0;

    public weatherForcastDisplayScreen(Session sessionInstance, String[][] weatherData, int noOfLocations) {
        this.sessionInstance = sessionInstance;
        setTitle("5 days Forecast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the location info label
        locationInfoLabel = new JLabel("5 days Forecast for " + weatherData[0][0] + ", " + weatherData[0][1] +
                "|| Longitude: " + weatherData[0][2] + "' Latitude: " + weatherData[0][3] + "'");
        locationInfoLabel.setForeground(new Color(30, 30, 30)); // Set text color to off-black
        locationInfoLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center horizontally

        // Create the return button
        returnButton = new JButton("Return to Home");
        returnButton.setBackground(new Color(218, 165, 32)); // Set background color to gold
        returnButton.setForeground(new Color(30, 30, 30)); // Set text color to off-black
        returnButton.addActionListener(new ReturnButtonListener()); // Add action listener

        // Add location info label and return button to the top panel
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(218, 165, 32)); // Set background color to gold
        topPanel.add(locationInfoLabel, BorderLayout.CENTER);
        topPanel.add(returnButton, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // Create the card panel and set layout to CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        // Populate card panel with weather data panels
        for (int locIndex = 1; locIndex < noOfLocations; locIndex++) {
            JPanel weatherPanel = createWeatherPanel(weatherData[locIndex]);
            cardPanel.add(weatherPanel, "Location " + locIndex);
        }

        // Add next location button if noOfLocations is greater than 1
        if (noOfLocations > 1) {
            nextLocationButton = new JButton("Next Day");
            nextLocationButton.setBackground(new Color(218, 165, 32)); // Set background color to gold
            nextLocationButton.setForeground(new Color(30, 30, 30)); // Set text color to off-black
            nextLocationButton.addActionListener(new NextLocationButtonListener());
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.setBackground(new Color(218, 165, 32)); // Set background color to gold
            bottomPanel.add(nextLocationButton);
            add(bottomPanel, BorderLayout.SOUTH);
        } else {
            nextLocationButton = null;
        }

        setVisible(true);

        // Check weather condition for the first panel
        checkWeatherCondition(0);
    }

    // Create a weather panel for a location
    private JPanel createWeatherPanel(String[] locationData) {
        JPanel weatherPanel = new JPanel(new GridLayout(5, 2)); // Increase the row count for the timestamp
        weatherPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        weatherPanel.setBackground(Color.LIGHT_GRAY); // Set background color to royal blue

        String[] labels = {"Date", "Min Temperature:", "Max Temperature:", "Weather:", "Weather Description:"}; // Add the timestamp label
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            label.setForeground(Color.BLACK); // Set text color to black
            JLabel value = new JLabel(locationData[i]);
            value.setForeground(Color.BLACK); // Set text color to black
            weatherPanel.add(label);
            weatherPanel.add(value);
        }
        return weatherPanel;
    }

    // ActionListener for return button
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle the action of returning to the home screen here
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
            // Check weather condition for the newly shown panel
            checkWeatherCondition(currentLocationIndex);
        }
    }

    // Check weather condition for a given panel index
    private void checkWeatherCondition(int panelIndex) {
        Component panel = cardPanel.getComponent(panelIndex);
        String[] locationData = getLocationDataFromPanel((JPanel) panel);
        String weatherCondition = locationData[3]; // Assuming weather condition is at index 3
        if (weatherCondition.equalsIgnoreCase("rain") ||
                weatherCondition.equalsIgnoreCase("thunderstorm") ||
                weatherCondition.equalsIgnoreCase("snow") ||
                weatherCondition.equalsIgnoreCase("mist")) {
            JOptionPane.showMessageDialog(this,
                    "Weather Alert: " + weatherCondition + " expected.",
                    "Weather Alert",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    // Extract location data from a weather panel
    private String[] getLocationDataFromPanel(JPanel weatherPanel) {
        String[] locationData = new String[5]; // Assuming 5 weather attributes
        Component[] components = weatherPanel.getComponents();
        for (int i = 0, j = 0; i < components.length; i += 2, j++) {
            JLabel valueLabel = (JLabel) components[i + 1];
            locationData[j] = valueLabel.getText();
        }
        return locationData;
    }
}
