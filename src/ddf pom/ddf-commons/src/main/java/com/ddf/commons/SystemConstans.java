package com.ddf.commons;

/**
 * All constants of the system, like all configuration stuff
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface SystemConstans {
	/* static fields */

	/**
	 * This is the path for the physical files of the file database table
	 */
	public static final String FILES_PATH = "/Users/davidmantilla/Desktop/mobiera/bases de datos/social/files/";
	/**
	 * The JNDI name for the mail service of the Java EE Container
	 */
	public static final String MAIL_SERVICE_JNDI = "java:jboss/mail/DDF";

	/**
	 * The JNDI name for the XADISK resource registered in the Java EE Container
	 */
	public static final String XADISK_SERVICE_JNDI = "java:jboss/global/XADiskCF";

	/**
	 * The context of the file server application
	 */
	public static final String FILES_SERVER_CONTEXT = "/DDFiles";

	/**
	 * The context of the file server application
	 */
	public static final String APP_SERVER_CONTEXT = "/ddf-web";

	/**
	 * The server base URL
	 */
	public static final String SERVER_BASE_URL = "http://localhost:10080";

	/**
	 * Default ammount of records to show in the paginated tables
	 */
	public static final int ROWS_PER_TABLE_PAGE = 2;

	/* Methods */

	/* Getters & Setters */
}
