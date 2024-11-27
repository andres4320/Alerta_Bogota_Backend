package co.edu.ue.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.ue.entity.User;
import co.edu.ue.jpa.IUserJpa;

@Repository
public class UserDao implements IUserDao {

    @Autowired
    private IUserJpa jpa;

    @Override
    public List<User> listUsers() {
        return jpa.findAll();
    }

    /*@Override
    public User searchById(int id) {
        //return jpa.findById(id).orElse(null);
    }*/

    @Override
    public boolean postUser(User user) {
        if (jpa.save(user) != null) return true;
        return false;
    }

    @Override
    public boolean updateUser(int id, User user) {
        if (jpa.existsById(id)) {
            user.setUserId(id);
            jpa.save(user);
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
	public User searchById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}