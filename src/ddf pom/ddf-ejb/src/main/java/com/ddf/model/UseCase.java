package com.ddf.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.ddf.commons.vo.interfaces.DDFEntity;

/**
 * The persistent class for the use_case database table.
 * 
 */
@Entity
@Table(name = "use_case")
@NamedQueries({
		@NamedQuery(name = "UseCase.findAll", query = "SELECT u FROM UseCase u"),
		@NamedQuery(name = "UseCase.findByUser", query = "SELECT u FROM User r, IN(r.profiles) p, IN( p.useCases ) u WHERE r.id = ?1") })
public class UseCase implements Serializable, DDFEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "use_case_name")
	private String useCaseName;

	public UseCase() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUseCaseName() {
		return this.useCaseName;
	}

	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
	}

}