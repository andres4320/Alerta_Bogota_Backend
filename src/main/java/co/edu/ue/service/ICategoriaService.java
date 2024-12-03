package co.edu.ue.service;

import java.util.List;
import co.edu.ue.entity.CategoriasIncidencia;

public interface ICategoriaService {    
    List<CategoriasIncidencia> listarTodasCategorias();
    List<CategoriasIncidencia> buscarNombreCategoria(String nombreCategoria);
    boolean crearCategoria(CategoriasIncidencia categoria);
    boolean actualizarCategoria(int id, CategoriasIncidencia categoria);
    boolean eliminarCategoria(int id);
	
}