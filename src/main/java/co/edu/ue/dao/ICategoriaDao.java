package co.edu.ue.dao;

import java.util.List;
import java.util.Map;

import co.edu.ue.entity.CategoriasIncidencia;

public interface ICategoriaDao {
    List<CategoriasIncidencia> listarTodasCategorias();
    List<CategoriasIncidencia> buscarNombreCategoria(String nombreCategoria);
    boolean crearCategoria(CategoriasIncidencia categoria);
    boolean actualizarCategoria(int id, CategoriasIncidencia categoria);
    boolean eliminarCategoria(int id);
	Long countCategories();
	List<Map<String, Long>> countMostUsedCategories();
}