package co.edu.ue.dao;

import java.util.List;
import co.edu.ue.entity.Favorito;

public interface IFavoritoDao {
    List<Favorito> listarTodosFavoritos();
    boolean crearFavorito(Favorito favorito);
    boolean eliminarFavorito(int id);
}