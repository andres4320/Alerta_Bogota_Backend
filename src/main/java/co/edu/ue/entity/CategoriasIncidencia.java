package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.List;


/**
 * The persistent class for the categorias_incidencias database table.
 * 
 */
@Entity
@Table(name="categorias_incidencias")
@NamedQuery(name="CategoriasIncidencia.findAll", query="SELECT c FROM CategoriasIncidencia c")
public class CategoriasIncidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="categoria_id")
	private int categoriaId;

	@Lob
	@Column(name="descripcion_categoria")
	private String descripcionCategoria;

	@Column(name="nombre_categoria")
	private String nombreCategoria;


	public CategoriasIncidencia() {
	}

	public int getCategoriaId() {
		return this.categoriaId;
	}

	public void setCategoriaId(int categoriaId) {
		this.categoriaId = categoriaId;
	}

	public String getDescripcionCategoria() {
		return this.descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


}