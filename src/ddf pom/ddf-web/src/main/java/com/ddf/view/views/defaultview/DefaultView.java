package com.ddf.view.views.defaultview;

import java.util.Map;

import javax.inject.Inject;

import com.ddf.model.session.SocialSession;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.vaadin.cdi.CDIView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

/**
 * This is a dummy view which redirect to the current default view registered in
 * the view, normally this means redirecting to login if user is not logged, or
 * redirecting home otherwise.
 * 
 * @author David Mantilla
 * @since 1.7
 */
@SuppressWarnings("rawtypes")
@CDIView("")
public class DefaultView extends DDFViewBase implements View {

	private static final long serialVersionUID = -2860820268488852821L;

	@Inject
	private SocialSession socialSession;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#enterSocialView(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void enterSocialView(Map params, ViewChangeEvent event) {
		// redirects current default view
		navigateTo(socialSession.getDefaultView());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#initSocialViewl(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void initSocialViewl(Map viewParameters2, ViewChangeEvent event) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialViewBase#getTemplateType()
	 */
	@Override
	public TemplateType getTemplateType() {
		return TemplateType.PUBLIC;
	}

}
