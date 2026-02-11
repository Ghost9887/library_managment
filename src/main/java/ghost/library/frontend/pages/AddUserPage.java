package ghost.library.frontend.pages;

import ghost.library.frontend.panels.AddUserPanel;
import ghost.library.frontend.panels.MainPanel;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JFrame;

public class AddUserPage {
    
    private final JFrame addUserFrame = new JFrame("Add user");

    public AddUserPage() {}

    public void createAddUserWindow(MainPanel mainPanel) {
        FlatLightLaf.setup();

        addUserFrame.add(new AddUserPanel(mainPanel));
        addUserFrame.pack();
        addUserFrame.setLocationRelativeTo(null);
        addUserFrame.setVisible(true);
        addUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
