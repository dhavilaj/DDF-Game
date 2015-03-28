package com.ddf.view.views.password;

import com.ddf.view.base.DDFView;

/**
 * Login view interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PassRecoverRequestView extends DDFView<PassRecoverRequestViewListener> {

	@SuppressWarnings("javadoc")
	public enum ViewField {
		EMAIL
	}

}
