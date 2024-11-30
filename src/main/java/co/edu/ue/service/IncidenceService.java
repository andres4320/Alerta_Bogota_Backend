package co.edu.ue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.IIncidenceDao;
import co.edu.ue.entity.Incidencia;

@Service
public class IncidenceService implements IIncidenceService{
	
    @Autowired
    IIncidenceDao dao;

    @Override
    public List<Incidencia> listarTodasIncidencias() {
        return dao.listarTodasIncidencias();
    }
    
    public List<Incidencia> buscarPorLocalidad(String localidad) {
		return dao.buscarPorLocalidad(localidad);
	}

    @Override
    public boolean crearIncidencia(Incidencia incidencia) {
        return dao.crearIncidencia(incidencia);
    }
    
    @Override
    public boolean actualizarIncidencia(int id, Incidencia incidencia) {
        return dao.actualizarIncidencia(id, incidencia);
    }

    @Override
    public boolean eliminarIncidencia(int id) {
        return dao.eliminarIncidencia(id);
    }


}
