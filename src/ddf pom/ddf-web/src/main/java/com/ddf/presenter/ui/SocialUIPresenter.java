package com.ddf.presenter.ui;

import com.ddf.model.controlaccess.AccessValidator;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.base.DDFUI;
import com.ddf.view.base.DDFUIListener;
import com.ddf.view.base.DDFView;

/**
 * @author David Mantilla
 *
 */
@SuppressWarnings("rawtypes")
public class SocialUIPresenter implements DDFUIListener {

	/* Instance variables */
	private DDFUI socialUI;
	private AccessValidator accessValidator;

	/**
	 * Constructor
	 * 
	 * @param socialUI
	 * @param accessValidator
	 */
	public SocialUIPresenter(DDFUI socialUI,
			AccessValidator accessValidator) {
		this.socialUI = socialUI;
		this.accessValidator = accessValidator;
		this.socialUI.addSocialUIListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.uis.SocialUIListener#processBeforeViewChange(
	 * com.ddf.view.views.base.SocialView,
	 * com.ddf.view.views.base.SocialView)
	 */
	@Override
	public boolean processBeforeViewChange(DDFView oldView,
			DDFView newView) {
		DDFViewEnum newViewEnum = DDFViewEnum.identifyView(newView);
		if (!accessValidator.validateAccessToView(newViewEnum)) {
			socialUI.navigateTo(DDFViewEnum.FORBIDDEN);
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.uis.SocialUIListener#processAfterViewChange(com
	 * .mobiera.social.view.views.base.SocialView,
	 * com.ddf.view.views.base.SocialView)
	 */
	@Override
	public void processAfterViewChange(DDFView socialView,
			DDFView socialView2) {
		// Nothing to do
	}

}
