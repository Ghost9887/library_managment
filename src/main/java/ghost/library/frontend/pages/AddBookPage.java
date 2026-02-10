package ghost.library.frontend.pages;

import ghost.library.frontend.panels.AddBookPanel;
import ghost.library.frontend.panels.MainPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JFrame;

public class AddBookPage {
    
    private final JFrame addBookFrame = new JFrame("Add book");

    public AddBookPage() {}

    public void createAddBookWindow(MainPanel mainPanel) {
        FlatLightLaf.setup();

        addBookFrame.add(new AddBookPanel(mainPanel));
        addBookFrame.pack();
        addBookFrame.setLocationRelativeTo(null);
        addBookFrame.setVisible(true);
        addBookFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
