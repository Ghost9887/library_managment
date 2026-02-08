package ghost.library.frontend;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import ghost.library.frontend.panels.MainPanel;

public final class MainPage {

    private final JFrame mainFrame = new JFrame("Library");
    private final int width = 1000;
    private final int height = 750;

    public void createWindow() {
        FlatLightLaf.setup();
        initMainFrame();
        mainFrame.add(new MainPanel());
    }

    private void initMainFrame() {
        mainFrame.setSize(width, height);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
