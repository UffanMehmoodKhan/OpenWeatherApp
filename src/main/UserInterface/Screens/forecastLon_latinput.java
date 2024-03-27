package main.UserInterface.Screens;
import javax.swing.*;
import main.Session.Session;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class forecastLon_latinput extends JFrame {
    private JTextField longitudeField;
    private JTextField latitudeField;
    private JButton submitButton;
    private Session sessionInstance;

    public forecastLon_latinput(Session Si) {
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
        JLabel bannerLabel = new JLabel("Enter coordinates of the location");
        bannerLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bannerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(bannerLabel, BorderLayout.NORTH);

        // Panel for input fields and submit button
        JPanel inputPanel = new JPanel(new GridLayout(1, 3));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(180, 20, 20, 20));

        // Longitude input field
        JLabel longitudeLabel = new JLabel("Longitude:");
        longitudeField = new JTextField(10);
        JPanel longitudePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        longitudePanel.add(longitudeLabel);
        longitudePanel.add(longitudeField);
        inputPanel.add(longitudePanel);

        // Latitude input field
        JLabel latitudeLabel = new JLabel("Latitude:");
        latitudeField = new JTextField(10);
        JPanel latitudePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        latitudePanel.add(latitudeLabel);
        latitudePanel.add(latitudeField);
        inputPanel.add(latitudePanel);

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
            // Get the values of longitude and latitude from input fields
            String longitude = longitudeField.getText().trim();
            String latitude = latitudeField.getText().trim();
            sessionInstance.get5DayForecastDataProcess(new double[][]{{Double.parseDouble(longitude), Double.parseDouble(latitude)}});
            dispose();
        }
    }
}
