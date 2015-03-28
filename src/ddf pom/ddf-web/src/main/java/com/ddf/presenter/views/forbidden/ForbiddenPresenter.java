package com.ddf.presenter.views.forbidden;

import com.ddf.model.session.SocialSession;
import com.ddf.view.views.forbidden.ForbiddenView;
import com.ddf.view.views.forbidden.ForbiddenViewListener;

/**
 * Presenter for the forbidden view
 * 
 * @author davidmantilla
 * @since 1.7
 *
 */
public class ForbiddenPresenter implements ForbiddenViewListener {

	private ForbiddenView view;
	private SocialSession socialSession;

	/**
	 * Constructor
	 * 
	 * @param forbiddenView
	 * @param socialSession
	 */
	public ForbiddenPresenter(ForbiddenView forbiddenView,
			SocialSession socialSession) {
		this.view = forbiddenView;
		this.socialSession = socialSession;
		this.view.addViewListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.views.forbidden.ForbiddenViewListener#
	 * processAllowedPlaceClick()
	 */
	@Override
	public void processAllowedPlaceClick() {
		this.view.navigateTo(socialSession.getDefaultView());
	}

}
