package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the incidencias database table.
 * 
 */
@Entity
@Table(name="incidencias")
@NamedQuery(name="Incidencia.findAll", query="SELECT i FROM Incidencia i")
public class Incidencia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="incidencia_id")
	private int incidenciaId;

	@Lob
	private String descripcion;

	private String direccion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	private BigDecimal latitud;

	private String localidad;

	private BigDecimal longitud;


	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;

	//bi-directional many-to-one association to CategoriasIncidencia
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private CategoriasIncidencia categoriasIncidencia;

	public Incidencia() {
	}

	public int getIncidenciaId() {
		return this.incidenciaId;
	}

	public void setIncidenciaId(int incidenciaId) {
		this.incidenciaId = incidenciaId;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getLatitud() {
		return this.latitud;
	}

	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public BigDecimal getLongitud() {
		return this.longitud;
	}

	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public CategoriasIncidencia getCategoriasIncidencia() {
		return this.categoriasIncidencia;
	}

	public void setCategoriasIncidencia(CategoriasIncidencia categoriasIncidencia) {
		this.categoriasIncidencia = categoriasIncidencia;
	}

}