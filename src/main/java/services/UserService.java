package services;

import models.User;
import repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserRepository userRepository =  new UserRepository();

    public long addUser(User user) {
        try {
            return userRepository.create(user);
        } catch (SQLException e){
            System.out.println("Fout bij toevoegen van gebruiker: " + e.getMessage());
            return -1;
        }
    }

    public List<User> getAllUsers() {
        try {
            return userRepository.read();
        } catch (SQLException e){
            System.out.println("Fout bij ophalen van gebruiker: " + e.getMessage());
            return List.of();
        }
    }

    public boolean updateUser(int id, User user) {
        try {
            return userRepository.update(id, user) > 0;
        } catch (SQLException e){
            System.out.println("Fout bij ophalen van gebruiker: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteUser(int id) {
        try {
            return userRepository.delete(id) > 0;
        } catch (SQLException e){
            System.out.println("Fout bij verwijderen van gebruiker: " + e.getMessage());
            return false;
        }
    }
}
