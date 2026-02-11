package ghost.library.frontend.pages;

import ghost.library.frontend.panels.EditUserPanel;
import ghost.library.frontend.panels.MainPanel;
import ghost.library.backend.entity.User;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JFrame;

public class EditUserPage {
    
    private final JFrame editUserFrame = new JFrame("Edit user");

    public EditUserPage() {}

    public void createEditUserWindow(User user, MainPanel mainPanel) {
        FlatLightLaf.setup();

        editUserFrame.add(new EditUserPanel(user, mainPanel));
        editUserFrame.pack();
        editUserFrame.setLocationRelativeTo(null);
        editUserFrame.setVisible(true);
        editUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
