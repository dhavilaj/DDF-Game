package com.ddf.view.views.login;

import com.ddf.view.base.DDFView;

/**
 * Login view interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface LoginView extends DDFView<LoginViewListener> {

	@SuppressWarnings("javadoc")
	public enum ViewField {
		EMAIL,
		PASSWORD;
	}

}
