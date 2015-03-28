package com.ddf.services.local;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.ejb.Local;

import com.ddf.commons.exceptions.services.MailServicesErrorException;

/**
 * Interface for all email services
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Local
public interface MailServicesLocal {

	/**
	 * Key of the password recovery email content in the properties resource
	 * file
	 */
	public static final String PASSWORD_RECOVERY_KEY = "com.ddf.mail.templates.PasswordRecovery";

	/**
	 * 
	 * @author David Mantilla
	 *
	 * @since 1.7
	 */
	public interface MailDefinition {
		/**
		 * Implementation of this method must return the subject in the given
		 * language
		 * 
		 * @param locale
		 * @return the subject in the given language
		 */
		public String getSubjectTemplate(Locale locale);

		/**
		 * Implementation of this method must return the body in the given
		 * language
		 * 
		 * @param locale
		 * @return the body in the given language
		 */
		public String getBodyTemplate(Locale locale);
	}

	/**
	 * Enumeration of all e-mails types
	 */
	public enum BuildInMailType implements MailDefinition {
		/**
		 * This is the mail which is sent when the user asks for password
		 * recovery
		 */
		PASSWORD_RECOVERY(PASSWORD_RECOVERY_KEY);
		private final String propertyBase;

		private BuildInMailType(String propertyBase) {
			this.propertyBase = propertyBase;
		}

		@Override
		public String getSubjectTemplate(Locale locale) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(
				BUNDLE_NAME, locale);
			String subject = resourceBundle.getString(propertyBase.concat(".")
				.concat(SUBJECT_PROPERTY_SUFIX));
			return subject;
		}

		@Override
		public String getBodyTemplate(Locale locale) {
			ResourceBundle resourceBundle = ResourceBundle.getBundle(
				BUNDLE_NAME, locale);
			String body = resourceBundle.getString(propertyBase.concat(".")
				.concat(BODY_PROPERTY_SUFIX));
			return body;
		}

	}

	/**
	 * Bundle template for mails
	 */
	public static final String BUNDLE_NAME = "mail_templates";
	/**
	 * Suffix for finding body value in the bundle
	 */
	public static final String BODY_PROPERTY_SUFIX = "Body";
	/**
	 * Suffix for finding subject value in the bundle
	 */
	public static final String SUBJECT_PROPERTY_SUFIX = "Subject";

	/**
	 * Sends a mail
	 * 
	 * @param mailDefinition
	 * @param locale
	 * @param destEmails
	 * @param parameters
	 * @throws MailServicesErrorException when some error occurs sending email
	 */
	public void sendMail(MailDefinition mailDefinition, Locale locale,
		List<String> destEmails, Map<String, String> parameters)
		throws MailServicesErrorException;

	/**
	 * Sends a filled message
	 * 
	 * @param body
	 * @param subject
	 * @param addresses Comma separated email list
	 * @throws MailServicesErrorException
	 */
	public abstract void sendFilledMessage(String body, String subject,
		String addresses) throws MailServicesErrorException;

}