package com.ddf.presenter.templates;

import com.ddf.model.controlaccess.AccessValidator;
import com.ddf.model.controlaccess.ForbiddenException;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.model.session.SocialSession;
import com.ddf.view.templates.MainTemplate;
import com.ddf.view.templates.MainTemplate.App;
import com.ddf.view.templates.MainTemplate.Operation;
import com.ddf.view.templates.MainTemplateListener;

/**
 * Mail Template presenter class
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class MainTemplatePresenter implements MainTemplateListener {

	/* static fields */

	/* instance variables */

	/* dependencies */
	private final SocialSession socialSession;
	@SuppressWarnings("unused")
	private final AccessValidator accessValidator;
	private final MainTemplate template;

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param template
	 * @param socialSession
	 * @param accessValidator
	 */
	public MainTemplatePresenter(MainTemplate template,
		SocialSession socialSession, AccessValidator accessValidator) {
		super();
		this.template = template;
		this.socialSession = socialSession;
		this.accessValidator = accessValidator;

		this.template.addViewListener(this);
		this.initTemplate();
	}

	/* Methods */
	/**
	 * Initialize the template
	 */
	private void initTemplate() {
		// set email
		this.template.setAccountEmail(socialSession.getCurrentAccount()
			.getEmail());


	}

	/* MainTemplateListener implementation */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processApplicationClick
	 * (com.ddf.view.templates.MainTemplate.App)
	 */
	@Override
	public void processApplicationClick(App clickedApp) {
//		for (AppVO appVO : socialSession.getAllowedApps()) {
//			if (appVO.getId().equals(clickedApp.getId())) {
//				appVO.getMainUrl();
//				return;
//			}
//		}
		throw new ForbiddenException(
			"User hasn't got permission to go application");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processSuperviseOperationClick
	 * (com.ddf.view.templates.MainTemplate.Operation)
	 */
	@Override
	public void processSuperviseOperationClick(Operation clickedOperation) {
		// TODO: 1. load clicked operation, put operation in session, reload
		// current page
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processMoreSupervisedOperationsClick()
	 */
	@Override
	public void processMoreSupervisedOperationsClick() {
		// template.navigateTo(SocialViewEnum.APPS_PANEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processMyAccountMenuItemClick()
	 */
	@Override
	public void processMyAccountMenuItemClick() {
		// template.navigateTo(SocialViewEnum.MY_ACCOUNT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processAdminMenuItemClick()
	 */
	@Override
	public void processAdminMenuItemClick() {
		//template.navigateTo(SocialViewEnum.ADMIN_PANEL);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.templates.MainTemplateListener#
	 * processLogoutMenuItemClick()
	 */
	@Override
	public void processLogoutMenuItemClick() {
		// avoid the current page block the logout redirection:
		socialSession.logout();
		this.template.getSocialUI().setViewLeavingCheckEnabled(false);
		this.template.navigateTo(DDFViewEnum.LOGIN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.templates.MainTemplateListener#processLogoClick()
	 */
	@Override
	public void processLogoClick() {
		template.navigateTo(DDFViewEnum.HOME);
	}

	/* Getters & Setters */
}
