import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class VotingMachineUI extends JFrame {
    private JTextField nameField, phoneNumberField;
    private JRadioButton partyARadio, partyBRadio, partyCRadio;
    private JButton submitButton, resultButton;
    private JLabel resultLabel;
    private int partyAVotes, partyBVotes, partyCVotes;
    private int contactCount = 0;

    public VotingMachineUI() {
        setTitle("Voting Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(0, 1));

        nameField = new JTextField(20);
        phoneNumberField = new JTextField(20);

        phoneNumberField.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume();
                }
            }

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}
        });

        partyARadio = new JRadioButton("Party A");
        partyBRadio = new JRadioButton("Party B");
        partyCRadio = new JRadioButton("Party C");

        ButtonGroup partyGroup = new ButtonGroup();
        partyGroup.add(partyARadio);
        partyGroup.add(partyBRadio);
        partyGroup.add(partyCRadio);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                submitVote();
            }
        });

        resultButton = new JButton("Check Results");
        resultButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showResults();
            }
        });

        resultLabel = new JLabel();

        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(nameField);
        mainPanel.add(new JLabel("Phone Number:"));
        mainPanel.add(phoneNumberField);
        mainPanel.add(new JLabel("Select Party:"));
        mainPanel.add(partyARadio);
        mainPanel.add(partyBRadio);
        mainPanel.add(partyCRadio);
        mainPanel.add(submitButton);
        mainPanel.add(resultButton);
        mainPanel.add(resultLabel);

        add(mainPanel);
    }

    private void submitVote() {
        if (contactCount >= 10) {
            JOptionPane.showMessageDialog(this, "Contact limit reached (10 contacts).");
            return;
        }

        String name = nameField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim();

        if (name.isEmpty() || phoneNumber.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both name and phone number.");
            return;
        }

        if (!phoneNumber.matches("\\d+")) {
            JOptionPane.showMessageDialog(this, "Phone number must contain only digits.");
            return;
        }

        if (partyARadio.isSelected()) {
            partyAVotes++;
        } else if (partyBRadio.isSelected()) {
            partyBVotes++;
        } else if (partyCRadio.isSelected()) {
            partyCVotes++;
        }

        contactCount++;
        JOptionPane.showMessageDialog(this, "Vote submitted successfully!");
    }

    private void showResults() {
        JFrame resultFrame = new JFrame("Voting Results");
        resultFrame.setSize(300, 200);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        resultFrame.setLocationRelativeTo(this);

        JPanel resultPanel = new JPanel(new GridLayout(0, 1));

        int maxVotes = Math.max(partyAVotes, Math.max(partyBVotes, partyCVotes));
        String winner;

        if (maxVotes == partyAVotes) {
            winner = "Party A";
        } else if (maxVotes == partyBVotes) {
            winner = "Party B";
        } else {
            winner = "Party C";
        }

        String resultText = "Party A Votes: " + partyAVotes + "\nParty B Votes: " + partyBVotes
                + "\nParty C Votes: " + partyCVotes + "\nWinner: " + winner;

        JLabel resultLabel = new JLabel(resultText);
        resultPanel.add(resultLabel);

        resultFrame.add(resultPanel);
        resultFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new VotingMachineUI().setVisible(true);
            }
        });
    }
}
