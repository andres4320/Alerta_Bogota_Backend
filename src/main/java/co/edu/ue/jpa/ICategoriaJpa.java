package co.edu.ue.jpa;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.ue.entity.CategoriasIncidencia;

public interface ICategoriaJpa extends JpaRepository<CategoriasIncidencia, Integer> {
    List<CategoriasIncidencia> findByNombreCategoria(String nombreCategoria);
    @Query("SELECT COUNT(c) FROM CategoriasIncidencia c")
    Long countCategories();
    @Query("SELECT c.nombreCategoria AS nombre, COUNT(i) AS total FROM Incidencia i JOIN i.categoriasIncidencia c GROUP BY c.nombreCategoria ORDER BY total DESC")
    List<Map<String, Long>> countMostUsedCategories();
}