package co.edu.ue.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.edu.ue.entity.Favorito;
import co.edu.ue.jpa.IFavoritoJpa;

@Repository
public class FavoritoDao implements IFavoritoDao {

    @Autowired
    IFavoritoJpa jpa;

    @Override
    public List<Favorito> listarTodosFavoritos() {
        return jpa.findAll();
    }

    @Override
    public boolean crearFavorito(Favorito favorito) {
        try {
            jpa.save(favorito);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean eliminarFavorito(int id) {
        if (jpa.existsById(id)) {
            jpa.deleteById(id);
            return true;
        }
        return false;
    }
}