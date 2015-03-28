package com.ddf.view.views.login;

import com.ddf.view.base.DDFViewListener;

/**
 * LoginView Listener interface
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public interface LoginViewListener extends DDFViewListener {

	/**
	 * Process the event of the login click
	 * 
	 * @param email Entered email
	 * @param password Entered password
	 */
	public void processLoginSubmit(String email, String password);

	/**
	 * Process the event of the forgot password link click
	 */
	public void processForgotPasswordClick();

}
