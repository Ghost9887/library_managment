package ghost.library.frontend.panels;

import ghost.library.backend.services.UserService;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Window;
import java.text.DateFormat;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddUserPanel extends JPanel {
    
    private final UserService userService = new UserService();
    private final MainPanel mainPanel;

    private JTextField firstNameInput;
    private JTextField lastNameInput;

    public AddUserPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        createAddUserWindow();
    }

    private void createAddUserWindow() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(heading(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(firstNameLabel(), gbc);

        gbc.gridx = 1;
        add(firstNameInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lastNameLabel(), gbc);

        gbc.gridx = 1;
        add(lastNameInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(saveButton(), gbc);
    }

    private JPanel heading() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Add user");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(label);

        return panel;
    }

    private JPanel firstNameLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("First name: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel firstNameInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        firstNameInput = new JTextField(15);
        panel.add(firstNameInput);

        return panel;
    }

    private JPanel lastNameLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Last name: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel lastNameInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        lastNameInput = new JTextField(15);
        panel.add(lastNameInput);

        return panel;
    }

    private JPanel saveButton() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {

            String firstName = firstNameInput.getText();
            String lastName = lastNameInput.getText();
            if (firstName.length() > 0 && lastName.length() > 0) {
                userService.addUser(
                    firstName,
                    lastName
                );
                mainPanel.buildTable();
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
            }else {
                showWarningAlert("Fields cannot be empty");           
            }
        });

        panel.add(saveBtn);
        return panel;
    }

    private void showWarningAlert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
