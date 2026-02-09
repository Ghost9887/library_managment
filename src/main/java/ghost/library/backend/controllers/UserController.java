package ghost.library.backend.controllers;

import ghost.library.backend.services.UserService;

public class UserController {

    private final UserService userService = new UserService();

    public UserController() {}

    public void addUser() {
        System.out.println("add user");
    }

    public void editUser() {
        System.out.println("edit user");
    }

    public void deleteUser() {
        System.out.println("delete user");
    }
}
