package co.edu.ue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ue.dao.IUserDao;
import co.edu.ue.entity.User;

@Service
public class UserService implements IUserService {

   @Autowired
   private IUserDao dao;

   @Override
   public List<User> listAllUsers() {
       return dao.listUsers();
   }

   @Override
   public User searchByIdUser(int id) {
       return dao.searchById(id);
   }

   @Override
   public boolean postUser(User user) {
       return dao.postUser(user);
   }

   @Override
   public boolean updateUser(int id, User user) {
       return dao.updateUser(id, user);
   }

   @Override
   public boolean deleteUser(int id) {
       return dao.deleteUser(id);
   }
}