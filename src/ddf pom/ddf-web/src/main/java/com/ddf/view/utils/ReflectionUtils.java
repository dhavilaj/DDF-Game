package com.ddf.view.utils;

import java.lang.reflect.Method;

/**
 * Utilities for reflection
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ReflectionUtils {

	/* static fields */

	/* instance variables */

	/* constructors */

	/* Methods */

	/**
	 * This method gets the container class for a property. In a simple property
	 * the holder class would be the same beanClass, but in a nested property,
	 * the class would be the penultimate property class. For example, in a
	 * propertyName "property1.property2.property3" the returned class would be
	 * the property2 class
	 * 
	 * @param beanClass
	 * @param propertyName
	 * @return the holder class for the property
	 * @throws NoSuchMethodException when the property doesn't exist in the bean
	 *             or it doesn't have a getter
	 * @throws SecurityException
	 */
	public static Class<?> getPropertyClassHolder(Class<?> beanClass,
			String propertyName) throws NoSuchMethodException,
			SecurityException {
		String[] properties = propertyName.split("\\.");
		if (properties.length == 1) {
			return beanClass;
		}
		Class<?> holderClass = beanClass;
		for (int i = 0; i < properties.length - 1; i++) {

			Method getter = holderClass.getMethod("get"
					+ properties[i].substring(0, 1).toUpperCase()
					+ properties[i].substring(1), new Class[] {});
			holderClass = getter.getReturnType();
		}
		return holderClass;
	}
	/* Getters & Setters */
}
