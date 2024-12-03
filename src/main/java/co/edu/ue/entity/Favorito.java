package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
@NamedQuery(name = "Favorito.findAll", query = "SELECT f FROM Favorito f")
public class Favorito implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorito_id")
    private int favoritoId;

    @Column(name = "incidencia_id")
    private int incidenciaId;

    @Column(name = "usuario_id")
    private int usuarioId;

    public Favorito() {
    }

    public int getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(int favoritoId) {
        this.favoritoId = favoritoId;
    }

    public int getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(int incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}