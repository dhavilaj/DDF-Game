package com.ddf.model.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;

import com.ddf.commons.exceptions.services.InactiveUserException;
import com.ddf.commons.exceptions.services.InvalidEmailException;
import com.ddf.commons.exceptions.services.InvalidIpLoginException;
import com.ddf.commons.exceptions.services.InvalidPasswordLoginException;
import com.ddf.commons.vo.AuthInfoVO;
import com.ddf.commons.vo.CredentialVO;
import com.ddf.commons.vo.ProfileVO;
import com.ddf.commons.vo.UserVO;
import com.ddf.model.controlaccess.AccessValidator;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.services.local.UserServicesLocal;
import com.ddf.view.i18n.Messages;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
@ManagedBean("socialSession")
@SessionScoped
public class SocialSessionImpl implements Serializable, SocialSession {

	private static final long serialVersionUID = -8697247980973925937L;
	@SuppressWarnings("unused")
	private static final long PUBLIC_PROFILE_ID = 2; // TODO: Configuration. Get
														// this from
														// configuration
														// file

	/* Dependencies */
	@Inject
	private UserServicesLocal userServices;
	//@Inject
	//private ProfileServicesLocal profileServices;
	@Inject
	private AccessValidator accessValidator;

	/* Instance variables */
	private UserVO currentAccount;
	private List<ProfileVO> currentProfiles;
	private Locale locale;
	private AuthInfoVO authInfoVO;

	/**
	 * Initialize the bean
	 */
	@PostConstruct
	public void initialize() {
		loadPublicUser();
	}

	/**
	 * Load Public User
	 */
	private void loadPublicUser() {
		// Initialize a public user
		currentAccount = new UserVO();
		currentAccount.setFirstName("Public");
		currentAccount.setLastName("User");
		currentProfiles = new ArrayList<>();
//		try {
//			//currentProfiles.add(profileServices.find(PUBLIC_PROFILE_ID));
//		} catch (ServiceException e) {
//			throw new RuntimeException(e);
//		}
		currentProfiles = java.util.Collections
			.unmodifiableList(currentProfiles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.model.session.SocialSession#login(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void login(CredentialVO credentialVO) throws LoginFailedException {

		authInfoVO = null;
		try {

			authInfoVO = userServices.authenticate(credentialVO);
			currentAccount = authInfoVO.getUser();
			accessValidator.clear();

		} catch (InvalidEmailException e) {
			throw new LoginFailedException(Messages
				.getString(Messages.LOGIN_INVALID_EMAIL_MESSAGE));
		} catch (InvalidPasswordLoginException e) {
			throw new LoginFailedException(Messages
				.getString(Messages.LOGIN_INVALID_PASSWORD_MESSAGE));
		} catch (InactiveUserException e) {
			throw new LoginFailedException(Messages
				.getString(Messages.LOGIN_INACTIVE_USER_MESSAGE));
		} catch (InvalidIpLoginException e) {
			throw new LoginFailedException(Messages
				.getString(Messages.LOGIN_INVALID_IP_MESSAGE));
		}
	}

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.session.SocialSession#logout()
	 */
	@Override
	public void logout() {
		loadPublicUser();
		this.authInfoVO = null;
		accessValidator.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.session.SocialSession#getCurrentAccount()
	 */
	@Override
	public UserVO getCurrentAccount() {
		return currentAccount;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.session.SocialSession#isLogged()
	 */
	@Override
	public boolean isLogged() {
		return currentAccount.getId() != null;
	}

	@Override
	public Locale getLocale() {
		if (locale == null) {
			locale = new Locale("en");
		}
		return locale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.session.SocialSession#getDefaultView()
	 */
	@Override
	public DDFViewEnum getDefaultView() {
		if (isLogged()) {
			return DDFViewEnum.HOME;
		} else {
			return DDFViewEnum.LOGIN;
		}
	}

	/**
	 * Return The current social session
	 * 
	 * @return The current social session
	 */
	public final static SocialSession getCurrent() {
		return CDI.current().select(SocialSession.class).get();
	}

}
