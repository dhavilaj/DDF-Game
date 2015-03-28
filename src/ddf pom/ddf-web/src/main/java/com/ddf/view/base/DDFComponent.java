package com.ddf.view.base;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.converters.SocialViewConverter;

/**
 * Interface for all social views.
 * 
 * @author David Mantilla
 *
 * @param <U> Type of the view listener
 */
public interface DDFComponent<U extends DDFViewListener> {

	/**
	 * Remove a view listener
	 * 
	 * @param listener Listener to remove
	 */
	public void removeViewListener(U listener);

	/**
	 * Clear the listener list
	 */
	public void clearViewListeners();

	/**
	 * Add a view listener
	 * 
	 * @param viewListener listener to register
	 */
	public void addViewListener(U viewListener);

	/**
	 * Getter for the ViewListeners. The returned list is unmodifiable, to add
	 * or remove listeners add and remove methods should be used
	 * 
	 * @return The unmodifiable list of view listeners
	 */
	public List<U> getViewListeners();

	/**
	 * Shows a notification error message
	 * 
	 * @param message Text to show in the message
	 */
	public void showErrorMessage(String message);

	/**
	 * Shows a notification info message
	 * 
	 * @param message Text to show in the message
	 */
	public void showInfoMessage(String message);

	/**
	 * Navigates to a new view
	 * 
	 * @param socialViewEnum view for navigate to
	 */
	public void navigateTo(DDFViewEnum socialViewEnum);

	/**
	 * Navigates to a new view with parameters
	 * 
	 * @param socialViewEnum view for navigate to
	 * @param params Map(name-value) with the parameters to pass to the new view
	 */
	public void navigateTo(DDFViewEnum socialViewEnum,
		Map<String, String> params);


	/**
	 * Binds a property of the "bean" attribute to a field of the view and
	 * creates a bean validator for the field based on the javax.validation
	 * annotations of the field
	 * 
	 * @param <T> Class of the property
	 * @param bean Bean where the property will be extracted from
	 * @param viewFieldName name of the field in the view
	 * @param expression expression to get the property, this could be a first
	 *            level property or a nested property (foo.foo2)
	 * @param type Type of the property. If is a nested property then the class
	 *            must be the last one
	 */
	public <T> void bindField(Object bean, String viewFieldName,
		String expression, Class<T> type);

	/**
	 * Sets a converter in a view registered field
	 * 
	 * @param <T> Model value class
	 * @param viewFieldName
	 * @param socialViewConverter
	 */
	public <T> void setFieldConverter(String viewFieldName,
		SocialViewConverter<T> socialViewConverter);

	/**
	 * Getter for the current request
	 * 
	 * @return the current request
	 */
	public HttpServletRequest getCurrentRequest();

}
