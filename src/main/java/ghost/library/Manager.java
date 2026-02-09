package ghost.library;

import ghost.library.frontend.MainPage;
import ghost.library.backend.Database;
import java.util.Optional;
import java.sql.Connection;
import java.io.File;

public final class Manager {
    
    public void run() {
        //check DB
        if (!checkIfDBExists()) {
            int code = Database.init();
            if (code == -1) {
                System.out.println("Failed to create database");
                System.exit(1);
            }
        }

        Database.connect();

        new MainPage().createWindow();
    }

    public boolean checkIfDBExists() {
        File f = new File("data/library.db");
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }

}
