package com.ddf.view.views.password;

import com.ddf.view.base.DDFViewListener;

/**
 * LoginView Listener interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PassRecoveryViewListener extends DDFViewListener {

	/**
	 * Process the event of the login click
	 * 
	 * @param password
	 * @param passwordConfirm
	 */
	public void processSubmit(String password, String passwordConfirm);

}
