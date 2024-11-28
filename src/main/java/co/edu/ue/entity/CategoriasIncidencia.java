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

	//bi-directional many-to-one association to AuditoriaIncidencia
	@OneToMany(mappedBy="categoriasIncidencia")
	private List<AuditoriaIncidencia> auditoriaIncidencias;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="categoriasIncidencia")
	private List<Incidencia> incidencias;

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

	public List<AuditoriaIncidencia> getAuditoriaIncidencias() {
		return this.auditoriaIncidencias;
	}

	public void setAuditoriaIncidencias(List<AuditoriaIncidencia> auditoriaIncidencias) {
		this.auditoriaIncidencias = auditoriaIncidencias;
	}

	public AuditoriaIncidencia addAuditoriaIncidencia(AuditoriaIncidencia auditoriaIncidencia) {
		getAuditoriaIncidencias().add(auditoriaIncidencia);
		auditoriaIncidencia.setCategoriasIncidencia(this);

		return auditoriaIncidencia;
	}

	public AuditoriaIncidencia removeAuditoriaIncidencia(AuditoriaIncidencia auditoriaIncidencia) {
		getAuditoriaIncidencias().remove(auditoriaIncidencia);
		auditoriaIncidencia.setCategoriasIncidencia(null);

		return auditoriaIncidencia;
	}

	public List<Incidencia> getIncidencias() {
		return this.incidencias;
	}

	public void setIncidencias(List<Incidencia> incidencias) {
		this.incidencias = incidencias;
	}

	public Incidencia addIncidencia(Incidencia incidencia) {
		getIncidencias().add(incidencia);
		incidencia.setCategoriasIncidencia(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setCategoriasIncidencia(null);

		return incidencia;
	}

}