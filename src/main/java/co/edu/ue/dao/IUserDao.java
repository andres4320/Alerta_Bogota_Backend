package co.edu.ue.dao;

import java.util.List;
import co.edu.ue.entity.User;

public interface IUserDao {
    List<User> listUsers();
    User searchById(int id);
    boolean postUser(User user);
    boolean updateUser(int id, User user);
    boolean deleteUser(int id);
}