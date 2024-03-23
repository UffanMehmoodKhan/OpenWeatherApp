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
    private int[][] enteredData;
    Session sessionInstance;

    public lat_locInputScreen(Session SI) {
        this.sessionInstance = SI;
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
    private class EnterListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Return the data
            enteredData = new int[3][2];
            for (int i = 0; i < 3; i++) {
                if (!latitudeFields[i].getText().isEmpty() && !longitudeFields[i].getText().isEmpty()) {
                    enteredData[i][0] = Integer.parseInt(latitudeFields[i].getText());
                    enteredData[i][1] = Integer.parseInt(longitudeFields[i].getText());
                }
            }
            //  sessionInstance.getWeatherLoc_LatProcess(enteredData); //// orignal call will be this 
            //sessionInstance.test(sessionInstance, enteredData); // temporary test call
            dispose(); // Close the current JFrame
        }
    }

   
}
