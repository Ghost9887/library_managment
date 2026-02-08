package ghost.library.frontend.panels;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;

public class MainPanel extends JPanel {
    
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
        add(bookTable(), gbc);

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
        JButton addBook = new JButton("add book");
        JButton editBook = new JButton("edit book");
        JButton deleteBook = new JButton("delete book");
        JButton addUser = new JButton("add user");
        JButton editUser = new JButton("edit user");
        JButton deleteUser = new JButton("delete user");
        
        List<JButton> buttons = new ArrayList<>();
        buttons.add(addBook);
        buttons.add(editBook);
        buttons.add(deleteBook);
        buttons.add(addUser);
        buttons.add(editUser);
        buttons.add(deleteUser);

        return buttons;
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

    private JScrollPane bookTable() {
        String[] columns = {"Name", "Author", "Date", "Status"};
        Object[][] data = {
            {"Test1", "Jakub", "25.05.2025", "Available"},
            {"Test2", "Dominika", "08.02.2026", "Taken"},
            {"Test3", "JK Rowling", "13.03.2012", "Taken"}
        };

        JTable books = new JTable(data, columns);
        books.setPreferredScrollableViewportSize(new Dimension(800, 500));
        JScrollPane scrollPane = new JScrollPane(books);
        
        scrollPane.setBorder(new LineBorder(Color.GRAY, 1, false));

        return scrollPane;
    }

    private JPanel footer() {
        JPanel footer = new JPanel();

        JLabel footerLabel = new JLabel("Made by GhostSoftware");
        footerLabel.setBorder(new EmptyBorder(10, 10, 10, 10));
        footerLabel.setFont(new Font("Serif", Font.BOLD, 11));
        footer.add(footerLabel);

        return footer;
    }
}
