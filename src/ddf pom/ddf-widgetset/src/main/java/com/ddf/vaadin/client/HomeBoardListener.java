package com.ddf.vaadin.client;

import com.vaadin.shared.communication.ServerRpc;

/**
 * Listener for the HomeBoardListener
 * 
 * @author David Mantilla
 *
 */
public interface HomeBoardListener extends ServerRpc {
	public enum Section {
		BURN_DOWN;
	}

	public void sectionClick(Section section);
}
