package com.ddf.view.components.breadcrumb;

import java.util.Map;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.base.DDFComponent;
import com.vaadin.ui.Component;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
public interface BreadCrumb extends DDFComponent<BreadCrumbListener>{

	/* Getters & Setters */

	/**
	 * @param label
	 * @param view
	 * @param params
	 * @param clickeable
	 */
	public abstract void addBreadCrumb(String label, DDFViewEnum view,
			Map<String, String> params, boolean clickeable);

	/**
	 * Add a custom breadcrumb
	 * 
	 * @param component
	 */
	public abstract void addBreadCrumb(Component component);


}