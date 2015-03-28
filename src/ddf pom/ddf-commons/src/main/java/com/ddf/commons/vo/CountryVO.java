package com.ddf.commons.vo;

import java.io.Serializable;

import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * The persistent class for the country database table.
 * 
 */
public class CountryVO implements Serializable, ValueObject {

	private static final long serialVersionUID = 1L;
	/**
	 * Name of the id property
	 */
	public static final String id_ = "id";
	/**
	 * Name of the country name property
	 */
	public static final String countryName_ = "countryName";

	private Long id;
	private String countryName;
	private String defaultLanguage;

	/**
	 * Constructor
	 */
	public CountryVO() {
	}

	/**
	 * Getter for id
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter for id
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter for countryName
	 * 
	 * @return the countryName
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Setter for countryName
	 * 
	 * @param countryName the countryName to set
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Getter for defaultLanguage
	 * 
	 * @return the defaultLanguage
	 */
	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	/**
	 * Setter for defaultLanguage
	 * 
	 * @param defaultLanguage the defaultLanguage to set
	 */
	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CountryVO other = (CountryVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}