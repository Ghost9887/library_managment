package ghost.library.frontend.pages;

import ghost.library.frontend.panels.EditBookPanel;
import ghost.library.backend.entity.Book;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JFrame;

public class EditBookPage {
    
    private final JFrame editBookFrame = new JFrame("Edit book");

    public EditBookPage() {}

    public void createEditBookWindow(Book book) {
        FlatLightLaf.setup();

        editBookFrame.add(new EditBookPanel(book));
        editBookFrame.pack();
        editBookFrame.setLocationRelativeTo(null);
        editBookFrame.setVisible(true);
        editBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
