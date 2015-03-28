/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ddf.vaadin.client;

import com.vaadin.shared.AbstractComponentState;

/**
 *
 * @author David Mantilla
 */
public class HomeBoardCanvasState extends AbstractComponentState {

	/* static variables */
	private static final long serialVersionUID = 3087133904987814259L;

	/* instance variables */
	// public ScrumMasterStatus scrumMasterStatus;
	public int phoneNotificationsCount;
	public int emailNotificationsCount;
	public int canvanNotificationsCount;
	public int burnDownNotificationsCount;
	public int calendarNotificationsCount;
	public int papersNotificationsCount;
	public int agilityBarPosition;
	public int healthBarPosition;
	public int proyectBarPosition;

	/* constructor */

	/**
	 * Default constructor
	 */
	public HomeBoardCanvasState() {

	}

}
