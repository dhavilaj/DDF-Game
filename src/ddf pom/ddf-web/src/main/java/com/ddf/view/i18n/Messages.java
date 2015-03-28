package com.ddf.view.i18n;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.ddf.model.session.SocialSessionImpl;

/**
 * Messages handler for presentation layer
 * 
 * @author David Mantilla
 * @since 1.7
 */
@SuppressWarnings("javadoc")
public class Messages {

	private static final Logger LOG = Logger
		.getLogger(Messages.class.getName());
	private static final Map<Locale, ResourceBundle> resourceBundleMap;

	private static final String BUNDLE_NAME = "messages"; //$NON-NLS-1$
	public static final String LOGIN_INVALID_EMAIL_MESSAGE = "SocialSession.Login.InvalidEmail.Message";
	public static final String LOGIN_INVALID_PASSWORD_MESSAGE = "SocialSession.Login.InvalidPassword.Message";
	public static final String LOGIN_INVALID_IP_MESSAGE = "SocialSession.Login.InvalidIP.Message";
	public static final String LOGIN_INACTIVE_USER_MESSAGE = "SocialSession.Login.InactiveUser.Message";
	public static final String UNEXPECTED_ERROR = "Common.UnexpectedError.Message";
	public static final String PASSWORD_NOT_MATCH = "PassRecoveryPresenter.PasswordNotMatch.Message";
	public static final String PASSWORD_CHANGED_SUCCESS = "PassRecoveryPresenter.PasswordChangedSuccess.Message";
	public static final String USER_ACTIVATION_SUCCESS = "UserListPresenter.UserActivationSuccess.Message";
	public static final String USER_INACTIVATION_SUCCESS = "UserListPresenter.UserInActivationSuccess.Message";
	
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
			return '!' + key + '!';
		}
	}

	/**
	 * Return a string of the resource bundle given its key and taken session
	 * current locale
	 * 
	 * @param key
	 * @return founded string
	 */
	public static String getString(String key) {
		try {
			return getResourceBundle(SocialSessionImpl.getCurrent().getLocale())
				.getString(key);
		} catch (MissingResourceException e) {
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
