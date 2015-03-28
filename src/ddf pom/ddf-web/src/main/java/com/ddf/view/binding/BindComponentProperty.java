package com.ddf.view.binding;

import com.vaadin.ui.Component;

/**
 * This class is for creating properties which has a component which behavior
 * depends on the value of the bounded expression
 * 
 * @author David Mantilla
 * @param <T>
 * @since 1.7
 */
public class BindComponentProperty<T extends Component> extends BindProperty<T> {

	/* static fields */
	private static final long serialVersionUID = 7110232950280096708L;

	/**
	 * This is the interface for the component creator implementation
	 * 
	 * @param <T> Component class
	 */
	public interface ComponentCreator<T extends Component> {
		/**
		 * The implementation of this method should create the component based
		 * on the value object. The value object is the result of evaluating
		 * expression over bean.
		 * 
		 * @param value Evaluated expression over the bean
		 * @param bean Row bean
		 * @return The created component
		 */
		public T createComponent(Object value, Object bean);
	}

	/* instance variables */
	private T component;
	private ComponentCreator<T> componentCreator;

	/* constructors */
	/**
	 * Creates an instance of this class copying another object Constructor
	 * 
	 * @param other
	 */
	public BindComponentProperty(BindComponentProperty<T> other) {
		super(other);
		this.componentCreator = other.componentCreator;
	}

	/**
	 * Constructor
	 * 
	 * @param bean
	 * @param expression
	 * @param type
	 * @param componentCreator
	 */
	public BindComponentProperty(Object bean, String expression, Class<T> type,
		ComponentCreator<T> componentCreator) {
		super(bean, expression, type);
		this.componentCreator = componentCreator;
	}

	/**
	 * Constructor
	 * 
	 * @param expression
	 * @param type
	 * @param componentCreator
	 */
	public BindComponentProperty(String expression, Class<T> type,
		ComponentCreator<T> componentCreator) {
		super(expression, type);
		this.componentCreator = componentCreator;
	}

	/* Getters & Setters */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.binding.BindProperty#getValue()
	 */
	@Override
	public T getValue() {
		if (component == null) {
			component = componentCreator
				.createComponent(super.getValue(), super.getBean());
		}
		return null;
	}

}
