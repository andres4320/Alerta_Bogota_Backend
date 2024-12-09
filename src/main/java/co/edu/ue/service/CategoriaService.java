package co.edu.ue.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ue.dao.ICategoriaDao;
import co.edu.ue.entity.CategoriasIncidencia;

@Service
public class CategoriaService implements ICategoriaService {
    
	@Autowired
	private ICategoriaDao dao;

    @Override
    public List<CategoriasIncidencia> listarTodasCategorias() {
        return dao.listarTodasCategorias();
    }

    @Override
    public List<CategoriasIncidencia> buscarNombreCategoria(String nombreCategoria) {
        return dao.buscarNombreCategoria(nombreCategoria);
    }

    @Override
    public boolean crearCategoria(CategoriasIncidencia categoria) {
        return dao.crearCategoria(categoria);
    }

    @Override
    public boolean actualizarCategoria(int id, CategoriasIncidencia categoria) {
        return dao.actualizarCategoria(id, categoria);
    }

    @Override
    public boolean eliminarCategoria(int id) {
        return dao.eliminarCategoria(id);
    }

	@Override
	public Long countCategories() {
		return dao.countCategories();
	}

	@Override
	public List<Map<String, Long>> countMostUsedCategories() {
		return dao.countMostUsedCategories();
	}
}