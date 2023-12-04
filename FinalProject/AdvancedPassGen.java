package FinalProject;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdvancedPassGen extends JFrame {
    private PassGenerator passwordGenerator;
    private JTextField passwordTextField;
    private JLabel strengthLabel;

    public AdvancedPassGen() {
        // Set up the JFrame
        setTitle("Password Generator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(null);
        setLocation(430, 150);

        // Create GUI components
        JLabel lengthLabel = new JLabel("Password Length:");
        JTextField lengthTextField = new JTextField();

        JLabel uppercaseLabel = new JLabel("Include Uppercase Letters:");
        JCheckBox uppercaseCheckBox = new JCheckBox();

        JLabel lowercaseLabel = new JLabel("Include Lowercase Letters:");
        JCheckBox lowercaseCheckBox = new JCheckBox();

        JLabel specialCharLabel = new JLabel("Include Special characters:");
        JCheckBox specialCharCheckBox = new JCheckBox();

        JLabel numbersLabel = new JLabel("Include Numbers:");
        JCheckBox numbersCheckBox = new JCheckBox();

        JButton generateButton = new JButton("Generate");
        passwordTextField = new JTextField();

        JLabel strengthCheckerLabel = new JLabel("Password Strength Checker:");
        JLabel yourpassLabel = new JLabel("Your password:");

        JLabel inputLabel = new JLabel("Input your password here:");
        JTextField strengthTextField = new JTextField();

        JButton checkStrengthButton = new JButton("Check Strength");

        strengthLabel = new JLabel();

        JButton infoButton = new JButton("Useful Information");
        JLabel infoLabel = new JLabel();    

        // Set component positions and sizes
        lengthLabel.setBounds(10, 10, 120, 25);
        lengthTextField.setBounds(140, 10, 120, 25);
        uppercaseLabel.setBounds(10, 40, 160, 25);
        uppercaseCheckBox.setBounds(180, 40, 20, 25);
        lowercaseLabel.setBounds(10, 70, 160, 25);
        lowercaseCheckBox.setBounds(180, 70, 20, 25);
        specialCharLabel.setBounds(10, 100, 160, 25);
        specialCharCheckBox.setBounds(180, 100, 20, 25);
        numbersLabel.setBounds(10, 130, 160, 25);
        numbersCheckBox.setBounds(180, 130, 20, 25);
        generateButton.setBounds(10, 180, 100, 25);
        passwordTextField.setBounds(220, 180, 150, 25);
        strengthCheckerLabel.setBounds(10, 230, 180, 25);
        inputLabel.setBounds(10, 260, 180, 25);
        strengthTextField.setBounds(200, 260, 120, 25);
        checkStrengthButton.setBounds(10, 300, 150, 25);
        strengthLabel.setBounds(180, 300, 400, 25);
        infoButton.setBounds(220, 70, 150, 25);
        infoLabel.setBounds(220, 100, 400, 25);
        yourpassLabel.setBounds(120, 180, 180, 25);
  

        // Add components to the JFrame
        add(lengthLabel);
        add(lengthTextField);
        add(uppercaseLabel);
        add(uppercaseCheckBox);
        add(lowercaseLabel);
        add(lowercaseCheckBox);
        add(specialCharLabel);
        add(specialCharCheckBox);
        add(numbersLabel);
        add(numbersCheckBox);
        add(generateButton);
        add(passwordTextField);
        add(strengthCheckerLabel);
        add(inputLabel);
        add(strengthTextField);
        add(checkStrengthButton);
        add(strengthLabel);
        add(infoButton);
        add(infoLabel);
        add(yourpassLabel);

        // Set up event handling for the generate button
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Retrieve user input from GUI components
                    int length = Integer.parseInt(lengthTextField.getText());

                    // Validate the length input
                    if (length <= 0 || length > 32) {
                        throw new IllegalArgumentException("Invalid password length. Please enter a value between 1 and 32.");
                    }

                    boolean includeUppercase = uppercaseCheckBox.isSelected();
                    boolean includeLowercase = lowercaseCheckBox.isSelected();
                    boolean includeSpecialCharacters = specialCharCheckBox.isSelected();
                    boolean includeNumbers = numbersCheckBox.isSelected();

                    // Set the password generator attributes
                    passwordGenerator.setLength(length);
                    passwordGenerator.setIncludeUppercase(includeUppercase);
                    passwordGenerator.setIncludeLowercase(includeLowercase);
                    passwordGenerator.setIncludeSpecialCharacters(includeSpecialCharacters);
                    passwordGenerator.setIncludeNumbers(includeNumbers);

                    // Generate the password
                    String generatedPassword = passwordGenerator.generatePassword();

                    // Display the generated password
                    passwordTextField.setText(generatedPassword);
                } catch (NumberFormatException ex) {
                    // Handle invalid number format input
                    passwordTextField.setText("");
                    passwordTextField.requestFocus();
                    JOptionPane.showMessageDialog(null, "Invalid password length. Please enter a valid number.");
                } catch (IllegalArgumentException ex) {
                    // Handle length limit validation error
                    passwordTextField.setText("");
                    passwordTextField.requestFocus();
JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // Set up event handling for the checkStrengthButton
    checkStrengthButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String inputPassword = strengthTextField.getText().trim();
        
        if (inputPassword.isEmpty()) {
            strengthLabel.setText("Password Strength: N/A");
        } else {
            String strength = passwordGenerator.checkPasswordStrength(inputPassword);
            strengthLabel.setText("Password Strength: " + strength);
        }
    }
    });
    infoButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String information = "Useful Information:\n\n" +
            "Creating a Secure Password:\n\n" +
            "1. Use a combination of uppercase and lowercase letters, numbers, and special characters.\n" +
            "2. Make the password at least eight characters long.\n" +
            "3. Avoid using easily guessable information like your name, birthdate, or common words.\n" +
            "4. Don't reuse passwords across multiple accounts.\n" +
            "5. Regularly update your passwords and avoid using the same password for an extended period.\n";

    System.out.println(information);
            infoLabel.setText("Check in the terminal.");
        }
    });
        // Instantiate the password generator
        passwordGenerator = new PassGenerator();

        // Show the JFrame
        setVisible(true);
    }

    public static void main(String[] args){
        new AdvancedPassGen();
    }
}