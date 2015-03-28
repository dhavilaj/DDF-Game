package com.ddf.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;

/**
 * The persistent class for the profile database table.
 * 
 */
@Entity
@NamedQuery(name = "Profile.findAll", query = "SELECT p FROM Profile p")
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "profile_name")
	private String profileName;

	@ManyToMany
	@JoinTable(name = "profile_use_case", joinColumns = { @JoinColumn(name = "profile_id") }, inverseJoinColumns = { @JoinColumn(name = "use_case_id") })
	private List<UseCase> useCases;

	public Profile() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public List<UseCase> getUseCases() {
		return this.useCases;
	}

	public void setUseCases(List<UseCase> useCases) {
		this.useCases = useCases;
	}

}