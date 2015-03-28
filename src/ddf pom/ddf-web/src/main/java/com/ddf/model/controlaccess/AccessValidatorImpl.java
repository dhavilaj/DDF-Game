package com.ddf.model.controlaccess;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import com.ddf.commons.vo.AppAccessInfoVO;
import com.ddf.commons.vo.UseCaseVO;
import com.ddf.model.session.SocialSession;
import com.ddf.services.local.UserServicesLocal;

/**
 * AccessValidator implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@ManagedBean("accessValidator")
@SessionScoped
public class AccessValidatorImpl implements AccessValidator, Serializable {

	/* Static variables */
	private static final long serialVersionUID = -3594590690446235427L;
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger
			.getLogger(AccessValidatorImpl.class.getName());

	/* Dependencies */
	@Inject
	private SocialSession socialSession;
	@Inject
	private UserServicesLocal userServices;

	/* Instance variables */
	private List<UseCaseEnum> useCasesSet;

	private Boolean accessToApp;

	/**
	 * Default Constructor
	 */
	public AccessValidatorImpl() {
	}

	/**
	 * Constructor
	 * 
	 * @param socialSession
	 * @param userServices
	 */
	public AccessValidatorImpl(SocialSession socialSession,
			UserServicesLocal userServices) {
		super();
		this.socialSession = socialSession;
		this.userServices = userServices;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.controlaccess.AccessValidator#validateAccessToView
	 * (com.ddf.model.controlaccess.SocialViewEnum)
	 */
	@Override
	public boolean validateAccessToView(DDFViewEnum socialViewEnum) {
		if (socialViewEnum.isAlwasyPublic()) {
			return true;
		}
		for (UseCaseEnum useCaseEnum : socialViewEnum.getUseCases()) {
			if (validateAccessToUseCase(useCaseEnum)) {
				return true;
			}
		}
		// If none use-case found view hasn't access
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.controlaccess.AccessValidator# validateAccessToUseCase
	 * (com.ddf.model.controlaccess.UseCaseEnum)
	 */
	@Override
	public boolean validateAccessToUseCase(UseCaseEnum useCaseEnum) {
		if (useCasesSet == null) {
			loadAppAccessInformation();
		}
		return useCasesSet.contains(useCaseEnum);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.controlaccess.AccessValidator#clearCache()
	 */
	@Override
	public void clear() {
		useCasesSet = null;
	}

	/**
	 * Load access information
	 */
	private void loadAppAccessInformation() {

		useCasesSet = new ArrayList<UseCaseEnum>();
		accessToApp = false;

		AppAccessInfoVO accessInfoVO;
		if (socialSession.isLogged()) {
			accessInfoVO = userServices.getAppAccessInfo(socialSession
					.getCurrentAccount().getId());
			// access to application
			accessToApp = true;

			// allowed use-cases
			for (UseCaseVO useCaseVO : accessInfoVO.getUseCases()) {
				useCasesSet.add(UseCaseEnum.findById(useCaseVO.getId()));
			}

		} else {
			loadPublicAccessInfo();
		}
	}

	/**
	 * Load public profile's access information
	 */
	private void loadPublicAccessInfo() {
		useCasesSet = new ArrayList<UseCaseEnum>();
		accessToApp = false;

		// TODO: This should be retrieved from database
		useCasesSet.add(UseCaseEnum.LOGIN);
		useCasesSet.add(UseCaseEnum.REGISTER);
		useCasesSet.add(UseCaseEnum.SEE_ABOUT_SECTION);
		useCasesSet.add(UseCaseEnum.SEE_MOBIERA_SECTION);
		useCasesSet.add(UseCaseEnum.SEE_PRIVACY_SECTION);
		useCasesSet.add(UseCaseEnum.SEE_TERMS_SECTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.model.controlaccess.AccessValidator#validateAccessToSocial
	 * ()
	 */
	@Override
	public boolean validateAccessToSocial() {
		if (accessToApp == null) {
			loadAppAccessInformation();
		}
		return accessToApp;
	}

}
