package com.ddf.commons.validation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ddf.commons.i18n.ValidationMessages;

/**
 * This class is just a holder for standard properties to make services able to
 * validate a particular property using javax.validations annotations
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class StandardPropertyValidator {
	/* static fields */

	/* instance variables */
	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
		+ ValidationMessages.EMAIL_PATTERN + "}")
	private String email;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 255)
	@Pattern(regexp = ValidationPatterns.URL_PATTERN, message = "{"
		+ ValidationMessages.URL_PATTERN + "}")
	private String url;
	
	
	@NotNull
	@Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = "{"
		+ ValidationMessages.PASSWORD_PATTERN + "}")
	private String password;

	/* constructors */

	/* Methods */

	/* Getters & Setters */
	
	/**
	 * Getter for email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for url
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for url
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for password
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
