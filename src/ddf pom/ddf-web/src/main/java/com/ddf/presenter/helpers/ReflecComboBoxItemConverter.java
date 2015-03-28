package com.ddf.presenter.helpers;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.ddf.view.base.ComboBoxItemConverter;

/**
 * Implements a comboBoxItemConverter by using reflection to get label and id
 * fields
 * 
 * @author David Mantilla
 *
 * @param <T>
 * @since 1.7
 */
public class ReflecComboBoxItemConverter<T> implements ComboBoxItemConverter<T> {

	private final String idPropertyName;
	private final String labelPropertyName;

	/**
	 * Constructor
	 * 
	 * @param idPropertyName Name of the property for obtaining the id from the
	 *            object. Use "" if want the object itself
	 * @param labelPropertyName Name of the property for obtain the label from
	 *            the object. Use "" if want the object itself
	 */
	public ReflecComboBoxItemConverter(String idPropertyName,
		String labelPropertyName) {
		this.idPropertyName = idPropertyName;
		this.labelPropertyName = labelPropertyName;
	}

	/* static fields */

	@Override
	public Object getIdFrom(T object) {
		if (idPropertyName.equals("")) {
			return object;
		}
		try {
			return PropertyUtils.getProperty(object, idPropertyName);
		} catch (IllegalAccessException | InvocationTargetException
			| NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getLabelFrom(T object) {
		if (labelPropertyName.equals("")) {
			return object == null ? "" : object.toString();
		}
		try {
			return BeanUtils.getProperty(object, labelPropertyName);
		} catch (IllegalAccessException | InvocationTargetException
			| NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
