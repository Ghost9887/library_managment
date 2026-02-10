package ghost.library.frontend.panels;

import ghost.library.backend.services.BookService;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
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

public class AddBookPanel extends JPanel {
    
    private final BookService bookService = new BookService();

    private JTextField titleInput;
    private JTextField authorInput;
    private JFormattedTextField releaseInput;

    public AddBookPanel() {
        createAddBookWindow();
    }

    private void createAddBookWindow() {
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
        add(titleLabel(), gbc);

        gbc.gridx = 1;
        add(titleInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(authorLabel(), gbc);

        gbc.gridx = 1;
        add(authorInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(releaseLabel(), gbc);

        gbc.gridx = 1;
        add(releaseInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(saveButton(), gbc);
    }

    private JPanel heading() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Add book");
        label.setFont(new Font("Serif", Font.BOLD, 18));
        panel.add(label);

        return panel;
    }

    private JPanel titleLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Title: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel titleInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        titleInput = new JTextField(15);
        panel.add(titleInput);

        return panel;
    }

    private JPanel authorLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Author: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel authorInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        authorInput = new JTextField(15);
        panel.add(authorInput);

        return panel;
    }

    private JPanel releaseLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Release Date: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel releaseInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));
        
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        DateFormatter df = new DateFormatter(format);
        df.setAllowsInvalid(false);
        df.setOverwriteMode(true);

        releaseInput = new JFormattedTextField(df);
        releaseInput.setColumns(10);
        releaseInput.setValue(new Date());

        panel.add(releaseInput);


        return panel;
    }

    private JPanel saveButton() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            bookService.add(
                titleInput.getText(),
                authorInput.getText(),
                releaseInput.getText()
            );
            
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

        panel.add(saveBtn);
        return panel;
    }

}
