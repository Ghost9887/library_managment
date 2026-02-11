package ghost.library.frontend.panels;

import ghost.library.backend.services.UserService;
import ghost.library.backend.entity.User;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Window;
import java.text.DateFormat;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;

public class EditUserPanel extends JPanel {
    
    private final UserService userService = new UserService();
    private final MainPanel mainPanel;
    private User user;

    private JTextField firstNameInput;
    private JTextField lastNameInput;

    public EditUserPanel(User user, MainPanel mainPanel) {
        this.user = user;
        this.mainPanel = mainPanel;
        createEditUserWindow();
    }

    private void createEditUserWindow() {
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
        add(idLabel(), gbc);

        gbc.gridx = 1;
        add(idInput(), gbc);
        
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(firstNameLabel(), gbc);

        gbc.gridx = 1;
        add(firstNameInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lastNameLabel(), gbc);

        gbc.gridx = 1;
        add(lastNameInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(saveButton(), gbc);
    }

    private JPanel heading() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Edit user");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(label);

        return panel;
    }

    private JPanel idLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Id: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel idInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JTextField idInput = new JTextField(String.valueOf(user.getId()), 10);
        idInput.setEditable(false);
        panel.add(idInput);

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

        firstNameInput = new JTextField(user.getFirstName(), 15);
        panel.add(firstNameInput);

        return panel;
    }

    private JPanel lastNameLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("lastName: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel lastNameInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        lastNameInput = new JTextField(user.getLastName(), 15);
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
                user.setFirstName(firstName);
                user.setLastName(lastName);

                userService.updateUser(user);
                mainPanel.buildTable();           
                Window window = SwingUtilities.getWindowAncestor(this);
                if (window != null) {
                    window.dispose();
                }
            }else showWarningAlert("Fields cannot be empty");
        });

        panel.add(saveBtn);
        return panel;
    }

    private void showWarningAlert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}
