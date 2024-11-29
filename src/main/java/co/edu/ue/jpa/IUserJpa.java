package co.edu.ue.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.ue.entity.Usuario;

public interface IUserJpa extends JpaRepository<Usuario, Integer> {
   
}