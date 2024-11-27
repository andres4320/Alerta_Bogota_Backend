package co.edu.ue.service;

import java.util.List;
import co.edu.ue.entity.User;

public interface IUserService {
    List<User> listAllUsers();
    User searchByIdUser(int id);
    boolean postUser(User user);
    boolean updateUser(int id, User user);
    boolean deleteUser(int id);
}