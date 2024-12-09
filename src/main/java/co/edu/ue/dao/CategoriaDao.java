package co.edu.ue.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.ue.entity.CategoriasIncidencia;
import co.edu.ue.jpa.ICategoriaJpa;

@Repository
public class CategoriaDao implements ICategoriaDao {
    
    @Autowired
    ICategoriaJpa jpa;

    @Override
    public List<CategoriasIncidencia> listarTodasCategorias() {
        return jpa.findAll();
    }

    @Override
    public List<CategoriasIncidencia> buscarNombreCategoria(String nombreCategoria) {
        return jpa.findByNombreCategoria(nombreCategoria);
    }

    @Override
    public boolean crearCategoria(CategoriasIncidencia categoria) {
        try {
            jpa.save(categoria);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean actualizarCategoria(int id, CategoriasIncidencia categoria) {
        if (jpa.existsById(id)) { // Verifica si la categoría existe
            CategoriasIncidencia categoriaEditar = jpa.findById(id).get(); // Obtiene la categoría existente

            // Actualiza los campos necesarios de la categoría existente
            categoriaEditar.setDescripcionCategoria(categoria.getDescripcionCategoria());
            categoriaEditar.setNombreCategoria(categoria.getNombreCategoria());

            try {
                jpa.save(categoriaEditar); // Guarda la categoría actualizada
                return true; // Retorna true si se actualiza correctamente
            } catch (Exception e) {
                return false; // Manejo básico de excepciones
            }
        }
        return false; // Retorna false si no existe la categoría a actualizar
    }

    @Override
    public boolean eliminarCategoria(int id) {
        if (jpa.existsById(id)) { // Verifica si la categoría existe
            jpa.deleteById(id); // Elimina la categoría por ID
            return true; // Retorna true si se elimina correctamente
        }
        return false; // Retorna false si no existe
    }

    @Override
	public Long countCategories() {
		return jpa.countCategories();
	}

	@Override
	public List<Map<String, Long>> countMostUsedCategories() {
		return jpa.countMostUsedCategories();
	}
}