package com.ddf.model.session;

import java.util.Locale;

import com.ddf.commons.vo.CredentialVO;
import com.ddf.commons.vo.UserVO;
import com.ddf.model.controlaccess.DDFViewEnum;

/**
 * This interface is the abstraction of the class which represents the session
 * of each user connected to the social Application
 * 
 * @author David Mantilla
 *
 */
public interface SocialSession {

	/**
	 * Login the user into the session
	 * 
	 * @param credentialVO
	 * @throws LoginFailedException
	 *             When the login process failed
	 */
	public abstract void login(CredentialVO credentialVO)
			throws LoginFailedException;

	/**
	 * Logout from Social
	 */
	public abstract void logout();

	/**
	 * Get current account
	 * 
	 * @return the current account
	 */
	public abstract UserVO getCurrentAccount();

	/**
	 * Getter for logged flag
	 * 
	 * @return true if the user is logged, false otherwise
	 */
	public abstract boolean isLogged();

	/**
	 * Returns the default session, normally this means login view if the user
	 * is not logged, home if is already logged
	 * 
	 * @return the default view
	 */
	public abstract DDFViewEnum getDefaultView();

	/**
	 * @return the session locale
	 */
	public abstract Locale getLocale();

}