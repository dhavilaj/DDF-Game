package com.ddf.presenter.views.password;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.ddf.commons.exceptions.services.InactiveUserException;
import com.ddf.commons.exceptions.services.InvalidEmailException;
import com.ddf.commons.exceptions.services.MailServicesErrorException;
import com.ddf.commons.i18n.ValidationMessages;
import com.ddf.commons.validation.NotEmpty;
import com.ddf.commons.validation.ValidationPatterns;
import com.ddf.services.local.UserServicesLocal;
import com.ddf.view.i18n.Messages;
import com.ddf.view.views.password.PassRecoverRequestView;
import com.ddf.view.views.password.PassRecoverRequestViewListener;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class PassRecoverPrequestPresenter implements
	PassRecoverRequestViewListener {

	/* static fields */
	private static final Logger LOG = Logger
		.getLogger(PassRecoverPrequestPresenter.class.getName());

	/* dependencies */
	private final PassRecoverRequestView view;
	private final UserServicesLocal userServices;

	@NotNull
	@NotEmpty
	@Size(min = 0, max = 80)
	@Pattern(regexp = ValidationPatterns.EMAIL_PATTERN, message = "{"
		+ ValidationMessages.EMAIL_PATTERN + "}")
	private String email;

	/* instance variables */

	/* constructors */
	/**
	 * Constructor
	 * 
	 * @param view
	 * @param userServices
	 */
	public PassRecoverPrequestPresenter(PassRecoverRequestView view,
		UserServicesLocal userServices) {
		super();
		this.view = view;
		this.view.addViewListener(this);
		this.userServices = userServices;

		// binds components
		this.view.bindField(this,
			PassRecoverRequestView.ViewField.EMAIL.name(), "email",
			String.class);
	}

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.password.PassRecoverRequestViewListener
	 * #processSubmit(java.lang.String)
	 */
	@Override
	public void processSubmit(String email) {
		String url = RecoveryLinkUtils.buildUrl(email);
		try {
			userServices.sendRecoveryPasswordEmail(email, url);
			view.showInfoMessage(Messages.getString("PassRecoveryRequestPresenter.Success"));
		} catch (InvalidEmailException e) {
			view.showErrorMessage(Messages
				.getString(Messages.LOGIN_INVALID_EMAIL_MESSAGE));
		} catch (InactiveUserException e) {
			view.showErrorMessage(Messages
				.getString(Messages.LOGIN_INACTIVE_USER_MESSAGE));
		} catch (MailServicesErrorException e) {
			LOG.log(Level.SEVERE, "Unexpected error sending email", e);
			view.showErrorMessage(Messages.getString(Messages.UNEXPECTED_ERROR));
		}
	}

	/**
	 * Getter for email
	 * 
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email
	 * 
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/* Getters & Setters */
}
