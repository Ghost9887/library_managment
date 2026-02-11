package ghost.library.backend.services;

import ghost.library.backend.entity.User;
import ghost.library.backend.repo.UserRepository;
import java.util.List;

public final class UserService {

    private final UserRepository userRepository = new UserRepository();
    
    public void addUser(String firstName, String lastName) {
        User newUser = new User(firstName, lastName);
        userRepository.add(newUser);
    }

    public void editUser() {
    }

    public void deleteUser() {
    }

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

}
