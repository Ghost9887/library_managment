package ghost.library.frontend.panels;

import ghost.library.frontend.pages.AddBookPage;
import ghost.library.frontend.pages.EditBookPage;
import ghost.library.frontend.pages.AddUserPage;
import ghost.library.backend.entity.Book;
import ghost.library.backend.services.BookService;
import ghost.library.backend.entity.User;
import ghost.library.backend.services.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
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
    enum Tab {
        Books,
        Users
    }

    private final BookService bookService = new BookService();
    private final UserService userSerivce = new UserService();
    private JTable bookTable;
    private JTable userTable;
    private JPanel tablePanel;
    private JPanel booksPanel;
    private JPanel usersPanel;
    private JTabbedPane tabPane;
    private Tab currentTab = Tab.Books;

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

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(searchPanel(), gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(sidePanel(), gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.BOTH;   
        gbc.weightx = 10.0;
        gbc.weighty = 10.0;
        add(tablePanel(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(footerPanel(), gbc);
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
        refresh.addActionListener(e -> buildTable());

        JButton add = new JButton("add");
        add.addActionListener(e -> {
            if (currentTab == Tab.Books) {
                new AddBookPage().createAddBookWindow(this);
            }else {
                new AddUserPage().createAddUserWindow(this);
            }
        });

        JButton edit = new JButton("edit");
        edit.addActionListener(e -> {
            int[] rows = bookTable.getSelectedRows();
            if (rows.length == 1) {

                Book book = bookService.getBookById(String.valueOf(bookTable.getValueAt(rows[0], 0)));
                if (book != null) {
                    new EditBookPage().createEditBookWindow(book, this);
                }else {
                    showWarningAlert("No book found");
                }
            }else if (rows.length > 1) {
                showWarningAlert("Too many books selected");
            }else {
                showWarningAlert("Nothing selected");
            }
        });

        JButton delete = new JButton("delete");
        delete.addActionListener(e -> {
            int[] rows = bookTable.getSelectedRows();
            if (rows.length > 0) {
                String message = "Are you sure you want to delete (" + rows.length + ") books?";
                if (showConfirmationAlert(message)) {
                    for (int i = 0; i < rows.length; i++) {
                        bookService.deleteBookById(String.valueOf(bookTable.getValueAt(rows[i], 0)));
                    }
                    buildTable();
                }
            }else {
                showWarningAlert("Nothing selected");
            }
        });

        return List.of(refresh, add, edit, delete);
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

    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel();

        JTextField searchInput = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        searchPanel.add(searchInput);
        searchPanel.add(searchBtn);

        return searchPanel;
    }

    private JPanel tablePanel() {
        tablePanel = new JPanel();

        tabPane = new JTabbedPane();

        tabPane.addTab("Books", new JPanel());
        tabPane.addTab("Users", new JPanel());

        tabPane.addChangeListener(e -> buildTable());
        tablePanel.add(tabPane);

        buildTable(); 

        return tablePanel;
    }

    public void buildTable() {
        int index = tabPane.getSelectedIndex();
        if (index == 1) {
            currentTab = Tab.Users;
            buildUserTable();
        } else {
            currentTab = Tab.Books;
            buildBookTable();
        }
    }

    private void buildUserTable() {
        tablePanel.removeAll();

        List<User> usersList = userSerivce.getAllUsers();

        String[] columns = {"Id", "First name", "Last name"};

        Object[][] data = usersList.stream()
            .map(u -> new Object[]{
                u.getId(),
                u.getFirstName(),
                u.getLastName()
            })
            .toArray(Object[][]::new);

        userTable = new JTable(data, columns);
        JScrollPane scroll = new JScrollPane(userTable);
        scroll.setPreferredSize(new Dimension(1200, 700));

        tabPane.setComponentAt(0, new JPanel());
        tabPane.setComponentAt(1, scroll);

        tablePanel.add(tabPane);

        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private void buildBookTable() {
        tablePanel.removeAll();

        List<Book> booksList = bookService.getAllBooks();

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
        JScrollPane scroll = new JScrollPane(bookTable);
        scroll.setPreferredSize(new Dimension(1200, 700));

        tabPane.setComponentAt(0, scroll);
        tabPane.setComponentAt(1, new JPanel());

        tablePanel.add(tabPane);

        tablePanel.revalidate();
        tablePanel.repaint();
    }

    private JPanel footerPanel() {
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
