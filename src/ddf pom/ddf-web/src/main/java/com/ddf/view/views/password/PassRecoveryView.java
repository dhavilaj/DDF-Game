package com.ddf.view.views.password;

import com.ddf.view.base.DDFView;

/**
 * Password recovery view interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PassRecoveryView extends DDFView<PassRecoveryViewListener> {

	@SuppressWarnings("javadoc")
	public enum ViewField {
		PASSWORD,
		PASSWORD_CONFIRM
	}

}
