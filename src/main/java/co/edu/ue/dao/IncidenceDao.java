package co.edu.ue.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import co.edu.ue.entity.Incidencia;
import co.edu.ue.jpa.IIncidenceJpa;

@Repository
public class IncidenceDao implements IIncidenceDao {
	
    @Autowired
    IIncidenceJpa jpa;

    @Override
    public List<Incidencia> listarTodasIncidencias() {
        return jpa.findAll();
    }
    @Override
    public List<Incidencia> buscarPorLocalidad(String localidad) {
        return jpa.findByLocalidad(localidad); 
    }


    @Override
    public boolean crearIncidencia(Incidencia incidencia) {
        try {
            jpa.save(incidencia);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    @Override
    public boolean eliminarIncidencia(int id) {
        if (jpa.existsById(id)) { // Verifica si la incidencia existe
            jpa.deleteById(id); // Elimina la incidencia por ID
            return true; // Retorna true si se elimina correctamente
        }
        return false; // Retorna false si no existe
    }

    @Override
    public boolean actualizarIncidencia(int id,Incidencia incidencia) {
        if (jpa.existsById(incidencia.getIncidenciaId())) { // Verifica si la incidencia existe
            Incidencia incidenciaEditar = jpa.findById(incidencia.getIncidenciaId()).get(); // Obtiene la incidencia existente

            // Actualiza los campos necesarios de la incidencia existente
            incidenciaEditar.setDescripcion(incidencia.getDescripcion());
            incidenciaEditar.setDireccion(incidencia.getDireccion());
            incidenciaEditar.setFecha(incidencia.getFecha());
            incidenciaEditar.setLatitud(incidencia.getLatitud());
            incidenciaEditar.setLocalidad(incidencia.getLocalidad());
            incidenciaEditar.setLongitud(incidencia.getLongitud());
            incidenciaEditar.setUsuario(incidencia.getUsuario());
            incidenciaEditar.setCategoriasIncidencia(incidencia.getCategoriasIncidencia());

            try {
                jpa.save(incidenciaEditar); // Guarda la incidencia actualizada
                return true; // Retorna true si se actualiza correctamente
            } catch (Exception e) {
                return false; // Manejo básico de excepciones
            }
        }
        return false; // Retorna false si no existe la incidencia a actualizar
    }


}
