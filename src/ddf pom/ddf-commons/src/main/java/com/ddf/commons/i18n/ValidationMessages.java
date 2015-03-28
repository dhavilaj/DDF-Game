package com.ddf.commons.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Messages handler for presentation layer
 * 
 * @author David Mantilla
 * @since 1.7
 */
@SuppressWarnings("javadoc")
public class ValidationMessages {

	private static final String BUNDLE_NAME = "ValidationMessages"; //$NON-NLS-1$
	private static final Logger LOG = Logger.getLogger(ValidationMessages.class
		.getName());
	private static final Map<Locale, ResourceBundle> resourceBundleMap;

	public static final String ASSERT_FALSE = "javax.validation.constraints.AssertFalse.message";
	public static final String ASSERT_TRUE = "javax.validation.constraints.AssertTrue.message";
	public static final String DECIMAL_MAX = "javax.validation.constraints.DecimalMax.message";
	public static final String DECIMAL_MIN = "javax.validation.constraints.DecimalMin.message";
	public static final String DIGITS = "javax.validation.constraints.Digits.message";
	public static final String FUTURE = "javax.validation.constraints.Future.message";
	public static final String MAX = "javax.validation.constraints.Max.message";
	public static final String MIN = "javax.validation.constraints.Min.message";
	public static final String NOT_NULL = "javax.validation.constraints.NotNull.message";
	public static final String NULL = "javax.validation.constraints.Null.message";
	public static final String PAST = "javax.validation.constraints.Past.message";
	public static final String PATTERN = "javax.validation.constraints.Pattern.message";
	public static final String SIZE = "javax.validation.constraints.Size.message";
	public static final String EMAIL_PATTERN = "com.ddf.validator.constrains.EmailPattern.message";
	public static final String NOT_EMPTY = "com.ddf.validator.constrains.NotEmpty.message";
	public static final String IP = "com.ddf.validator.constrains.Ip.message";
	public static final String URL_PATTERN = "com.ddf.validator.constrains.UrlPattern.message";
	public static final String PASSWORD_PATTERN = "com.ddf.validator.constrains.PasswordPattern.message";
	public static final String GENERAL_NAME_PATTERN = "com.ddf.validator.constrains.PasswordPattern.message";

	static {
		resourceBundleMap = new HashMap<Locale, ResourceBundle>();
	}

	/**
	 * Return a string of the resource bundle given its key and the locale
	 * 
	 * @param key
	 * @param locale
	 * @return founded string
	 */
	public static String getString(String key, Locale locale) {
		try {
			return getResourceBundle(locale).getString(key);
		} catch (MissingResourceException e) {
			LOG.log(Level.WARNING,
				"Message not found in ValidationMessages bundle: " + key, e);
			return "???" + key + "???";
		}
	}

	/**
	 * Get resource bundle given a locale. If locale is not found English one is
	 * given by default
	 * 
	 * @param locale
	 * @return
	 */
	private static ResourceBundle getResourceBundle(Locale locale) {
		ResourceBundle resourceBundle = resourceBundleMap.get(locale);
		if (resourceBundle == null) {
			return loadResourceBundle(locale);
		}
		return resourceBundle;
	}

	/**
	 * Load a resource bundle by its locale and put it into the map
	 * 
	 * @param locale
	 * @return
	 */
	synchronized private static ResourceBundle loadResourceBundle(Locale locale) {
		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, locale);
		} catch (MissingResourceException e) {
			LOG.log(Level.WARNING, "Language not supported", e);
			resourceBundle = ResourceBundle.getBundle(BUNDLE_NAME, new Locale(
				"en"));
		}
		resourceBundleMap.put(locale, resourceBundle);
		return resourceBundle;
	}
}
