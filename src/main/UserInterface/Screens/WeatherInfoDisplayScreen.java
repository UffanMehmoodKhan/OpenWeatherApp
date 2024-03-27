package main.UserInterface.Screens;

import main.Session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherInfoDisplayScreen extends JFrame {
    Session sessionInstance;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton returnButton;
    private JButton nextLocationButton;
    private int currentLocationIndex = 0;

    public WeatherInfoDisplayScreen(Session Si, String[][] weatherData, int noOfLocations) {
        this.sessionInstance = Si;
        setTitle("Weather Information");
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

        // Populate card panel with weather data panels
        for (int locIndex = 0; locIndex < noOfLocations; locIndex++) {
            JPanel weatherPanel = createWeatherPanel(weatherData[locIndex]);
            cardPanel.add(weatherPanel, "Location " + (locIndex + 1));
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

    // Create a weather panel for a location
    private JPanel createWeatherPanel(String[] locationData) {
    JPanel weatherPanel = new JPanel(new GridLayout(14, 2)); // Increase the row count for the timestamp
    weatherPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

    String[] labels = {"City Name:", "Country Name:", "Timestamp:", "Sunrise Time:", "Sunset Time:", "Latitude:", "Longitude:",
            "Weather:", "Weather Description:", "Temperature:", "Feels Like:", "Max Temperature:", "Min Temperature:"}; // Add the timestamp label
    for (int i = 0; i < labels.length; i++) {
        JLabel label = new JLabel(labels[i]);
        JLabel value = new JLabel(locationData[i]);
        weatherPanel.add(label);
        weatherPanel.add(value);
    }
    return weatherPanel;
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
