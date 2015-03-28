package com.ddf.commons.vo.criteria;

/**
 * Partner query criteria
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PartnerQueryCriteria extends QueryCriteria{

	/* static fields */

	/* instance variables */
	@QueryFilterField(field = "id", operation = FilterOperation.EQUALS)
	private Long idEquals;

	/* constructors */

	/**
	 * Constructor
	 */
	public PartnerQueryCriteria() {
	}

	/* Methods */

	/* Getters & Setters */
	/**
	 * Getter for idEquals
	 * 
	 * @return the idEquals
	 */
	public Long getIdEquals() {
		return idEquals;
	}

	/**
	 * Setter for idEquals
	 * 
	 * @param idEquals the idEquals to set
	 */
	public void setIdEquals(Long idEquals) {
		this.idEquals = idEquals;
	}
}
