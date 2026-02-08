package ghost.library.frontend;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.JFrame;

import ghost.library.frontend.panels.MainPanel;

public final class MainPage {

    private final JFrame mainFrame = new JFrame("Library");
    private final int width = 1000;
    private final int height = 750;

    public void createWindow() {
        FlatLightLaf.setup();

        mainFrame.add(new MainPanel());

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
