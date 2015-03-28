package com.ddf.commons.validation;

import java.util.Locale;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import com.ddf.commons.i18n.SocialMessageInterpolator;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class BeanValidator {

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * @param <T> Class of the bean to validate
	 * @param bean Bean to validate
	 * @param locale locale to use in message interpolation
	 * @param groups Group or groups to validate
	 * @return the set of errors due to violated constrains
	 */
	public static final <T> Set<ConstraintViolation<T>> validateBean(T bean,
		Locale locale, Class<?>... groups) {
		Configuration<?> configuration = Validation.byDefaultProvider()
			.configure();
		ValidatorFactory factory = configuration.messageInterpolator(
			new SocialMessageInterpolator(configuration
				.getDefaultMessageInterpolator(), locale))
			.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> errors = validator.validate(bean, groups);

		return errors;
	}

	/**
	 * @param <T> Class of the bean to validate property in
	 * @param bean Bean to validate
	 * @param propertyName Name of the property to validate
	 * @param locale locale to use in message interpolation
	 * @param groups Group or groups to validate
	 * @return the set of errors due to violated constrains
	 */
	public static final <T> Set<ConstraintViolation<T>> validateProperty(
		T bean, String propertyName, Locale locale, Class<?>... groups) {
		Configuration<?> configuration = Validation.byDefaultProvider()
			.configure();
		ValidatorFactory factory = configuration.messageInterpolator(
			new SocialMessageInterpolator(configuration
				.getDefaultMessageInterpolator(), locale))
			.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> errors = validator.validateProperty(bean,
			propertyName, groups);

		return errors;
	}

	/* Getters & Setters */
}
