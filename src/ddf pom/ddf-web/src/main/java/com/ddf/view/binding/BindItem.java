package com.ddf.view.binding;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.vaadin.data.Item;
import com.vaadin.data.Property;

/**
 * Social item implementation to allow data binding to table
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class BindItem implements Item {

	/* static fields */
	private static final long serialVersionUID = 8135182074799944334L;

	/* instance variables */
	private Map<Object, Property<?>> properties;
	private Object bean;

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param bean
	 */
	public BindItem(Object bean) {
		this.bean = bean;
		this.properties = new HashMap<>();
	}

	/* Methods */

	/* Item implementation */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Item#getItemProperty(java.lang.Object)
	 */
	@Override
	public Property<?> getItemProperty(Object id) {
		return properties.get(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Item#getItemPropertyIds()
	 */
	@Override
	public Collection<?> getItemPropertyIds() {
		return properties.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Item#addItemProperty(java.lang.Object,
	 * com.vaadin.data.Property)
	 */
	@Override
	public boolean addItemProperty(Object id,
			@SuppressWarnings("rawtypes") Property property)
			throws UnsupportedOperationException {

		if (properties.keySet().contains(id)) {
			return false;
		} else {
			if (property instanceof BindProperty<?>) {
				BindProperty<?> bindProperty = (BindProperty<?>) property;
				bindProperty.setBean(bean);
			}
			properties.put(id, property);
			return true;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Item#removeItemProperty(java.lang.Object)
	 */
	@Override
	public boolean removeItemProperty(Object id)
			throws UnsupportedOperationException {
		if (properties.keySet().contains(id)) {
			properties.remove(id);
			return true;
		} else {
			return false;
		}

	}

	/* Getters & Setters */
}
