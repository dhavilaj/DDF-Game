package com.ddf.view.templates;

import com.ddf.view.base.DDFTemplate;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;

/**
 * Template for all public views
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PublicTemplate extends CssLayout implements DDFTemplate {

	/* static fields */

	private static final long serialVersionUID = 1L;
	/* instance variables */
	private CssLayout cssLayout;

	/* constructors */

	/**
	 * Default constructor
	 */
	public PublicTemplate() {
		setSizeFull();

		Label label = new Label("Public Template");
		this.addComponent(label);
		this.setStyleName("page-wrapper");

		cssLayout = new CssLayout();
		cssLayout.setSizeFull();
		this.addComponent(cssLayout);
	}

	/* Methods */

	/* Getters & Setters */
	@Override
	public ComponentContainer getNavigationSection() {
		return cssLayout;
	}
}
