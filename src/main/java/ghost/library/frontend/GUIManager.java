package ghost.library.frontend;

public final class GUIManager {
    
    private final MainPage mainPage = new MainPage();

    public void run() {
        System.out.println("Running...");
        mainPage.createWindow();
    }

}
