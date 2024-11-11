package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the courses database table.
 * 
 */
@Entity
@Table(name="courses")
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="cou_id")
	private int couId;

	@Column(name="cou_description")
	private String couDescription;

	@Column(name="cou_name")
	private String couName;

	@Column(name="cou_price")
	private BigDecimal couPrice;
	
	@Column(name="cou_status")
	private String couStatus;

	//bi-directional many-to-one association to TypeCourse
	@ManyToOne
	@JoinColumn(name="type_id")
	private TypeCourse typeCourse;

	public Course() {
	}

	public int getCouId() {
		return this.couId;
	}

	public void setCouId(int couId) {
		this.couId = couId;
	}

	public String getCouDescription() {
		return this.couDescription;
	}

	public void setCouDescription(String couDescription) {
		this.couDescription = couDescription;
	}

	public String getCouName() {
		return this.couName;
	}

	public void setCouName(String couName) {
		this.couName = couName;
	}

	public BigDecimal getCouPrice() {
		return this.couPrice;
	}

	public void setCouPrice(BigDecimal couPrice) {
		this.couPrice = couPrice;
	}

	public TypeCourse getTypeCourse() {
		return this.typeCourse;
	}

	public void setTypeCourse(TypeCourse typeCourse) {
		this.typeCourse = typeCourse;
	}
	
	public String getCouStatus() {
		return couStatus;
	}

	public void setCouStatus(String couStatus) {
		this.couStatus = couStatus;
	}
}