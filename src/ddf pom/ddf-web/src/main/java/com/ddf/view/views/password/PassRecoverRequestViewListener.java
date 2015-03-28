package com.ddf.view.views.password;

import com.ddf.view.base.DDFViewListener;

/**
 * LoginView Listener interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PassRecoverRequestViewListener extends DDFViewListener {

	/**
	 * Process the event of the login click
	 * 
	 * @param email Entered email
	 */
	public void processSubmit(String email);

}
