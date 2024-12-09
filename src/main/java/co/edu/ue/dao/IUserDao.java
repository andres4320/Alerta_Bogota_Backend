package co.edu.ue.dao;

import java.util.List;
import java.util.Map;

import co.edu.ue.entity.Usuario;

public interface IUserDao {
   List<Usuario> listUsers();
   Usuario searchById(int id);
   boolean postUser(Usuario usuario);
   boolean updateUser(int id, Usuario usuario);
   boolean deleteUser(int id);
   Usuario findByUseEmail(String email);
List<Map<String, Long>> countUsersByRole();
List<Map<String, Long>> countUsersByRegistrationMonth();
}