package com.ddf.view.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.model.session.SocialSessionImpl;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.binding.BindProperty;
import com.ddf.view.converters.GenericConverter;
import com.ddf.view.converters.SocialViewConverter;
import com.ddf.view.uis.DDFUIImpl;
import com.ddf.view.utils.ParamUtils;
import com.ddf.view.utils.ReflectionUtils;
import com.ddf.view.validators.SocialValidator;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.validator.BeanValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.Position;
import com.vaadin.ui.AbstractField;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This class is the base for all the social application views
 * 
 * @author David Mantilla
 *
 * @param <U> Type of the view listener
 * */
/**
 * @author David Mantilla
 *
 * @param <U>
 * @since 1.7
 */
/**
 * @author David Mantilla
 *
 * @param <U>
 * @since 1.7
 */
public abstract class DDFViewBase<U extends DDFViewListener> extends
	VerticalLayout implements View, DDFView<U> {

	/* static */
	private static final long serialVersionUID = -1389607391985209121L;
	private List<U> viewListeners;
	private Map<String, String> viewParameters;
	private Map<String, AbstractField<?>> fieldsMap;
	private boolean alreadyLoaded;

	/**
	 * Constructor
	 */
	public DDFViewBase() {
		this.viewListeners = new ArrayList<U>();
		this.fieldsMap = new HashMap<>();
	}

	/* Methods */

	/* Messages handling */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#showErrorMessage(java.lang
	 * .String)
	 */
	@Override
	public void showErrorMessage(String message) {
		showMessage(message, Type.ERROR_MESSAGE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#showInfoMessage(java.lang
	 * .String)
	 */
	@Override
	public void showInfoMessage(String message) {
		showMessage(message, Type.ASSISTIVE_NOTIFICATION);
	}

	/**
	 * Shows a notification
	 * 
	 * @param message
	 * @param type
	 */
	protected void showMessage(String message, Type type) {
		Notification notification = new Notification(message, type);
		notification.setDelayMsec(6000);
		notification.setPosition(Position.BOTTOM_RIGHT);
		notification.show(Page.getCurrent());
	}

	/* Listener list handling */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#addViewListener(com.mobiera
	 * .social.view.views.base.SocialViewListener)
	 */
	public void addViewListener(U viewBaseListener) {
		viewListeners.add(viewBaseListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.views.base.SocialView#clearViewListeners()
	 */
	@Override
	public void clearViewListeners() {
		viewListeners.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#removeViewListener(com.
	 * mobiera.social.view.views.base.SocialViewListener)
	 */
	@Override
	public void removeViewListener(U listener) {
		viewListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.views.base.SocialView#getViewListeners()
	 */
	@Override
	public List<U> getViewListeners() {
		return new ArrayList<U>(viewListeners);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.views.base.SocialView#getViewParameters()
	 */
	@Override
	public Map<String, String> getViewParameters() {
		return viewParameters;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#navigateTo(com.mobiera.
	 * social.model.controlaccess.SocialViewEnum)
	 */
	@Override
	public void navigateTo(DDFViewEnum socialViewDefinition) {
		UI.getCurrent().getNavigator().navigateTo(
			socialViewDefinition.getViewName());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#navigateTo(com.mobiera.
	 * social.model.controlaccess.SocialViewEnum, java.util.Map)
	 */
	@Override
	public void navigateTo(DDFViewEnum socialViewDefinition,
		Map<String, String> params) {
		((DDFUI) DDFUIImpl.getCurrent()).navigateTo(socialViewDefinition,
			params);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.View#enter(com.vaadin.navigator.ViewChangeListener
	 * .ViewChangeEvent)
	 */
	@Override
	final public void enter(ViewChangeEvent event) {
		viewParameters = ParamUtils.uriParamsToMap(event.getParameters());
		if (!alreadyLoaded) {
			initSocialViewl(viewParameters, event);
			alreadyLoaded = true;
		}
		// enter to the view
		enterSocialView(viewParameters, event);
	}

	/**
	 * The implementation of this method should do every action in the view
	 * initializing. It is called when the view enters by the first and only
	 * first time
	 * 
	 * @param viewParameters2
	 * @param event
	 */
	protected abstract void initSocialViewl(
		Map<String, String> viewParameters2, ViewChangeEvent event);

	/**
	 * The implementation of this method should do every action in the view
	 * entering
	 * 
	 * @param params
	 * @param event
	 */
	protected abstract void enterSocialView(Map<String, String> params,
		ViewChangeEvent event);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.presenter.base.SocialView#bindProperty(java.lang.Object
	 * , java.lang.String, java.lang.String, java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public <T> void bindField(Object bean, String viewFieldName,
		String expression, Class<T> type) {
		// binds the property to the field
		BindProperty<T> boundedProperty = new BindProperty<T>(bean, expression,
			type);
		AbstractField field = findField(viewFieldName);
		field.setPropertyDataSource(boundedProperty);

		// creates a bean validator for the field based on the bounded property
		String[] properties = expression.split("\\.");
		String lastPropertyName = properties[properties.length - 1];
		try {
			BeanValidator beanValidator = new BeanValidator(ReflectionUtils
				.getPropertyClassHolder(bean.getClass(), expression),
				lastPropertyName);
			beanValidator.setLocale(SocialSessionImpl.getCurrent().getLocale());
			field.addValidator(beanValidator);
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.presenter.base.SocialView#setFieldConverter(java.lang
	 * .String, com.ddf.view.converters.SocialViewConverter)
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public <T> void setFieldConverter(String viewFieldName,
		SocialViewConverter<T> socialViewConverter) {
		AbstractField field = findField(viewFieldName);
		field.setConverter(new GenericConverter<T>(socialViewConverter));
	}

	/**
	 * Add a validator for a registered field
	 * 
	 * @param fieldName
	 * @param socialValidator
	 */
	public void addFieldValidator(String fieldName,
		SocialValidator socialValidator) {
		findField(fieldName).addValidator(socialValidator);
	}

	/**
	 * Remove a validator for a registered field
	 * 
	 * @param fieldName
	 * @param socialValidator
	 */
	public void removeFieldValidator(String fieldName,
		SocialValidator socialValidator) {
		findField(fieldName).removeValidator(socialValidator);
	}

	/**
	 * This method allows to register a field for future binding. The method
	 * bindProperty will be search the viewFieldName only in the list of the
	 * registeredFields
	 * 
	 * @param fieldName
	 * @param field
	 */
	protected void registerField(String fieldName, AbstractField<?> field) {
		fieldsMap.put(fieldName, field);
	}

	/**
	 * Finds a field in the map of the registered fields
	 * 
	 * @param viewFieldName
	 * @param fieldName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	protected AbstractField findField(String viewFieldName) {
		AbstractField field = fieldsMap.get(viewFieldName);
		if (field == null) {
			throw new IllegalArgumentException(viewFieldName
				+ " field was not found in the view");
		}
		return field;
	}

	/**
	 * Validates registered fields
	 * 
	 * @param showMessages If this is true then a notification message with
	 *            errors will be shown
	 * @param fieldNames list of field names
	 * @return true if all validations are sucessful, false otherwise
	 */
	public boolean validateFields(boolean showMessages, String... fieldNames) {
		// validate field
		StringBuilder errorMessage = new StringBuilder();
		boolean success = true;
		for (String field : fieldNames) {
			AbstractField<?> fieldToValidate = findField(field);
			try {
				fieldToValidate.setValidationVisible(true);
				fieldToValidate.validate();
			} catch (InvalidValueException e) {
				errorMessage.append(fieldToValidate.getCaption()).append(" : ")
					.append(e.getCauses()[0].getMessage()).append("\n");
				success = false;
			}
		}
		if (showMessages && !success) {
			showErrorMessage(errorMessage.toString());
		}
		return success;
	}

	/**
	 * The implementation of this method should return the template that the
	 * view would be showed in
	 * 
	 * @return the template type this page needs
	 */
	public abstract TemplateType getTemplateType();

	/**
	 * @return the current social UI
	 */
	public DDFUIImpl getCurrentUI() {
		return (DDFUIImpl) UI.getCurrent();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialView#getCurrentRequest()
	 */
	@Override
	public HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) VaadinService.getCurrentRequest();
	}

	/**
	 * This method is called when the page is leaving
	 * 
	 * @param newView View to go after if the current view allows leaving
	 * @param params Parameters for the new view
	 * 
	 * @return true if the navigator may going to another page. If return false
	 *         the navigation will be blocked
	 */
	public boolean leave(DDFViewEnum newView, Map<String, String> params) {
		return true;
	}

	@Override
	public DDFUI getSocialUI() {
		return (DDFUI) UI.getCurrent();
	}
}
