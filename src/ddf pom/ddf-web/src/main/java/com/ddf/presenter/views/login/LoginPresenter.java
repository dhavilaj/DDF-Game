package com.ddf.presenter.views.login;

import com.ddf.commons.vo.CredentialVO;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.model.session.LoginFailedException;
import com.ddf.model.session.SocialSession;
import com.ddf.view.views.login.LoginView;
import com.ddf.view.views.login.LoginViewListener;

/**
 * Presenter for the login view
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class LoginPresenter implements LoginViewListener {

	/* Dependencies */
	private SocialSession socialSession;
	private LoginView view;
	private CredentialVO credentialVO;

	/**
	 * Alternative constructor to inject all dependencies (Testing mode)
	 * 
	 * @param socialSession
	 * @param view
	 */
	public LoginPresenter(LoginView view, SocialSession socialSession) {
		super();
		this.socialSession = socialSession;
		this.credentialVO = new CredentialVO();
		this.view = view;
		this.view.addViewListener(this);

		// binds components
		this.view.bindField(this, LoginView.ViewField.EMAIL.name(),
			"credentialVO.email", String.class);
		this.view.bindField(this, LoginView.ViewField.PASSWORD.name(),
			"credentialVO.password", String.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.login.LoginViewListener#processLoginSubmit
	 * (java.lang.String, java.lang.String)
	 */
	@Override
	public void processLoginSubmit(String email, String password) {
		try {
			// gets request IP
			String ipAddress = view.getCurrentRequest().getHeader(
				"X-FORWARDED-FOR");
			if (ipAddress == null) {
				ipAddress = view.getCurrentRequest().getRemoteAddr();
			}
			socialSession.login(new CredentialVO(email, password, ipAddress));
			view.navigateTo(DDFViewEnum.HOME);
		} catch (LoginFailedException e) {
			this.view.showErrorMessage(e.getMessage());
		}
	}

	/* Getters y Setters */

	/**
	 * Getter for credentialVO
	 * 
	 * @return the credentialVO
	 */
	public CredentialVO getCredentialVO() {
		return credentialVO;
	}

	/**
	 * Setter for credentialVO
	 * 
	 * @param credentialVO the credentialVO to set
	 */
	public void setCredentialVO(CredentialVO credentialVO) {
		this.credentialVO = credentialVO;
	}

	/* (non-Javadoc)
	 * @see com.ddf.view.views.login.LoginViewListener#processForgotPasswordClick()
	 */
	@Override
	public void processForgotPasswordClick() {
		this.view.navigateTo(DDFViewEnum.PASSWORD_RECOVERY_REQUEST);
	}
}
