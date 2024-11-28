package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;


/**
 * The persistent class for the auditoria_incidencias database table.
 * 
 */
@Entity
@Table(name="auditoria_incidencias")
@NamedQuery(name="AuditoriaIncidencia.findAll", query="SELECT a FROM AuditoriaIncidencia a")
public class AuditoriaIncidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="auditoria_id")
	private int auditoriaId;

	@Column(name="campo_editado")
	private String campoEditado;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_edicion")
	private Date fechaEdicion;

	@Lob
	@Column(name="valor_anterior")
	private String valorAnterior;

	@Lob
	@Column(name="valor_nuevo")
	private String valorNuevo;

	//bi-directional many-to-one association to CategoriasIncidencia
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private CategoriasIncidencia categoriasIncidencia;

	//bi-directional many-to-one association to Incidencia
	@ManyToOne
	@JoinColumn(name="incidencia_id")
	private Incidencia incidencia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	public AuditoriaIncidencia() {
	}

	public int getAuditoriaId() {
		return this.auditoriaId;
	}

	public void setAuditoriaId(int auditoriaId) {
		this.auditoriaId = auditoriaId;
	}

	public String getCampoEditado() {
		return this.campoEditado;
	}

	public void setCampoEditado(String campoEditado) {
		this.campoEditado = campoEditado;
	}

	public Date getFechaEdicion() {
		return this.fechaEdicion;
	}

	public void setFechaEdicion(Date fechaEdicion) {
		this.fechaEdicion = fechaEdicion;
	}

	public String getValorAnterior() {
		return this.valorAnterior;
	}

	public void setValorAnterior(String valorAnterior) {
		this.valorAnterior = valorAnterior;
	}

	public String getValorNuevo() {
		return this.valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

	public CategoriasIncidencia getCategoriasIncidencia() {
		return this.categoriasIncidencia;
	}

	public void setCategoriasIncidencia(CategoriasIncidencia categoriasIncidencia) {
		this.categoriasIncidencia = categoriasIncidencia;
	}

	public Incidencia getIncidencia() {
		return this.incidencia;
	}

	public void setIncidencia(Incidencia incidencia) {
		this.incidencia = incidencia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}