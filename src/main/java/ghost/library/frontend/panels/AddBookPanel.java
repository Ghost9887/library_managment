package ghost.library.frontend.panels;

import ghost.library.backend.services.BookService;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Window;

public class AddBookPanel extends JPanel {
    
    private final BookService bookService = new BookService();

    private JTextField titleInput;
    private JTextField authorInput;
    private JTextField yearInput;

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
        add(yearLabel(), gbc);

        gbc.gridx = 1;
        add(yearInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(saveButton(), gbc);
    }

    private JPanel heading() {
        JPanel heading = new JPanel();
        heading.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel headingLabel = new JLabel("Add book");
        headingLabel.setFont(new Font("Serif", Font.BOLD, 18));
        heading.add(headingLabel);

        return heading;
    }

    private JPanel titleLabel() {
        JPanel title = new JPanel();
        title.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel titleLabel = new JLabel("Title: ");
        titleLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        title.add(titleLabel);

        return title;
    }

    private JPanel titleInput() {
        JPanel input = new JPanel();
        input.setBorder(new EmptyBorder(5, 5, 5, 50));

        titleInput = new JTextField(20);
        input.add(titleInput);

        return input;
    }

    private JPanel authorLabel() {
        JPanel author = new JPanel();
        author.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel authorLabel = new JLabel("Author: ");
        authorLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        author.add(authorLabel);

        return author;
    }

    private JPanel authorInput() {
        JPanel input = new JPanel();
        input.setBorder(new EmptyBorder(5, 5, 5, 50));

        authorInput = new JTextField(20);
        input.add(authorInput);

        return input;
    }

    private JPanel yearLabel() {
        JPanel year = new JPanel();
        year.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel yearLabel = new JLabel("Year: ");
        yearLabel.setFont(new Font("Serif", Font.PLAIN, 13));
        year.add(yearLabel);

        return year;
    }

    private JPanel yearInput() {
        JPanel input = new JPanel();
        input.setBorder(new EmptyBorder(5, 5, 5, 50));

        yearInput = new JTextField(5);
        input.add(yearInput);

        return input;
    }

    private JPanel saveButton() {
        JPanel save = new JPanel();

        save.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {
            bookService.add(
                titleInput.getText(),
                authorInput.getText(),
                yearInput.getText()
            );
            
            Window window = SwingUtilities.getWindowAncestor(this);
            if (window != null) {
                window.dispose();
            }
        });

        save.add(saveBtn);
        return save;
    }

}
