package com.ddf.view.converters;

import java.util.Locale;

/**
 * Interface for converters between view and model
 * 
 * @author David Mantilla
 * @param <T>
 * @since 1.7
 */
public interface SocialViewConverter<T> {

	/**
	 * The implementation of this method should convert the presentation string
	 * to a model object of the type <U>
	 * 
	 * @param value string value to convert to object
	 * @param locale current presentation locale
	 * @return the converted value
	 */
	public T getAsObject(String value, Locale locale);

	/**
	 * The implementation of this method should convert the model objet to a
	 * presentation string
	 * 
	 * @param value
	 * @param locale
	 * @return the converted value in String
	 */
	public String getAsString(Object value, Locale locale);

	/**
	 * The implementation of this method should return the class of the model
	 * value
	 * 
	 * @return the class of the model value
	 */
	public Class<T> getModelType();
}
