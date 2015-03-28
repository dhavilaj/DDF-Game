package com.ddf.vaadin.client;

import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * 
 * Animated element
 * 
 * @author David Mantilla
 *
 */
public interface AnimatedElement {

	/**
	 * Go to the next frame of the animation
	 */
	public void goNext();

	/**
	 * Restart the animation
	 */
	public void restart();

	/**
	 * Return the X position
	 * 
	 * @return
	 */
	public int getX();

	/**
	 * Return the Y position
	 * 
	 * @return
	 */
	public int getY();

	/**
	 * Return the current frame for the current step
	 * 
	 * @return
	 */
	public Frame getCurrentFrame();

	/**
	 * Draw this element and all its children
	 * 
	 * @param rootPanel
	 */
	public void draw(RootPanel rootPanel, Context2d context2d);

	/**
	 * Return the children list
	 * 
	 * @param animatedElement
	 */
	public List<AnimatedElement> getChildren();

	/**
	 * Return if the animated element is visible or not
	 * 
	 * @return
	 */
	public boolean isVisible();

	/**
	 * Setter for X
	 * @param i
	 */
	public void setX(int i);
	
	/**
	 * Setter for Y
	 * @param i
	 */
	public void setY(int i);


}
