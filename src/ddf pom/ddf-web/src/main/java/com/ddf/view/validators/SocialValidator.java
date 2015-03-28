package com.ddf.view.validators;

import com.vaadin.data.Validator;

/**
 * This is the interface for all social validators given from presenter layer
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface SocialValidator extends Validator {

	/* Inner Types */
	/**
	 * @author David Mantilla
	 *
	 * @since 1.7
	 */
	public class SocialValidatorException extends
			Validator.InvalidValueException {

		private static final long serialVersionUID = 827562239818428394L;

		/**
		 * Constructor
		 * 
		 * @param message
		 * @param causes
		 */
		public SocialValidatorException(String message,
				InvalidValueException... causes) {
			super(message, causes);
		}

		/**
		 * Constructor
		 * 
		 * @param message
		 */
		public SocialValidatorException(String message) {
			super(message);
		}

	}

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Validator#validate(java.lang.Object)
	 */
	@Override
	public void validate(Object value)
			throws SocialValidator.SocialValidatorException;

	/* Getters & Setters */
}
