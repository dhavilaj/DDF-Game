package com.ddf.view.components.breadcrumb;

import com.ddf.view.base.DDFViewListener;

/**
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface BreadCrumbListener extends DDFViewListener{

	/* Methods */

	/**
	 * @param crumbClickEvent
	 * @return true if is allowed to continue with navigation
	 */
	public boolean processBeforeBreadCrumbNavigate(
			BreadCrumbClickEvent crumbClickEvent);

	/* Getters & Setters */
}
