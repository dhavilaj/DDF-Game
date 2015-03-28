package com.ddf.commons.vo;

import java.util.List;

/**
 * Value Object for Authentication information
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class AuthInfoVO {

	/* static fields */
	private UserVO user;

	/* instance variables */

	/* constructors */
	/**
	 * Default Constructor
	 */
	public AuthInfoVO() {

	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Getter for account
	 * 
	 * @return the account
	 */
	public UserVO getUser() {
		return user;
	}

	/**
	 * Setter for account
	 * 
	 * @param account
	 *            the account to set
	 */
	public void setUser(UserVO account) {
		this.user = account;
	}

	/**
	 * Constructor
	 * 
	 * @param apps
	 */
	public AuthInfoVO(List<AppVO> apps) {
		super();
	}

}
