package com.ddf.commons.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom validation for empty validation
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class IpValidator implements ConstraintValidator<Ip, String> {

	private boolean wildCard;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.validation.ConstraintValidator#initialize(java.lang.annotation.
	 * Annotation)
	 */
	@Override
	public void initialize(Ip arg0) {
		this.wildCard = arg0.wildCard();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
	 * javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value,
		ConstraintValidatorContext validatorConstant) {
		if (value == null) {
			return true;
		}
		if (value.isEmpty()) {
			return true;
		}
		if ( value.startsWith(".")){
			return false;
		}
		if ( value.endsWith(".")){
			return false;
		}

		String[] ipParts = value.split("\\.");
		if (ipParts.length != 4) {
			return false;
		}

		String regExp = wildCard ? "^[0-9\\*]{1,3}$" : "^[0-9]{1,3}$";
		for (String part : ipParts) {
			if (!part.matches(regExp)) {
				return false;
			}
			int n = new Integer(part.replaceAll("[\\*]+", "2"));
			if (n > 255) {
				return false;
			}
		}

		return true;
	}

}
