package main.UserInterface.Screens;

import main.Session.Session;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forecastLocationName extends JFrame {
    private JTextField locationField;
    private JButton submitButton;
    private Session sessionInstance;

    public forecastLocationName(Session Si) {
        this.sessionInstance = Si;
        setTitle("Weather Forecast");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        initComponents();

        setVisible(true);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Banner at the top
        JLabel bannerLabel = new JLabel("Enter location");
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(bannerLabel, BorderLayout.NORTH);

        // Panel for input fields and submit button
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(180, 20, 20, 20));

        // Location input field
        JLabel locationLabel = new JLabel("Location:");
        locationField = new JTextField(20);
        JPanel locationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        locationPanel.add(locationLabel);
        locationPanel.add(locationField);
        inputPanel.add(locationPanel);

        // Submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(submitButton);
        inputPanel.add(buttonPanel);

        add(inputPanel, BorderLayout.CENTER);
    }

    private class SubmitButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the value of location from the input field
            String location = locationField.getText().trim();
            // Process the location data
            sessionInstance.get5DayForecastDataProcess(location);
            dispose();
        }
    }

    
}
