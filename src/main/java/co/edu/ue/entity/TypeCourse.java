package co.edu.ue.entity;

import java.io.Serializable;
import jakarta.persistence.*;
//import java.util.List;


/**
 * The persistent class for the type_course database table.
 * 
 */
@Entity
@Table(name="type_course")
@NamedQuery(name="TypeCourse.findAll", query="SELECT t FROM TypeCourse t")
public class TypeCourse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="type_id")
	private int typeId;

	@Column(name="type_description")
	private String typeDescription;

	public TypeCourse() {
	}

	public int getTypeId() {
		return this.typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeDescription() {
		return this.typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}
}