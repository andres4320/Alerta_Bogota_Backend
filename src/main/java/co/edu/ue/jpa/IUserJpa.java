package co.edu.ue.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ue.entity.User;

public interface IUserJpa extends JpaRepository<User, Integer> {
   
}