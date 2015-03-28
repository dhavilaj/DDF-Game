/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ddf.vaadin.client;

import com.ddf.vaadin.widgetset.homeboard.HomeBoardCanvas;
import com.google.gwt.core.client.GWT;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

/**
 * Connector for the HomeBardCanvas
 * 
 * @author David Mantilla
 */
@Connect(value = HomeBoardCanvas.class, loadStyle = Connect.LoadStyle.EAGER)
public class HomeBoardCanvasConnector extends AbstractComponentConnector {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 */
	public HomeBoardCanvasConnector() {
//		getWidget().getHomeBoardListeners().add(new HomeBoardListener() {
//
//			@Override
//			public void sectionClick(Section section) {
//				HomeBoardListener serverRPC = RpcProxy.create(
//						HomeBoardListener.class, HomeBoardCanvasConnector.this);
//				serverRPC.sectionClick(section);
//			}
//		});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.client.ui.AbstractComponentConnector#onStateChanged(com.vaadin
	 * .client.communication.StateChangeEvent)
	 */
	@Override
	public void onStateChanged(StateChangeEvent stateChangeEvent) {
		// super call
		super.onStateChanged(stateChangeEvent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.client.ui.AbstractComponentConnector#getWidget()
	 */
	@Override
	public HomeBoardCanvasWidget getWidget() {
		return (HomeBoardCanvasWidget) super.getWidget();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.client.ui.AbstractComponentConnector#getState()
	 */
	@Override
	public HomeBoardCanvasState getState() {
		return (HomeBoardCanvasState) super.getState();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.client.ui.AbstractComponentConnector#createWidget()
	 */
	@Override
	protected HomeBoardCanvasWidget createWidget() {
		return GWT.create(HomeBoardCanvasWidget.class);
	}

}
