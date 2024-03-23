package main.UserInterface.Screens; 
import javax.swing.*;

import main.Session.Session;

import java.awt.*; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 

public class welcomeScreen extends JFrame implements ActionListener { 
    Session sessionInstance;
    private JButton[] buttons; 
    private String[] buttonLabels = {"Button 1", "Button 2", "Button 3", "Button 4", "Button 5", "Button 6"}; 
    
    public welcomeScreen(Session SI) { 
        sessionInstance = SI;
        setTitle("Weather App - Welcome"); 

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

        setSize(700, 500); // Adjust size as needed 

        setLocationRelativeTo(null); // Center the frame on the screen 
        // Set layout to BorderLayout 
        setLayout(new BorderLayout()); 
        // Create welcome label 
        JLabel welcomeLabel = new JLabel("Welcome to Weather App", SwingConstants.CENTER); 
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20)); 
        add(welcomeLabel, BorderLayout.NORTH); 
        // Create panel for buttons 

        JPanel buttonPanel = new JPanel(new GridLayout(2, 3, 10, 10)); // 2 rows, 3 columns, with padding 

        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding around the panel 
        // Create buttons and add action listener 
        buttons = new JButton[buttonLabels.length]; 
        for (int i = 0; i < buttonLabels.length; i++) { 

            buttons[i] = new JButton(buttonLabels[i]); 

            buttons[i].addActionListener(this); 

            buttonPanel.add(buttons[i]); 

        } 
        // Set background color for button panel 
        buttonPanel.setBackground(new Color(230, 230, 250)); // Lavender color, adjust as needed 
        // Add button panel to center of frame 
        add(buttonPanel, BorderLayout.CENTER); 
        // Set frame visible 
        setVisible(true); 
    } 
    @Override 
    public void actionPerformed(ActionEvent e) { 
        // Handle button clicks 
        for (int i = 0; i < buttons.length; i++) { 
            if (e.getSource() == buttons[i]) { 
                // Close the welcome screen
                dispose(); // Close the current JFrame
                sessionInstance.getWeatherLoc_LatInput(sessionInstance);
                return;
            } 
        } 
    } 
} 