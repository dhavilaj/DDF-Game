package com.ddf.commons.vo;

import java.util.ArrayList;
import java.util.List;

import com.ddf.commons.vo.interfaces.ValueObject;

/**
 * Value Object with access information for a given application
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class AppAccessInfoVO implements ValueObject {

	/* static fields */

	/* instance variables */
	private List<UseCaseVO> useCases;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public AppAccessInfoVO() {
		useCases = new ArrayList<>();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for useCases
	 * 
	 * @return the useCases
	 */
	public List<UseCaseVO> getUseCases() {
		return useCases;
	}

	/**
	 * Setter for useCases
	 * 
	 * @param useCases
	 *            the useCases to set
	 */
	public void setUseCases(List<UseCaseVO> useCases) {
		this.useCases = useCases;
	}

}
