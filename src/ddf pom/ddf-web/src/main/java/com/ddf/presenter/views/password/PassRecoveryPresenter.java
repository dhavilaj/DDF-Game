package com.ddf.presenter.views.password;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Logger;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ddf.commons.exceptions.services.InactiveUserException;
import com.ddf.commons.i18n.ValidationMessages;
import com.ddf.commons.validation.ValidationPatterns;
import com.ddf.commons.vo.UserVO;
import com.ddf.commons.vo.QueryResult;
import com.ddf.commons.vo.criteria.UserQueryCriteria;
import com.ddf.model.controlaccess.ForbiddenException;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.services.local.UserServicesLocal;
import com.ddf.view.i18n.Messages;
import com.ddf.view.views.password.PassRecoveryView;
import com.ddf.view.views.password.PassRecoveryViewListener;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class PassRecoveryPresenter implements PassRecoveryViewListener {

	/* static fields */
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
		.getLogger(PassRecoveryPresenter.class.getName());

	@SuppressWarnings("javadoc")
	public static final String PARAM_NAME_EMAIL = "email";
	@SuppressWarnings("javadoc")
	public static final String PARAM_NAME_TOKEN = "token";
	@SuppressWarnings("javadoc")
	public static final String PARAM_NAME_TOKEN_TIME = "token-time";

	/* dependencies */
	private final PassRecoveryView view;
	private final UserServicesLocal userServices;

	/* instance variables */

	@NotNull
	@Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = "{"
		+ ValidationMessages.PASSWORD_PATTERN + "}")
	private String password;

	@NotNull
	@Pattern(regexp = ValidationPatterns.PASSWORD_PATTERN, message = "{"
		+ ValidationMessages.PASSWORD_PATTERN + "}")
	private String passwordConfirm;

	private UserVO account;

	/* instance variables */

	/* constructors */
	/**
	 * Constructor
	 * 
	 * @param view
	 * @param userServices
	 */
	public PassRecoveryPresenter(PassRecoveryView view,
		UserServicesLocal userServices) {
		super();
		this.view = view;
		this.view.addViewListener(this);
		this.userServices = userServices;

		// binds components
		this.view.bindField(this, PassRecoveryView.ViewField.PASSWORD.name(),
			"password", String.class);
		this.view.bindField(this, PassRecoveryView.ViewField.PASSWORD_CONFIRM
			.name(), "passwordConfirm", String.class);

		// load view parameters
		initializeFromParams(view, userServices);
	}

	/**
	 * Initialize from parameters views
	 * 
	 * @param view
	 * @param accountServices
	 */
	private void initializeFromParams(PassRecoveryView view,
		UserServicesLocal userServices) {
		UserQueryCriteria accountQueryCriteria = new UserQueryCriteria(0,
			1);

		String email = view.getViewParameters().get(PARAM_NAME_EMAIL);
		String tokenTime = view.getViewParameters().get(PARAM_NAME_TOKEN_TIME);
		String token = view.getViewParameters().get(PARAM_NAME_TOKEN);
		if (tokenTime == null || email == null || token == null) {
			throw new ForbiddenException();
		}

		// get email from view parameters
		try {
			// finds email and account
			email = URLDecoder.decode(email, "utf-8");
		} catch (UnsupportedEncodingException e) {
			throw new UnsupportedOperationException(e); // with UTF-8 ???
														// You
														// have to be
														// kidding
		}

		// find account by email in database
		accountQueryCriteria.setEmailEquals(email);
		QueryResult<UserVO> queryResult = userServices
			.findAccounts(accountQueryCriteria);
		if (queryResult.getResult().isEmpty()) {
			throw new ForbiddenException();
		}
		account = queryResult.getResult().get(0);

		// validates token

		if (!RecoveryLinkUtils.validateToken(token, tokenTime, email)) {
			throw new ForbiddenException();
		}

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
	public void processSubmit(String password, String passwordConfirm) {
		// validates match
		if (!password.equals(passwordConfirm)) {
			view.showErrorMessage(Messages
				.getString(Messages.PASSWORD_NOT_MATCH));
			return;
		}

		try {
			userServices.updatePassword(account.getId(), password);
			view.showInfoMessage(Messages
				.getString(Messages.PASSWORD_CHANGED_SUCCESS));
			view.navigateTo(DDFViewEnum.LOGIN);
		} catch (InactiveUserException e) {
			view.showErrorMessage(Messages
				.getString(Messages.LOGIN_INACTIVE_USER_MESSAGE));
		}
	}

	/**
	 * Getter for password
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password
	 * 
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for passwordConfirm
	 * 
	 * @return the passwordConfirm
	 */
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	/**
	 * Setter for passwordConfirm
	 * 
	 * @param passwordConfirm the passwordConfirm to set
	 */
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	/* Getters & Setters */
}
