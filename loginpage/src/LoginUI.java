import javax.swing.*;

//  import loginpage.src.DatabaseHelper;

import java.awt.*;
import java.awt.event.*;

public class LoginUI 
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Login Page");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);  
        frame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(75, 0, 130));

        JPanel leftPanel = new JPanel();
leftPanel.setPreferredSize(new Dimension(350, 500)); 
leftPanel.setBackground(new Color(123, 104, 238));

// Load the image
ImageIcon imageIcon = new ImageIcon("/Users/mohitsoni/JAVA PROJECT/userloginpage/loginpage/src/62372.png"); // Ensure this matches your image filename
Image img = imageIcon.getImage().getScaledInstance(350, 500, Image.SCALE_SMOOTH); // Resize to fit
JLabel illustrationLabel = new JLabel(new ImageIcon(img));

leftPanel.add(illustrationLabel);


        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(450, 500)); // Adjusted for new window size
        rightPanel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel welcomeLabel = new JLabel("Welcome to login page");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        welcomeLabel.setForeground(new Color(75, 0, 130));
        gbc.gridx = 0; gbc.gridy = 0;
        rightPanel.add(welcomeLabel, gbc);

        JTextField userField = new JTextField("\uD83D\uDC64 Username", 18);
        userField.setForeground(Color.GRAY);
        userField.setPreferredSize(new Dimension(250, 40)); // Increased input field size
        gbc.gridy = 1;
        rightPanel.add(userField, gbc);

        JPasswordField passField = new JPasswordField(18);
        setPasswordPlaceholderBehavior(passField, "\uD83D\uDD11 Password");
        passField.setPreferredSize(new Dimension(250, 40)); // Increased input field size
        gbc.gridy = 2;
        rightPanel.add(passField, gbc);

        JButton loginButton = new JButton("\uD83D\uDE80 Login");
        loginButton.setBackground(new Color(50, 50, 255));
        loginButton.setForeground(Color.BLACK);
        loginButton.setPreferredSize(new Dimension(250, 40)); // Increased button size
        gbc.gridy = 3;
        rightPanel.add(loginButton, gbc);

        JButton createAccountButton = new JButton("\uD83D\uDC64 Create Account");
        createAccountButton.setBackground(new Color(0, 200, 0));
        createAccountButton.setForeground(Color.BLACK);
        createAccountButton.setPreferredSize(new Dimension(250, 40)); // Increased button size
        gbc.gridy = 4;
        rightPanel.add(createAccountButton, gbc);

        mainPanel.add(leftPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.setVisible(true);

        setPlaceholderBehavior(userField, "\uD83D\uDC64 Username");
        loginButton.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());
        
            if (DatabaseHelper.validateUser(username, password)) {
                JOptionPane.showMessageDialog(frame, "Login Successful!");
                frame.dispose(); // Close login window
                SwingUtilities.invokeLater(() -> ExpressionConverter.main(new String[]{}));
   
            } else {
                JOptionPane.showMessageDialog(frame, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        createAccountButton.addActionListener(e -> openCreateAccountWindow());
    }

    private static void setPlaceholderBehavior(JTextField field, String placeholder) {
        field.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (field.getText().equals(placeholder)) {
                    field.setText("");
                    field.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (field.getText().trim().isEmpty()) {
                    field.setText(placeholder);
                    field.setForeground(Color.GRAY);
                }
            }
        });
    }

    private static void setPasswordPlaceholderBehavior(JPasswordField passField, String placeholder) {
        passField.setEchoChar((char) 0); 
        passField.setText(placeholder);
        passField.setForeground(Color.GRAY);

        passField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passField.getPassword()).equals(placeholder)) {
                    passField.setText("");
                    passField.setForeground(Color.BLACK);
                    passField.setEchoChar('●'); 
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(passField.getPassword()).trim().isEmpty()) {
                    passField.setText(placeholder);
                    passField.setForeground(Color.GRAY);
                    passField.setEchoChar((char) 0); 
                }
            }
        });
    }

    private static void openCreateAccountWindow() {
        JFrame createAccountFrame = new JFrame("Create Account");
        createAccountFrame.setSize(450, 350);
        createAccountFrame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField newUserField = new JTextField("\uD83D\uDC64 Username", 18);
        newUserField.setForeground(Color.GRAY);
        newUserField.setPreferredSize(new Dimension(250, 40));
        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(newUserField, gbc);

        JPasswordField newPasswordField = new JPasswordField(18);
        setPasswordPlaceholderBehavior(newPasswordField, "\uD83D\uDD11 Password");
        newPasswordField.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 1;
        panel.add(newPasswordField, gbc);

        JPasswordField confirmPasswordField = new JPasswordField(18);
        setPasswordPlaceholderBehavior(confirmPasswordField, "\uD83D\uDD12 Confirm Password");
        confirmPasswordField.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 2;
        panel.add(confirmPasswordField, gbc);

        JButton submitButton = new JButton("\uD83D\uDCBE Submit");
        submitButton.setBackground(new Color(0, 150, 255));
        submitButton.setForeground(Color.BLACK);
        submitButton.setPreferredSize(new Dimension(250, 40));
        gbc.gridy = 3;
        panel.add(submitButton, gbc);

        createAccountFrame.add(panel);
        createAccountFrame.setVisible(true);

        setPlaceholderBehavior(newUserField, "\uD83D\uDC64 Username");

        submitButton.addActionListener(e -> {
            String username = newUserField.getText();
            String password = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
        
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(createAccountFrame, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        
            if (DatabaseHelper.registerUser(username, password)) {
                JOptionPane.showMessageDialog(createAccountFrame, "Account Created Successfully!");
                createAccountFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(createAccountFrame, "Failed to create account!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
