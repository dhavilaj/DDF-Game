/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ddf.vaadin.widgetset.homeboard;

import com.ddf.vaadin.client.HomeBoardCanvasState;
import com.ddf.vaadin.client.HomeBoardListener;
import com.vaadin.ui.AbstractComponent;
import com.vaadin.ui.Notification;

/**
 *
 * @author David Mantilla
 */
@SuppressWarnings("serial")
public class HomeBoardCanvas extends AbstractComponent {

	private static final long serialVersionUID = -7131115850553458900L;

	/* instance attributes */
	private HomeBoardListener rpcClient = new HomeBoardListener() {
		@Override
		public void sectionClick(Section section) {
			Notification.show("Click");
		}
	};

	/**
	 * Constructor
	 */
	public HomeBoardCanvas() {
		registerRpc(rpcClient);
	}

	/* getters y setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.AbstractComponent#getState()
	 */
	@Override
	public HomeBoardCanvasState getState() {
		return (HomeBoardCanvasState) super.getState();
	}

	/**
	 * @return the scrumMasterStatus
//	 */
//	public ScrumMasterStatus getScrumMasterStatus() {
//		return getState().scrumMasterStatus;
//	}
//
//	/**
//	 * @param scrumMasterStatus
//	 *            the scrumMasterStatus to set
//	 */
//	public void setScrumMasterStatus(ScrumMasterStatus scrumMasterStatus) {
//		this.getState().scrumMasterStatus = scrumMasterStatus;
//	}

	/**
	 * @return the phoneNotificationsCount
	 */
	public int getPhoneNotificationsCount() {
		return getState().phoneNotificationsCount;
	}

	/**
	 * @param phoneNotificationsCount
	 *            the phoneNotificationsCount to set
	 */
	public void setPhoneNotificationsCount(int phoneNotificationsCount) {
		this.getState().phoneNotificationsCount = phoneNotificationsCount;
	}

	/**
	 * @return the emailNotificationsCount
	 */
	public int getEmailNotificationsCount() {
		return getState().emailNotificationsCount;
	}

	/**
	 * @param emailNotificationsCount
	 *            the emailNotificationsCount to set
	 */
	public void setEmailNotificationsCount(int emailNotificationsCount) {
		this.getState().emailNotificationsCount = emailNotificationsCount;
	}

	/**
	 * @return the canvanNotificationsCount
	 */
	public int getCanvanNotificationsCount() {
		return getState().canvanNotificationsCount;
	}

	/**
	 * @param canvanNotificationsCount
	 *            the canvanNotificationsCount to set
	 */
	public void setCanvanNotificationsCount(int canvanNotificationsCount) {
		this.getState().canvanNotificationsCount = canvanNotificationsCount;
	}

	/**
	 * @return the burnDownNotificationsCount
	 */
	public int getBurnDownNotificationsCount() {
		return getState().burnDownNotificationsCount;
	}

	/**
	 * @param burnDownNotificationsCount
	 *            the burnDownNotificationsCount to set
	 */
	public void setBurnDownNotificationsCount(int burnDownNotificationsCount) {
		this.getState().burnDownNotificationsCount = burnDownNotificationsCount;
	}

	/**
	 * @return the calendarNotificationsCount
	 */
	public int getCalendarNotificationsCount() {
		return getState().calendarNotificationsCount;
	}

	/**
	 * @param calendarNotificationsCount
	 *            the calendarNotificationsCount to set
	 */
	public void setCalendarNotificationsCount(int calendarNotificationsCount) {
		this.getState().calendarNotificationsCount = calendarNotificationsCount;
	}

	/**
	 * @return the papersNotificationsCount
	 */
	public int getPapersNotificationsCount() {
		return getState().papersNotificationsCount;
	}

	/**
	 * @param papersNotificationsCount
	 *            the papersNotificationsCount to set
	 */
	public void setPapersNotificationsCount(int papersNotificationsCount) {
		this.getState().papersNotificationsCount = papersNotificationsCount;
	}

	/**
	 * @return the agilityBarPosition
	 */
	public int getAgilityBarPosition() {
		return getState().agilityBarPosition;
	}

	/**
	 * @param agilityBarPosition
	 *            the agilityBarPosition to set
	 */
	public void setAgilityBarPosition(int agilityBarPosition) {
		this.getState().agilityBarPosition = agilityBarPosition;
	}

	/**
	 * @return the healthBarPosition
	 */
	public int getHealthBarPosition() {
		return getState().healthBarPosition;
	}

	/**
	 * @param healthBarPosition
	 *            the healthBarPosition to set
	 */
	public void setHealthBarPosition(int healthBarPosition) {
		this.getState().healthBarPosition = healthBarPosition;
	}

	/**
	 * @return the proyectBarPosition
	 */
	public int getProyectBarPosition() {
		return getState().proyectBarPosition;
	}

	/**
	 * @param proyectBarPosition
	 *            the proyectBarPosition to set
	 */
	public void setProyectBarPosition(int proyectBarPosition) {
		this.getState().proyectBarPosition = proyectBarPosition;
	}
}
