package com.ddf.commons.vo;

import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * UseCase value object class
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class UseCaseVO implements ValueObject {

	private Long id;
	private String description;
	private String useCaseName;

	/**
	 * Constructor
	 */
	public UseCaseVO() {
	}

	/**
	 * @param id
	 */
	public UseCaseVO(Long id) {
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
	 * @param id the id to set
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the useCaseName
	 */
	public String getUseCaseName() {
		return useCaseName;
	}

	/**
	 * @param useCaseName the useCaseName to set
	 */
	public void setUseCaseName(String useCaseName) {
		this.useCaseName = useCaseName;
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
		UseCaseVO other = (UseCaseVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
