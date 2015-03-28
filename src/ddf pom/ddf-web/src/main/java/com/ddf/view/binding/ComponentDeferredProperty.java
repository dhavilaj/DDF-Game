package com.ddf.view.binding;

import com.vaadin.data.Property;
import com.vaadin.ui.Component;

/**
 * This is a component property. The difference with a VAADIN ObjectProperty is
 * that the value (a component) is not created until the value is requested.
 * This is perfect for bounded table because in a bounded table every row should
 * create its own components as the same component can't appear more than one.
 * When a property of this type is added to a table container, a component will
 * be created via componentCreator for each row
 * 
 * @author David Mantilla
 *
 * @param <T> Class of the root component to be added to the cell
 * @since 1.7
 */
public class ComponentDeferredProperty<T extends Component> implements
	Property<T> {

	/* static */
	private static final long serialVersionUID = 2349718231805506612L;

	/**
	 * This is the interface for the component creator implementation
	 * 
	 * @param <T> Component class
	 */
	public interface ComponentCreator<T extends Component> {
		/**
		 * The implementation of this method should create the component
		 * 
		 * @return The created component
		 */
		public T createComponent();
	}

	/* instance variables */
	private Class<T> type;
	private T component;
	private ComponentCreator<T> componentCreator;

	/**
	 * Constructor
	 * 
	 * @param type
	 */
	public ComponentDeferredProperty(Class<T> type) {
		this.type = type;
	}

	/**
	 * Constructor
	 * 
	 * @param type
	 * @param component
	 */
	public ComponentDeferredProperty(Class<T> type, T component) {
		this.type = type;
		this.component = component;
	}

	/* Methods */

	/* Property implementation */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#getValue()
	 */
	@Override
	public T getValue() {
		if (component == null) {
			component = componentCreator.createComponent();
		}
		return component;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(T newValue)
		throws com.vaadin.data.Property.ReadOnlyException {
		throw new ReadOnlyException();
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

	@Override
	public boolean isReadOnly() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.data.Property#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean newStatus) {
		//
	}
}
