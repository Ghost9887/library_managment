package ghost.library.frontend.panels;

import ghost.library.backend.services.BookService;
import ghost.library.backend.entity.Book;
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

public class EditBookPanel extends JPanel {
    
    private final BookService bookService = new BookService();
    private final MainPanel mainPanel;
    private Book book;

    private JTextField titleInput;
    private JTextField authorInput;
    private JFormattedTextField releaseInput;
    private JCheckBox availableInput;

    public EditBookPanel(Book book, MainPanel mainPanel) {
        this.book = book;
        this.mainPanel = mainPanel;
        createEditBookWindow();
    }

    private void createEditBookWindow() {
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
        add(titleLabel(), gbc);

        gbc.gridx = 1;
        add(titleInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(authorLabel(), gbc);

        gbc.gridx = 1;
        add(authorInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(releaseLabel(), gbc);

        gbc.gridx = 1;
        add(releaseInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(availableLabel(), gbc);

        gbc.gridx = 1;
        add(availableInput(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        add(saveButton(), gbc);
    }

    private JPanel heading() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel label = new JLabel("Edit book");
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

        JTextField idInput = new JTextField(String.valueOf(book.getId()), 10);
        idInput.setEditable(false);
        panel.add(idInput);

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

        titleInput = new JTextField(book.getTitle(), 15);
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

        authorInput = new JTextField(book.getAuthor(), 15);
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
        
        try {
            Date date = format.parse(book.getReleaseDate());
            releaseInput.setValue(date);
        }catch (ParseException e) {
            showWarningAlert("Failed to parse Date field");
        }

        panel.add(releaseInput);
        return panel;
    }

    private JPanel availableLabel() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        JLabel label = new JLabel("Available: ");
        label.setFont(new Font("Serif", Font.PLAIN, 13));
        panel.add(label);

        return panel;
    }

    private JPanel availableInput() {
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5, 5, 50));

        availableInput = new JCheckBox("", book.isAvailable()); 
        panel.add(availableInput);

        return panel;
    } 

    private JPanel saveButton() {
        JPanel panel = new JPanel();

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        JButton saveBtn = new JButton("Save");
        saveBtn.addActionListener(e -> {

            String title = titleInput.getText();
            String author = authorInput.getText();
            String release = releaseInput.getText();

            if (title.length() > 0 && author.length() > 0) {
                book.setTitle(title);
                book.setAuthor(author);
                book.setAvailable(availableInput.isSelected() ? 1 : 0);
                book.setReleaseDate(release);

                bookService.updateBook(book);
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
