package com.ddf.view.base;

/**
 * Converter to put a list of any object into a combo-box
 * 
 * @author David Mantilla
 * @param <T> Class of the object to convert to combo box item
 * @since 1.7
 */
public interface ComboBoxItemConverter<T> {

	/* static fields */

	/* Methods */

	/**
	 * Get the id that represents the object
	 * 
	 * @param object
	 * @return the id
	 */
	public Object getIdFrom(T object);

	/**
	 * Gets the label that should represents the object in the combo box list
	 * 
	 * @param object
	 * @return the label
	 */
	public String getLabelFrom(T object);

	/* Getters & Setters */
}
