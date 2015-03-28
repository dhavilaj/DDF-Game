package com.ddf.view.converters;

import java.util.Locale;

import com.vaadin.data.util.converter.Converter;

/**
 * This converter works for String to String, that means almost all the times it
 * doesn't do anything except in those cases where the string is null. In the
 * case of null an empty string is shown in the presentation and vice-versa,
 * when an empty string is entered in presentation, a null value is returned to
 * model
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class StringConverter implements Converter<String, String> {

	/* static fields */
	private static final long serialVersionUID = 8199481354822476125L;

	/* instance variables */

	/* constructors */

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 * java.lang.Class, java.util.Locale)
	 */
	@Override
	public String convertToModel(String value,
			Class<? extends String> targetType, Locale locale)
			throws Converter.ConversionException {
		if (value == null) {
			return null;
		}
		if (value.equals("")) {
			return null;
		}
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToPresentation(java.lang
	 * .Object, java.lang.Class, java.util.Locale)
	 */
	@Override
	public String convertToPresentation(String value,
			Class<? extends String> targetType, Locale locale)
			throws Converter.ConversionException {
		if (value == null) {
			return "";
		}
		return value;
	}

	/* Getters & Setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.util.converter.Converter#getModelType()
	 */
	@Override
	public Class<String> getModelType() {
		return String.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.util.converter.Converter#getPresentationType()
	 */
	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}
}
