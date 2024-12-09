package co.edu.ue.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.ue.entity.Usuario;

public interface IUserJpa extends JpaRepository<Usuario, Integer> {
	Usuario findByUseEmail(String useEmail);
	@Query("SELECT u.rolId AS rol, COUNT(u) AS total FROM Usuario u GROUP BY u.rolId")
	List<Map<String, Long>> countUsersByRole();
	@Query("SELECT FUNCTION('MONTH', u.fechaRegistro) AS mes, COUNT(u) AS total FROM Usuario u GROUP BY FUNCTION('MONTH', u.fechaRegistro)")
	List<Map<String, Long>> countUsersByRegistrationMonth();
}