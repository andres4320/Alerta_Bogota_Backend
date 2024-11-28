package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuarios database table.
 * 
 */
@Entity
@Table(name="usuarios")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="usuario_id")
	private int usuarioId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="primer_apellido")
	private String primerApellido;

	@Column(name="primer_nombre")
	private String primerNombre;

	@Column(name="rol_id")
	private int rolId;

	@Column(name="segundo_apellido")
	private String segundoApellido;

	@Column(name="segundo_nombre")
	private String segundoNombre;

	@Column(name="use_email")
	private String useEmail;

	@Column(name="use_pass")
	private String usePass;

	//bi-directional many-to-one association to AuditoriaIncidencia
	@OneToMany(mappedBy="usuario")
	private List<AuditoriaIncidencia> auditoriaIncidencias;

	//bi-directional many-to-one association to Incidencia
	@OneToMany(mappedBy="usuario")
	private List<Incidencia> incidencias;

	public Usuario() {
	}

	public int getUsuarioId() {
		return this.usuarioId;
	}

	public void setUsuarioId(int usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getPrimerApellido() {
		return this.primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getPrimerNombre() {
		return this.primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public int getRolId() {
		return this.rolId;
	}

	public void setRolId(int rolId) {
		this.rolId = rolId;
	}

	public String getSegundoApellido() {
		return this.segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getSegundoNombre() {
		return this.segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getUseEmail() {
		return this.useEmail;
	}

	public void setUseEmail(String useEmail) {
		this.useEmail = useEmail;
	}

	public String getUsePass() {
		return this.usePass;
	}

	public void setUsePass(String usePass) {
		this.usePass = usePass;
	}

	public List<AuditoriaIncidencia> getAuditoriaIncidencias() {
		return this.auditoriaIncidencias;
	}

	public void setAuditoriaIncidencias(List<AuditoriaIncidencia> auditoriaIncidencias) {
		this.auditoriaIncidencias = auditoriaIncidencias;
	}

	public AuditoriaIncidencia addAuditoriaIncidencia(AuditoriaIncidencia auditoriaIncidencia) {
		getAuditoriaIncidencias().add(auditoriaIncidencia);
		auditoriaIncidencia.setUsuario(this);

		return auditoriaIncidencia;
	}

	public AuditoriaIncidencia removeAuditoriaIncidencia(AuditoriaIncidencia auditoriaIncidencia) {
		getAuditoriaIncidencias().remove(auditoriaIncidencia);
		auditoriaIncidencia.setUsuario(null);

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
		incidencia.setUsuario(this);

		return incidencia;
	}

	public Incidencia removeIncidencia(Incidencia incidencia) {
		getIncidencias().remove(incidencia);
		incidencia.setUsuario(null);

		return incidencia;
	}

}