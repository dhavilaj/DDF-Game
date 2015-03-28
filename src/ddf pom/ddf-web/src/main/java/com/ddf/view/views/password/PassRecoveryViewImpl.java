package com.ddf.view.views.password;

import java.util.Map;

import javax.inject.Inject;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.presenter.views.password.PassRecoveryPresenter;
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
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.themes.Reindeer;

/**
 * Password Recovery View implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@CDIView(value = DDFViewNames.PASSWORD_RECOVERY, supportsParameters = true)
@ViewScoped()
public class PassRecoveryViewImpl extends
		DDFViewBase<PassRecoveryViewListener> implements PassRecoveryView {

	private static final long serialVersionUID = -864436340147453319L;
	private PasswordField passwordField;
	private PasswordField passwordConfirmField;

	@Inject
	private UserServicesLocal userServices;

	/**
	 * Constructor
	 */
	public PassRecoveryViewImpl() {
		initComponents();
	}

	/**
	 * Initialize vaadin components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {

		Page.getCurrent()
				.setTitle(Messages.getString("PassRecoveryView.Title"));//$NON-NLS-1$

		// Password recovery wrapper
		CssLayout loginWrapper = new CssLayout();
		loginWrapper.addStyleName("password-recovery-form-wrapper"); //$NON-NLS-1$
		addComponent(loginWrapper);

		// Title
		Label label = new Label(Messages.getString("PassRecoveryView.Title"));//$NON-NLS-1$
		label.setStyleName(Reindeer.LABEL_H1);
		loginWrapper.addComponent(label);

		// Form
		FormLayout formLayout = new FormLayout();
		formLayout.addStyleName("password-recovery-form");
		formLayout.setSizeUndefined();
		loginWrapper.addComponent(formLayout);

		// Introduction text
		label = new Label(Messages.getString("PassRecoveryView.Intro"));//$NON-NLS-1$
		formLayout.addComponent(label);

		// Password field
		passwordField = new PasswordField();
		passwordField.setCaption(Messages
				.getString("PassRecoveryView.Password")); //$NON-NLS-1$
		passwordField.setConverter(new StringConverter());
		passwordField.setValidationVisible(false);
		formLayout.addComponent(passwordField);
		registerField(PassRecoveryView.ViewField.PASSWORD.name(), passwordField);

		passwordConfirmField = new PasswordField();
		passwordConfirmField.setCaption(Messages
				.getString("PassRecoveryView.ConfirmPassword")); //$NON-NLS-1$
		passwordConfirmField.setConverter(new StringConverter());
		passwordConfirmField.setValidationVisible(false);
		formLayout.addComponent(passwordConfirmField);
		registerField(PassRecoveryView.ViewField.PASSWORD_CONFIRM.name(),
				passwordConfirmField);

		// Submit button
		Button button = new Button();
		button.setCaption(Messages.getString("PassRecoveryView.Submit")); //$NON-NLS-1$
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (PassRecoveryViewListener l : getViewListeners()) {
					if (!validateFields(true,
							PassRecoveryView.ViewField.PASSWORD.name(),
							PassRecoveryView.ViewField.PASSWORD_CONFIRM.name())) {
						return;
					}

					// call the listener
					l.processSubmit(passwordField.getValue(),
							passwordConfirmField.getValue());
				}
			}

		});
		formLayout.addComponent(button);
	}

	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
			ViewChangeEvent event) {
		new PassRecoveryPresenter(this, userServices);
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
