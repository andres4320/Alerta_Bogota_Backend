package co.edu.ue.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.ue.entity.Usuario;
import co.edu.ue.jpa.IUserJpa;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private IUserJpa jpa;

    @Override
    public List<Usuario> listUsers() {
        return jpa.findAll();
    }

    @Override
    public Usuario searchById(int id) {
        return jpa.findById(id).orElse(null);
    }

    @Override
    public boolean postUser(Usuario usuario) {
        return jpa.save(usuario) != null;
    }

    @Override
    public boolean updateUser(int id, Usuario usuario) {
        if (jpa.existsById(id)) {
            usuario.setUsuarioId(id);
            jpa.save(usuario);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(int id) {
        if (jpa.existsById(id)) {
            jpa.deleteById(id); 
            return true;
        }
        return false;
    }

    @Override
    public Usuario findByUseEmail(String email) {
        return jpa.findByUseEmail(email);
    }
}