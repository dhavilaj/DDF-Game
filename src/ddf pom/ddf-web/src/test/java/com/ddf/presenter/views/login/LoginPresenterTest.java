package com.ddf.presenter.views.login;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ddf.helpers.TestBeanValidator;
import com.ddf.model.session.SocialSession;
import com.ddf.presenter.views.login.LoginPresenter;
import com.ddf.view.views.login.LoginView;

@SuppressWarnings("javadoc")
public class LoginPresenterTest {

	/* static fields */

	/* instance variables */
	private SocialSession socialSession;
	private LoginView loginView;
	private LoginPresenter loginPresenter;

	/* constructors */
	@Before
	public void initialize() {
		socialSession = Mockito.mock(SocialSession.class);
		loginView = Mockito.mock(LoginView.class);

		loginPresenter = new LoginPresenter(loginView, socialSession);
	}

	/* Methods */

	@Test
	public void processLoginSubmitTest_EmailValidationAll() {
		loginPresenter.getCredentialVO().setPassword("12345678");
		TestBeanValidator.testEmailValidation(loginPresenter.getCredentialVO(),
				"email", true);
	}


	/* Getters & Setters */
}
