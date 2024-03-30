package main.UserInterface.Screens;


import main.Session.Session;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherInfoDisplayScreen extends JFrame {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JButton returnButton;
    private JButton nextLocationButton;
    private int currentLocationIndex = 0;
    private Session sessionInstance;

    public WeatherInfoDisplayScreen(Session sessionInstance, String[][] weatherData, int noOfLocations) {
        this.sessionInstance = sessionInstance;
        setTitle("Weather Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        // Create the card panel and set layout to CardLayout
        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        add(cardPanel, BorderLayout.CENTER);

        // Populate card panel with weather data panels
        for (int locIndex = 0; locIndex < noOfLocations; locIndex++) {
            JPanel weatherPanel = createWeatherPanel(weatherData[locIndex]);
            cardPanel.add(weatherPanel, "Location " + (locIndex + 1));
        }

        // Manually trigger weather condition check for the first panel
        

        // Add return button at the top right
        returnButton = new JButton("Return to Home");
        returnButton.addActionListener(new ReturnButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Add next location button if noOfLocations is greater than 1
        if (noOfLocations > 1) {
            nextLocationButton = new JButton("Next Location");
            nextLocationButton.addActionListener(new NextLocationButtonListener());
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.add(nextLocationButton);
            add(bottomPanel, BorderLayout.SOUTH);
        }

        setVisible(true);
        checkWeatherCondition(0);
    }

    // Create a weather panel for a location
    private JPanel createWeatherPanel(String[] locationData) {
        JPanel weatherPanel = new JPanel(new GridLayout(14, 2)); // Increase the row count for the timestamp
        weatherPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] labels = {"City Name:", "Country Name:", "Timestamp:", "Sunrise Time:", "Sunset Time:", "Latitude:", "Longitude:",
                "Weather:", "Weather Description:", "Temperature:", "Feels Like:", "Min Temperature:", "Max Temperature:"}; // Add the timestamp label
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JLabel value = new JLabel(locationData[i]);
            weatherPanel.add(label);
            weatherPanel.add(value);
        }

        return weatherPanel;
    }

    // Check weather condition for a given panel index
    private void checkWeatherCondition(int panelIndex) {
        Component panel = cardPanel.getComponent(panelIndex);
        String[] locationData = getLocationDataFromPanel((JPanel) panel);
        String weatherCondition = locationData[7];
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
        String[] locationData = new String[14];
        Component[] components = weatherPanel.getComponents();
        for (int i = 0, j = 0; i < components.length; i += 2, j++) {
            JLabel valueLabel = (JLabel) components[i + 1];
            locationData[j] = valueLabel.getText();
        }
        return locationData;
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
            // Check weather condition for the newly shown panel
            checkWeatherCondition(currentLocationIndex);
        }
    }
}