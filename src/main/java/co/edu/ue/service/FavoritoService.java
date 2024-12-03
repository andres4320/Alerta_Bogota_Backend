package co.edu.ue.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.ue.dao.IFavoritoDao;
import co.edu.ue.entity.Favorito;

@Service
public class FavoritoService implements IFavoritoService {

    @Autowired
    IFavoritoDao dao;

    @Override
    public List<Favorito> listarTodosFavoritos() {
        return dao.listarTodosFavoritos();
    }

    @Override
    public boolean crearFavorito(Favorito favorito) {
        return dao.crearFavorito(favorito);
    }

    @Override
    public boolean eliminarFavorito(int id) {
        return dao.eliminarFavorito(id);
    }
}