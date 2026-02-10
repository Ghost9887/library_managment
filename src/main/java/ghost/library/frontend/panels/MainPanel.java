package ghost.library.frontend.panels;

import ghost.library.frontend.pages.AddBookPage;
import ghost.library.backend.entity.Book;
import ghost.library.backend.services.BookService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Window;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

public class MainPanel extends JPanel {
    
    private final BookService bookService = new BookService();
    private JTable bookTable;
    private JPanel bookTablePanel;

    public MainPanel() {
        createPanel();
    }

    private void createPanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(topPanel(), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(sidePanel(), gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;   
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        add(bookTablePanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(footer(), gbc);
    }

    private JPanel sidePanel() {
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BoxLayout(sidePanel, BoxLayout.Y_AXIS));
        List<JButton> buttons = sideButtons();
        for (JButton btn : buttons) {
            sidePanel.add(btn);
            sidePanel.add(Box.createVerticalStrut(10)); 
        }
        sidePanel.setBorder(new EmptyBorder(5, 0, 0, 5));
        return sidePanel;
    }

    private List<JButton> sideButtons() {
        JButton refresh = new JButton("refresh");
        refresh.addActionListener(e -> buildBookTable());

        JButton addBook = new JButton("add book");
        addBook.addActionListener(e -> new AddBookPage().createAddBookWindow());

        JButton editBook = new JButton("edit book");
        //editBook.addActionListener(e -> bookController.editBook());

        JButton deleteBook = new JButton("delete book");
        deleteBook.addActionListener(e -> {
            int[] rows = bookTable.getSelectedRows();
            if (rows.length > 0) {
                String message = "Are you sure you want to delete (" + rows.length + ") books?";
                if (showConfirmationAlert(message)) {
                    for (int i = 0; i < rows.length; i++) {
                        bookService.deleteBookById(String.valueOf(bookTable.getValueAt(rows[i], 0)));
                    }
                }
            }else {
                showWarningAlert("Nothing selected");
            }
        });

        JButton addUser = new JButton("add user");
        //addUser.addActionListener(e -> userController.addUser());

        JButton editUser = new JButton("edit user");
        //editUser.addActionListener(e -> userController.editUser());

        JButton deleteUser = new JButton("delete user");
        //deleteUser.addActionListener(e -> userController.deleteUser());

        //TEMPORARY       

        JButton printUsers = new JButton("Print Users");

        return List.of(refresh, addBook, editBook, deleteBook, addUser, editUser, deleteUser, printUsers);
    } 

    private JPanel topPanel() {
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        
        JButton file = new JButton("File");
        JButton view = new JButton("View");
        JButton options = new JButton("Options");
        JButton help = new JButton("Help");

        topPanel.add(file);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(view);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(options);
        topPanel.add(Box.createHorizontalStrut(10));
        topPanel.add(help);
        topPanel.setBorder(new EmptyBorder(5, 0, 20, 0));

        return topPanel;
    }

    private JPanel bookTablePanel() {
        bookTablePanel = new JPanel();

        buildBookTable();

        return bookTablePanel;
    }

    private void buildBookTable() {
        bookTablePanel.removeAll();
        List<Book> booksList = bookService.getAll();

        String[] columns = {"Id", "Title", "Author", "Release Date", "Available"};

        Object[][] data = booksList.stream()
            .map(b -> new Object[]{
                b.getId(),
                b.getTitle(),
                b.getAuthor(),
                b.getReleaseDate(),
                b.isAvailable() ? "Available" : "Taken"
            })
            .toArray(Object[][]::new);

        bookTable = new JTable(data, columns);
        bookTable.setPreferredScrollableViewportSize(new Dimension(1200, 700));

        JScrollPane scrollPane = new JScrollPane(bookTable);
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, false));

        bookTablePanel.add(scrollPane);

        bookTablePanel.revalidate();
        bookTablePanel.repaint();
    }

    private JPanel footer() {
        JPanel footer = new JPanel();

        JLabel footerLabel = new JLabel("Made by GhostSoftware");
        footerLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        footerLabel.setFont(new Font("Serif", Font.BOLD, 11));
        footer.add(footerLabel);

        return footer;
    }

    private void showWarningAlert(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    private boolean showConfirmationAlert(String message) {
        int res = JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION);
        return res == JOptionPane.YES_OPTION;
    }
}
