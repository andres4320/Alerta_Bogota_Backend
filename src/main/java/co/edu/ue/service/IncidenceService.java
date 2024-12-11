package co.edu.ue.service;


import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.ue.dao.IIncidenceDao;
import co.edu.ue.entity.Incidencia;

@Service
public class IncidenceService implements IIncidenceService{
	
    @Autowired
    IIncidenceDao dao;

    @Override
    public List<Incidencia> listIncidences() {
        return dao.listIncidences();
    }
    
    public List<Incidencia> searchByLocality(String localidad) {
		return dao.searchByLocality(localidad);
	}


	@Override
	public List<Incidencia> searchByDate(Date fecha) {
		System.out.println("Fecha enviada al repositorio: " + fecha);
		return dao.searchByDate(fecha); 
	}

    @Override
    public boolean postIncidence(Incidencia incidencia) {
        return dao.postIncidence(incidencia);
    }
    
    @Override
    public boolean updateIncidence(int id, Incidencia incidencia) {
        return dao.updateIncidence(id, incidencia);
    }

    @Override
    public boolean deleteIncidence(int id) {
        return dao.deleteIncidence(id);
    }
     @Override
    public List<Incidencia> searchByUsuarioId(int usuarioId) {
        return dao.searchByUsuarioId(usuarioId);
  }  
   
    @Override
	public List<Incidencia> searchByCategory(String nombreCategoria) {
		return dao.searchByCategory(nombreCategoria); 
	}

    @Override
    public List<Map<String, Long>> countIncidencesByLocality() {
        return dao.countIncidencesByLocality();
    }

    @Override
    public List<Map<String, Long>> countIncidencesByCategory() {
        return dao.countIncidencesByCategory();
    }

    @Override
    public List<Map<String, Long>> countIncidencesByDate() {
        return dao.countIncidencesByDate();
    }
}
