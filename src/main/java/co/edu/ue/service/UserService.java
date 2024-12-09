package co.edu.ue.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import co.edu.ue.dao.IUserDao;
import co.edu.ue.entity.Usuario;

@Service
public class UserService implements IUserService {

   @Autowired
   private IUserDao dao;

   @Autowired
   private BCryptPasswordEncoder passwordEncoder;

   @Override
   public List<Usuario> listAllUsers() {
       return dao.listUsers();
   }

   @Override
   public Usuario searchByIdUser(int id) {
       return dao.searchById(id);
   }
   
   @Override
   public boolean postUser(Usuario usuario) {
       String encryptedPassword = passwordEncoder.encode(usuario.getUsePass());
       usuario.setUsePass(encryptedPassword);
       return dao.postUser(usuario);
   }
   
   @Override
   public boolean updateUser(int id, Usuario usuario) {
       if (dao.searchById(id) != null) {
           usuario.setUsuarioId(id);
           
           if (usuario.getUsePass() != null && !usuario.getUsePass().isEmpty()) {
               String encryptedPassword = passwordEncoder.encode(usuario.getUsePass());
               usuario.setUsePass(encryptedPassword);
           }
           
           dao.updateUser(id, usuario); 
           return true;
       }
       return false;
   }

   @Override
   public boolean deleteUser(int id) {
       return dao.deleteUser(id);
   }

	@Override
	public boolean doesEmailExist(String email) {
		Usuario usuario = dao.findByUseEmail(email);
	    return usuario != null;
	}

	@Override
	public Usuario findByUseEmail(String email) {
		return dao.findByUseEmail(email);
	}

	@Override
	public List<Map<String, Long>> countUsersByRole() {
		return dao.countUsersByRole();
	}

	@Override
	public List<Map<String, Long>> countUsersByRegistrationMonth() {
		return dao.countUsersByRegistrationMonth();
	}
}