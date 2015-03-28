package com.ddf.view.base;

import com.vaadin.ui.ComponentContainer;

/**
 * This is the interface for all templates in the social application
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public interface DDFTemplate {


	/**
	 * The implementation of this method should return the section where the
	 * 
	 * @return the section where the views are going to navigate
	 */
	public ComponentContainer getNavigationSection();

}
