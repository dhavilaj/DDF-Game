package com.ddf.view.converters;

import java.util.Locale;

import javax.faces.convert.ConverterException;

import com.vaadin.data.util.converter.Converter;

/**
 * Generic converter which acts like a adapter between the SocialViewConverter
 * (vaadin independent converter) and the vaadin converter
 * 
 * @author David Mantilla
 *
 * @param <T>
 * @since 1.7
 */
public class GenericConverter<T> implements Converter<String, T> {

	/* static fields */
	private static final long serialVersionUID = 683741294213892656L;

	/* instance variables */
	private final SocialViewConverter<T> socialViewConverter;

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param socialViewConverter
	 */
	public GenericConverter(SocialViewConverter<T> socialViewConverter) {
		this.socialViewConverter = socialViewConverter;
	}

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToModel(java.lang.Object,
	 * java.lang.Class, java.util.Locale)
	 */
	@Override
	public T convertToModel(String value, Class<? extends T> targetType,
			Locale locale) throws ConversionException {
		try {
			return socialViewConverter.getAsObject(value, locale);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.data.util.converter.Converter#convertToPresentation(java.lang
	 * .Object, java.lang.Class, java.util.Locale)
	 */
	@Override
	public String convertToPresentation(T value,
			Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		try {
			return socialViewConverter.getAsString(value, locale);
		} catch (Exception e) {
			throw new ConverterException(e);
		}
	}

	/* Getters & Setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.util.converter.Converter#getModelType()
	 */
	@Override
	public Class<T> getModelType() {
		return socialViewConverter.getModelType();
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
