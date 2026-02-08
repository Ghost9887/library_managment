package ghost.library.frontend.panels;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.FlowLayout;

public class MainPanel extends JPanel {
    
    public MainPanel() {
        createPanel();
    }

    private void createPanel() {
        setLayout(new FlowLayout());
        add(new JButton("Hello"));
    }

}
