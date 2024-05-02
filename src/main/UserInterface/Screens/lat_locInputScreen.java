package main.UserInterface.Screens; 

import javax.swing.*;

import main.Session.Session;

import java.awt.*;
import java.awt.event.*;

public class lat_locInputScreen extends JFrame {
    private JLabel headlineLabel;
    private JPanel inputPanel;
    private JTextField[] latitudeFields;
    private JTextField[] longitudeFields;
    private double[][] enteredData; // Modified to double[][]
    Session sessionInstance;
    private int choice =0;

    public lat_locInputScreen(Session SI,int choice) {
        this.sessionInstance = SI;
        this.choice = choice;
        setTitle("Weather App - Location Input");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500); // Set size to width 700 and height 500
        setLocationRelativeTo(null); // Center the frame on the screen

        // Set layout to BorderLayout
        setLayout(new BorderLayout());

        // Create headline label
        headlineLabel = new JLabel("Enter the latitude, longitude for checking weather", SwingConstants.CENTER);
        headlineLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(headlineLabel, BorderLayout.NORTH);

        // Create panel for input fields
        inputPanel = new JPanel(new GridLayout(3, 2, 10, 10)); // 3 rows, 2 columns, with padding
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel

        // Create and add text fields for latitude and longitude
        latitudeFields = new JTextField[3];
        longitudeFields = new JTextField[3];
        for (int i = 0; i < 3; i++) {
            latitudeFields[i] = new JTextField();
            longitudeFields[i] = new JTextField();
            latitudeFields[i].addActionListener(new EnterListener());
            longitudeFields[i].addActionListener(new EnterListener());
            inputPanel.add(new JLabel("Latitude " + (i + 1) + ":"));
            inputPanel.add(latitudeFields[i]);
            inputPanel.add(new JLabel("Longitude " + (i + 1) + ":"));
            inputPanel.add(longitudeFields[i]);
        }

        // Add input panel to center of frame
        add(inputPanel, BorderLayout.CENTER);

        // Set frame visible
        setVisible(true);
    }

    // ActionListener to handle Enter key press
    // ActionListener to handle Enter key press
private class EnterListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Count the number of sets with data
        int setsWithData = 0;
        for (int i = 0; i < 3; i++) {
            if (!latitudeFields[i].getText().isEmpty() && !longitudeFields[i].getText().isEmpty()) {
                setsWithData++;
            }
        }
        
        // Initialize the enteredData array with the correct size
        enteredData = new double[setsWithData][2];

        // Fill the enteredData array with non-zero values
        int index = 0;
        for (int i = 0; i < 3; i++) {
            if (!latitudeFields[i].getText().isEmpty() && !longitudeFields[i].getText().isEmpty()) {
                // Convert integer strings to double values
                double latitude = Double.parseDouble(latitudeFields[i].getText());
                double longitude = Double.parseDouble(longitudeFields[i].getText());
                
                enteredData[index][0] = latitude;
                enteredData[index][1] = longitude;
                index++;
            }
        }

        // Pass the enteredData to the sessionInstance
        if (choice == 1)
        {sessionInstance.getWeatherLoc_LatProcess(enteredData);
        }
        else if (choice == 2)
        {
            sessionInstance.getAirPollutionLoc_LatProcess(enteredData);
        }
        // else if (choice == 3)
        // {
        //     sessionInstance.get5DayForecastDataProcess(enteredData);
        // }
        // Close the current JFrame
        dispose();
    }
}

}
