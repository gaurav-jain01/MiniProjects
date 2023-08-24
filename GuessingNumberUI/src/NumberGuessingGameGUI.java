import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;


public class NumberGuessingGameGUI extends JFrame {
    private int randomNumber;
    private int attempts;

    private JLabel titleLabel;
    private JLabel messageLabel;
    private JTextField guessTextField;
    private JButton guessButton;

    public NumberGuessingGameGUI() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;

        titleLabel = new JLabel("Number Guessing Game");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);

        messageLabel = new JLabel("Enter your guess:");
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        messageLabel.setHorizontalAlignment(JLabel.CENTER);

        guessTextField = new JTextField(6); // Create text field for guess input
        guessTextField.setFont(new Font("Arial", Font.PLAIN,16));

        guessButton = new JButton("Guess");
        guessButton.addActionListener(new GuessButtonListener());

        JPanel mainPanel = new JPanel(); // Create main panel
        mainPanel.setLayout(new GridLayout(4, 1));
        mainPanel.add(titleLabel); // Add title label to main panel
        mainPanel.add(messageLabel); // Add message label to main panel
        mainPanel.add(guessTextField); // Add guess text field to main panel
        mainPanel.add(guessButton); // Add guess button to main panel

        getContentPane().add(mainPanel, BorderLayout.CENTER); // Add main panel to content pane

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame on exit
        setTitle("Number Guessing Game");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private class GuessButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessTextField.getText()); // Get user's guess
                attempts++; // Increment attempts counter

                if (guess == randomNumber) { // Check if guess is correct
                    JOptionPane.showMessageDialog(NumberGuessingGameGUI.this,
                            "Congratulations! You guessed the number " + randomNumber + " in " + attempts + " attempts.",
                            "Winner!", JOptionPane.INFORMATION_MESSAGE); // Show winner message
                    resetGame(); // Reset the game for a new round
                } else if (guess < randomNumber) {
                    messageLabel.setText("Try a higher number."); // Prompt for a higher guess
                } else {
                    messageLabel.setText("Try a lower number."); // Prompt for a lower guess
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(NumberGuessingGameGUI.this,
                        "Please enter a valid number.",
                        "Invalid Input", JOptionPane.ERROR_MESSAGE); // Show error message for invalid input
            }
        }
    }

    private void resetGame() {
        randomNumber = new Random().nextInt(100) + 1; // Generate a new random number
        attempts = 0; // Reset attempts counter
        messageLabel.setText("Enter your guess:");
        guessTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NumberGuessingGameGUI()); // Run GUI in event dispatch thread
    }
}