package main.UserInterface.Screens;

import javax.swing.*;

import main.Session.Session;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WeatherInfoDisplayScreen extends JFrame {
    Session sessionInstance;
    private JLabel headlineLabel;
    private JPanel weatherPanel;
    private JButton returnButton;

    public WeatherInfoDisplayScreen(Session Si,String[] weatherData) {
        this.sessionInstance = Si;
        setTitle("Weather Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Add return button at the top right
        returnButton = new JButton("Return to Home");
        returnButton.addActionListener(new ReturnButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(returnButton);
        add(buttonPanel, BorderLayout.NORTH);

        // Headline label
        headlineLabel = new JLabel("Weather Information", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(headlineLabel, BorderLayout.CENTER);

        // Weather data panel
        weatherPanel = new JPanel(new GridLayout(12, 2));
        weatherPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        add(weatherPanel, BorderLayout.CENTER);

        // Populate weather data
        String[] labels = {"City Name:", "Country Name:", "Sunrise Time:", "Sunset Time:", "Latitude:", "Longitude:",
                "Weather:", "Weather Description:", "Temperature:", "Feels Like:", "Max Temperature:", "Min Temperature:"};
        for (int i = 0; i < labels.length; i++) {
            JLabel label = new JLabel(labels[i]);
            JLabel value = new JLabel(weatherData[i]);
            weatherPanel.add(label);
            weatherPanel.add(value);
        }

        setVisible(true);
    }

    // ActionListener for return button
    private class ReturnButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Return to home action (you can implement this according to your application flow)
            // For demonstration, I'm simply disposing the current frame
            sessionInstance.getWelcomeScreen();
            dispose();
        }
    }
}

