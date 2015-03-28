package com.ddf.view.views.login;

import java.util.Map;

import javax.inject.Inject;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.model.session.SocialSession;
import com.ddf.presenter.views.login.LoginPresenter;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.converters.StringConverter;
import com.ddf.view.i18n.Messages;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

/**
 * Login View implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@CDIView(value = DDFViewNames.LOGIN, supportsParameters = true)
@ViewScoped()
public class LoginViewImpl extends DDFViewBase<LoginViewListener> implements
	LoginView {

	private static final long serialVersionUID = -864436340147453319L;
	private TextField emailField;
	private PasswordField passwordField;

	@Inject
	private SocialSession socialSession;

	/**
	 * Constructor
	 */
	public LoginViewImpl() {
		initComponents();
	}

	/**
	 * Initialize vaadin components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {

		// Login wrapper
		CssLayout loginWrapper = new CssLayout();
		loginWrapper.addStyleName("login-form-wrapper"); //$NON-NLS-1$
		addComponent(loginWrapper);

		// Logo
		VerticalLayout logoLayout = new VerticalLayout();
		logoLayout.addStyleName("scrum-logo"); //$NON-NLS-1$
		loginWrapper.addComponent(logoLayout);

		// Login panel container
		FormLayout formLayout = new FormLayout();
		formLayout.addStyleName("login-form"); //$NON-NLS-1$
		formLayout.setSizeUndefined();
		loginWrapper.addComponent(formLayout);

		// email field
		emailField = new TextField();
		emailField.setCaption(Messages.getString("LoginView.Email")); //$NON-NLS-1$
		emailField.setConverter(new StringConverter());
		emailField.setValidationVisible(false);
		formLayout.addComponent(emailField);
		registerField(LoginView.ViewField.EMAIL.name(), emailField);

		// Password field
		passwordField = new PasswordField();
		passwordField.setCaption(Messages.getString("LoginView.Password")); //$NON-NLS-1$
		passwordField.setConverter(new StringConverter());
		passwordField.setValidationVisible(false);
		registerField(LoginView.ViewField.PASSWORD.name(), passwordField);
		formLayout.addComponent(passwordField);

		// Submit button
		Button button = new Button();
		button.setCaption(Messages.getString("LoginView.Login")); //$NON-NLS-1$
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (LoginViewListener l : getViewListeners()) {
					if (!validateFields(true, LoginView.ViewField.EMAIL.name(),
						LoginView.ViewField.PASSWORD.name())) {
						return;
					}

					// call the listener
					l.processLoginSubmit(emailField.getValue(), passwordField
						.getValue());
				}
			}

		});
		formLayout.addComponent(button);

		// Forgot password button
		Button forgotPassword = new Button();
		forgotPassword.setStyleName(BaseTheme.BUTTON_LINK);
		forgotPassword.setCaption(Messages
			.getString("LoginView.ForgotPassword")); //$NON-NLS-1$
		forgotPassword.addClickListener(new Button.ClickListener() {
			@Override
			public void buttonClick(ClickEvent event) {
				for (LoginViewListener l : getViewListeners()) {
					l.processForgotPasswordClick();
				}
			}
		});
		loginWrapper.addComponent(forgotPassword);
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

	}

	/**
	 * Process enter event in the view
	 * 
	 * @param event
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
		ViewChangeEvent event) {
		// clear presenter every time enters, no cache
		clearViewListeners();
		new LoginPresenter(this, socialSession);
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
