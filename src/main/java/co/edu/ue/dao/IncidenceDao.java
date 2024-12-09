package co.edu.ue.dao;


import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Incidencia;
import co.edu.ue.jpa.IIncidenceJpa;

@Repository
public class IncidenceDao implements IIncidenceDao {
	
    @Autowired
    IIncidenceJpa jpa;

    @Override
    public List<Incidencia> listIncidences() {
        return jpa.findAll();
    }
    @Override
    public List<Incidencia> searchByLocality(String localidad) {
        return jpa.findByLocalidad(localidad); 
    }
    
    @Override
    public List<Incidencia> searchByCategory(String nombreCategoria) {
        return jpa.findByCategoriasIncidencia_nombreCategoria(nombreCategoria); 
    }

    @Override
    public List<Incidencia> searchByDate(Date fecha) {
        return jpa.findByFecha(fecha); 
    }

    @Override
    public List<Incidencia> searchByUsuarioId(int usuarioId) {
        return jpa.findByUsuario_usuarioId(usuarioId);
    }

    
    @Override
    public boolean postIncidence(Incidencia incidencia) {
        try {
            jpa.save(incidencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean deleteIncidence(int id) {
        if (jpa.existsById(id)) { 
            jpa.deleteById(id); 
            return true; 
        }
        return false; 
    }

    @Override
    public boolean updateIncidence(int id,Incidencia incidencia) {
        if (jpa.existsById(incidencia.getIncidenciaId())) { 
            Incidencia incidenciaEditar = jpa.findById(incidencia.getIncidenciaId()).get(); 

            
            incidenciaEditar.setDescripcion(incidencia.getDescripcion());
            incidenciaEditar.setDireccion(incidencia.getDireccion());
            incidenciaEditar.setFecha(incidencia.getFecha());
            incidenciaEditar.setLatitud(incidencia.getLatitud());
            incidenciaEditar.setLocalidad(incidencia.getLocalidad());
            incidenciaEditar.setLongitud(incidencia.getLongitud());
            incidenciaEditar.setUsuario(incidencia.getUsuario());
            incidenciaEditar.setCategoriasIncidencia(incidencia.getCategoriasIncidencia());

            try {
                jpa.save(incidenciaEditar); 
                return true; 
            } catch (Exception e) {
                return false; 
            }
        }
        return false; 
    }
    
    @Override
    public Long countIncidences() {
        return jpa.countIncidencias();
    }

    @Override
    public List<Map<String, Long>> countIncidencesByLocality() {
        return jpa.countIncidencesByLocality();
    }

    @Override
    public List<Map<String, Long>> countIncidencesByCategory() {
        return jpa.countIncidencesByCategory();
    }

    @Override
    public List<Map<String, Long>> countIncidencesByDate() {
        return jpa.countIncidencesByDate();
    }
}
