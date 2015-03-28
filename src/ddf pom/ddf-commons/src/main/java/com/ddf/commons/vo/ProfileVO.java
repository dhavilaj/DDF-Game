package com.ddf.commons.vo;

import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * Profile Value Object
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ProfileVO implements ValueObject {

	private Long id;
	private String description;
	private String profileName;

	/**
	 * Default Constructor
	 */
	public ProfileVO() {
	}

	public ProfileVO(Long id, String description, String profileName) {
		super();
		this.id = id;
		this.description = description;
		this.profileName = profileName;
	}

	/**
	 * Id constructor
	 * 
	 * @param id
	 */
	public ProfileVO(Long id) {
		super();
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName
	 *            the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

}
