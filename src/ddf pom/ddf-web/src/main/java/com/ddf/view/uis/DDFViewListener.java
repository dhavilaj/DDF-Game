package com.ddf.view.uis;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.base.DDFUI;
import com.ddf.view.base.DDFUIListener;
import com.ddf.view.base.DDFView;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.utils.ParamUtils;
import com.vaadin.navigator.ViewChangeListener;

/**
 * View change Listener for the social UI
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class DDFViewListener implements ViewChangeListener {

	private final DDFUI socialUI;
	private static final long serialVersionUID = -4588205950959365102L;

	/**
	 * Constructor
	 * 
	 * @param socialUI
	 */
	public DDFViewListener(DDFUI socialUI) {
		super();
		this.socialUI = socialUI;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.ViewChangeListener#beforeViewChange(com.vaadin.navigator
	 * .ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public boolean beforeViewChange(ViewChangeEvent event) {
		for (DDFUIListener l : socialUI.getSocialUIListeners()) {
			boolean result = l.processBeforeViewChange((DDFView<?>) (event
				.getOldView()), (DDFView<?>) (event.getNewView()));
			if (!result) {
				return false;
			}
		}

		// identifies the view
		DDFViewBase<?> newView = (DDFViewBase<?>) (event.getNewView());
		DDFViewEnum viewEnum = DDFViewEnum
			.findByClass(newView.getClass());

		// check if the current view allows leaving (e.g. unsaved changes)
		if (socialUI.isViewLeavingCheckEnabled()) {
			if (event.getOldView() instanceof DDFViewBase) {
				DDFViewBase<?> oldSocialView = (DDFViewBase<?>) event
					.getOldView();
				boolean canGo = oldSocialView.leave(viewEnum, ParamUtils
					.uriParamsToMap(event.getParameters()));
				if (!canGo) {
					return false;
				}
			}
		}
		// the flag only can be disabled for one navigation try
		socialUI.setViewLeavingCheckEnabled(true);

		// Change template
		socialUI.setSocialTemplate(newView.getTemplateType());

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.vaadin.navigator.ViewChangeListener#afterViewChange(com.vaadin.navigator
	 * .ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	public void afterViewChange(ViewChangeEvent event) {
		for (DDFUIListener l : socialUI.getSocialUIListeners()) {
			l.processAfterViewChange((DDFView<?>) (event.getOldView()),
				(DDFView<?>) (event.getNewView()));
		}
	}

}
