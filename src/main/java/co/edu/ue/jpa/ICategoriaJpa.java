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
    @Query(value = "SELECT i FROM Incidencia i WHERE i.categoriaId = (SELECT c.categoriaId FROM CategoriasIncidencia c WHERE c.nombreCategoria = :nombreCategoria)")
    List<Map<String, Long>> countMostUsedCategories();
}