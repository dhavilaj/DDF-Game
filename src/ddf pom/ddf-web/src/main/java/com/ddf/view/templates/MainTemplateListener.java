package com.ddf.view.templates;

import com.ddf.view.base.DDFViewListener;

/**
 * Main Template Listener interface
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface MainTemplateListener extends DDFViewListener {

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * Implementation of this methods should process the event of clicking an
	 * application in the header section
	 * 
	 * @param clickedApp
	 */
	public void processApplicationClick(MainTemplate.App clickedApp);

	/**
	 * @param clickedOperation
	 */
	public void processSuperviseOperationClick(
		MainTemplate.Operation clickedOperation);

	/**
	 * Process more (in supervision menu) click
	 */
	public void processMoreSupervisedOperationsClick();

	/**
	 * Process my account menu item click
	 */
	public void processMyAccountMenuItemClick();

	/**
	 * Process admin menu item click
	 */
	public void processAdminMenuItemClick();

	/**
	 * Process logout menu item click
	 */
	public void processLogoutMenuItemClick();

	/**
	 * Process logo click
	 */
	public void processLogoClick();

	/* Getters & Setters */
}
