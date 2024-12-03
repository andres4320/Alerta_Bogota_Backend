package co.edu.ue.service;

import java.util.List;
import co.edu.ue.entity.Favorito;

public interface IFavoritoService {
    List<Favorito> listarTodosFavoritos();
    boolean crearFavorito(Favorito favorito);
    boolean eliminarFavorito(int id);
}