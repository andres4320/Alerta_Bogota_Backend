package co.edu.ue.service;

import java.util.List;
import java.util.Map;

import co.edu.ue.entity.Usuario;

public interface IUserService {
   List<Usuario> listAllUsers();
   Usuario searchByIdUser(int id);
   boolean postUser(Usuario usuario);
   boolean updateUser(int id, Usuario usuario);
   boolean deleteUser(int id);
   boolean doesEmailExist(String email);
   Usuario findByUseEmail(String email);
List<Map<String, Long>> countUsersByRole();
List<Map<String, Long>> countUsersByRegistrationMonth();
}