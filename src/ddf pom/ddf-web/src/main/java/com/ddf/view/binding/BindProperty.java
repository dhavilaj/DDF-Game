package com.ddf.view.binding;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.vaadin.data.Property;

/**
 * @author David Mantilla
 *
 * @param <T>
 * @since 1.7
 */
public class BindProperty<T> implements Property<T> {

	private static final long serialVersionUID = -6840767581160374553L;
	private Class<T> type;
	private String expression;
	private Object bean;
	private boolean readOnly;

	/* static fields */

	/* instance variables */

	/* constructors */

	/**
	 * Constructor for creating a clone
	 * 
	 * @param bindProperty
	 */
	public BindProperty(BindProperty<T> bindProperty) {
		this.type = bindProperty.type;
		this.bean = bindProperty.bean;
		this.expression = bindProperty.expression;
	}

	/**
	 * Constructor
	 * 
	 * @param bean
	 * @param expression
	 * @param type
	 */
	public BindProperty(Object bean, String expression, Class<T> type) {
		this.type = type;
		this.bean = bean;
		this.expression = expression;
	}

	/**
	 * Constructor
	 * 
	 * @param expression
	 * @param type
	 */
	public BindProperty(String expression, Class<T> type) {
		this.type = type;
		this.expression = expression;
	}

	/* Methods */

	/* Getters & Setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#getValue()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		try {
			String[] properties = expression.split("\\.");
			Object propertyValue = bean;
			for (int i = 0; i < properties.length; i++) {
				propertyValue = PropertyUtils.getProperty(propertyValue,
					properties[i]);
				if (propertyValue == null) {
					break;
				}
			}
			return (T) propertyValue;
		} catch (IllegalAccessException | InvocationTargetException
			| NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(T newValue)
		throws com.vaadin.data.Property.ReadOnlyException {
		try {
			BeanUtils.setProperty(bean, expression, newValue);
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#getType()
	 */
	@Override
	public Class<? extends T> getType() {
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#isReadOnly()
	 */
	@Override
	public boolean isReadOnly() {
		return this.readOnly;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		this.readOnly = readOnly;
	}

	/**
	 * Getter for bean
	 * 
	 * @return the bean
	 */
	public Object getBean() {
		return bean;
	}

	/**
	 * Setter for bean
	 * 
	 * @param bean the bean to set
	 */
	public void setBean(Object bean) {
		this.bean = bean;
	}

	/**
	 * Getter for expression
	 * 
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * Setter for expression
	 * 
	 * @param expression the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}
}
