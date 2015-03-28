package com.ddf.view.views.password;

import java.util.Map;

import javax.inject.Inject;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.presenter.views.password.PassRecoverPrequestPresenter;
import com.ddf.services.local.UserServicesLocal;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.converters.StringConverter;
import com.ddf.view.i18n.Messages;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.Reindeer;

/**
 * Login View implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@CDIView(value = DDFViewNames.PASSWORD_RECOVERY_REQUEST,
	supportsParameters = true)
@ViewScoped()
public class PassRecoverRequestViewImpl extends
	DDFViewBase<PassRecoverRequestViewListener> implements
	PassRecoverRequestView {

	private static final long serialVersionUID = -864436340147453319L;
	private TextField emailField;

	@Inject
	private UserServicesLocal userServices;

	/**
	 * Constructor
	 */
	public PassRecoverRequestViewImpl() {
		initComponents();
	}

	/**
	 * Initialize vaadin components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {

		Page.getCurrent().setTitle(
			Messages.getString("PassRecoveryRequestView.Title"));//$NON-NLS-1$

		// Password recovery request wrapper
		CssLayout loginWrapper = new CssLayout();
		loginWrapper.addStyleName("password-request-form-wrapper"); //$NON-NLS-1$
		addComponent(loginWrapper);

		// Title
		Label label = new Label(Messages
			.getString("PassRecoveryRequestView.Title"));//$NON-NLS-1$
		label.setStyleName(Reindeer.LABEL_H1);
		loginWrapper.addComponent(label);

		// Form
		FormLayout formLayout = new FormLayout();
		formLayout.addStyleName("password-request-form"); //$NON-NLS-1$
		formLayout.setSizeUndefined();
		loginWrapper.addComponent(formLayout);

		// Introdunction text
		label = new Label(Messages.getString("PassRecoveryRequestView.Intro"));//$NON-NLS-1$
		formLayout.addComponent(label);

		// email field
		emailField = new TextField();
		emailField.setCaption(Messages
			.getString("PassRecoveryRequestView.Email")); //$NON-NLS-1$
		emailField.setConverter(new StringConverter());
		emailField.setValidationVisible(false);
		formLayout.addComponent(emailField);
		registerField(PassRecoverRequestView.ViewField.EMAIL.name(), emailField);

		// Submit button
		Button button = new Button();
		button.setCaption(Messages.getString("PassRecoveryRequestView.Submit")); //$NON-NLS-1$
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (PassRecoverRequestViewListener l : getViewListeners()) {
					if (!validateFields(true,
						PassRecoverRequestView.ViewField.EMAIL.name())) {
						return;
					}

					// call the listener
					l.processSubmit(emailField.getValue());
				}
			}

		});
		formLayout.addComponent(button);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#initSocialViewl(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
		ViewChangeEvent event) {
		new PassRecoverPrequestPresenter(this, userServices);
	}

	/**
	 * Process enter event in the view
	 * 
	 * @param event
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
		ViewChangeEvent event) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialViewBase#getTemplateType()
	 */
	@Override
	public TemplateType getTemplateType() {
		return TemplateType.PUBLIC;
	}

}
