package com.ddf.helpers;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;

import com.ddf.commons.i18n.SocialMessageInterpolator;
import com.ddf.commons.test.TestUtils;

@SuppressWarnings("javadoc")
public class TestBeanValidator {
	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */
	public static <T> void testPropertyValidation(T bean, String propertyPath,
			String value, boolean assertInFail) {
		Locale locale = new Locale("en");

		Configuration<?> configuration = Validation.byDefaultProvider()
				.configure();
		ValidatorFactory factory = configuration.messageInterpolator(
				new SocialMessageInterpolator(configuration
						.getDefaultMessageInterpolator(), locale))
				.buildValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> errors = validator.validate(bean,
				new Class[] {});

		Assert.assertTrue(errors.isEmpty() ^ assertInFail);

		for (ConstraintViolation<T> error : errors) {
			System.out.println(error.getMessage());
		}

		if (assertInFail) {
			for (ConstraintViolation<T> error : errors) {
				Assert.assertTrue(value, error.getPropertyPath().toString()
						.equals(propertyPath));
				if (error.getInvalidValue() != null) {
					Assert.assertTrue(value, error.getInvalidValue().equals(value));
				} else {
					Assert.assertTrue(value == null);
				}
			}
		}
	}

	public static <T> void testEmailValidation(T bean, String propertyPath,
			boolean required) {
		try {
			// empty
			String email;
			if (required) {
				email = "";
				BeanUtils.setProperty(bean, propertyPath, email);
				TestBeanValidator.testPropertyValidation(bean, "email", email,
						true);

				email = null;
				BeanUtils.setProperty(bean, propertyPath, email);
				TestBeanValidator.testPropertyValidation(bean, "email", email,
						true);
			}

			// invalid e-mails
			for (String invalidEmail : TestUtils.getInvalidSizeEmails()) {
				BeanUtils.setProperty(bean, propertyPath, invalidEmail);
				TestBeanValidator.testPropertyValidation(bean, "email",
						invalidEmail, true);
			}

			// valid e-mails
			for (String validEmail : TestUtils.getValidEmails()) {
				BeanUtils.setProperty(bean, propertyPath, validEmail);
				TestBeanValidator.testPropertyValidation(bean, "email",
						validEmail, false);
			}

		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
	/* Getters & Setters */

}
