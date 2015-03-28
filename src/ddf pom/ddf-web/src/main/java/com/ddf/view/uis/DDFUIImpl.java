package com.ddf.view.uis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.ddf.model.controlaccess.AccessValidator;
import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.model.session.SocialSession;
import com.ddf.presenter.templates.MainTemplatePresenter;
import com.ddf.presenter.ui.SocialUIPresenter;
import com.ddf.view.base.DDFUI;
import com.ddf.view.base.DDFUIListener;
import com.ddf.view.templates.MainTemplateImpl;
import com.ddf.view.templates.PublicTemplate;
import com.ddf.view.utils.ParamUtils;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.cdi.CDIUI;
import com.vaadin.cdi.CDIViewProvider;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.UI;

/**
 * SocialUI Implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Theme("ddf")
@CDIUI("")
@Widgetset("com.ddf.vaadin.DDFWidgetset")
public class DDFUIImpl extends UI implements DDFUI {

	private static final long serialVersionUID = -8168241896112036073L;

	/* dependencies */
	@Inject
	private CDIViewProvider viewProvider;

	@Inject
	private AccessValidator accessValidator;

	@Inject
	private SocialSession socialSession;

	/* instance variables */
	private List<DDFUIListener> socialUIListeners;

	private TemplateType socialTemplate;

	private ComponentContainer viewSection;

	/**
	 * This flag enabled or disable the page leaving check. If the flag is
	 * disabled, the "leave" method is called but the result is ignored, so the
	 * navigation is done despite a false return
	 */
	private boolean viewLeavingCheckEnabled;

	/**
	 * Constructor
	 */
	public DDFUIImpl() {
		// List of listeners
		socialUIListeners = new ArrayList<DDFUIListener>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.UI#init(com.vaadin.server.VaadinRequest)
	 */
	@Override
	protected void init(VaadinRequest request) {
		// session locale
		setLocale(socialSession.getLocale());

		// Public template by default
		createNavigator();
		setSocialTemplate(TemplateType.NONE);
	}

	/**
	 * Initialize the bean
	 */
	@PostConstruct
	public void initialize() {
		new SocialUIPresenter(this, accessValidator);
	}

	/* Listener list handling */

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.uis.SocialUI#addSocialUIListener(com.mobiera.
	 * social.view.uis.SocialUIListener)
	 */
	@Override
	public void addSocialUIListener(DDFUIListener socialUIListener) {
		socialUIListeners.add(socialUIListener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.uis.SocialUI#clearSocialUIListeners()
	 */
	@Override
	public void clearSocialUIListeners() {
		socialUIListeners.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.uis.SocialUI#removeSocialUIListener(com.mobiera
	 * .social.view.uis.SocialUIListener)
	 */
	@Override
	public void removeSocialUIListener(DDFUIListener listener) {
		socialUIListeners.remove(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.uis.SocialUI#getSocialUIListeners()
	 */
	@Override
	public List<DDFUIListener> getSocialUIListeners() {
		return Collections.unmodifiableList(socialUIListeners);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.uis.SocialUI#navigateTo(com.ddf.model
	 * .controlaccess.SocialViewEnum)
	 */
	@Override
	public void navigateTo(DDFViewEnum socialViewEnum) {
		getNavigator().navigateTo(socialViewEnum.getViewName());
	}

	/**
	 * Creates the navigator for the specified content
	 * 
	 * @param content
	 */
	@SuppressWarnings("serial")
	private void createNavigator() {
		Navigator navigator = null;

		navigator = new Navigator(this, new ViewDisplay() {
			@Override
			public void showView(View view) {
				viewSection.removeAllComponents();
				viewSection.addComponent((Component) view);
			}
		});
		navigator.addProvider(viewProvider);

		// this must be the last listener as it changes the template
		navigator.addViewChangeListener(new DDFViewListener(this));
	}

	@Override
	public boolean setSocialTemplate(DDFUI.TemplateType requestedTemplate) {
		// it the requested template is already the current template, there's
		// nothing to do
		if (requestedTemplate.equals(socialTemplate)) {
			return false;
		}

		this.socialTemplate = requestedTemplate;
		if (requestedTemplate.equals(TemplateType.NONE)) {
			CssLayout cssLayout = new CssLayout();
			setContent(cssLayout);
			viewSection = cssLayout;
			return true;
		} else if (requestedTemplate.equals(TemplateType.PUBLIC)) {
			PublicTemplate template = new PublicTemplate();
			setContent(template);
			viewSection = template.getNavigationSection();
			return true;
		} else if (requestedTemplate.equals(TemplateType.MAIN)) {
			MainTemplateImpl template = new MainTemplateImpl();
			setContent(template);
			viewSection = template.getNavigationSection();
			new MainTemplatePresenter(template, socialSession, accessValidator);
			return true;
		} else {
			throw new UnsupportedOperationException(
				"Template not yet supported");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialView#navigateTo(com.mobiera.
	 * social.model.controlaccess.SocialViewEnum, java.util.Map)
	 */
	@Override
	public void navigateTo(DDFViewEnum socialViewDefinition,
		Map<String, String> params) {
		StringBuilder uriFragment = new StringBuilder(socialViewDefinition
			.getViewName());
		uriFragment.append(ParamUtils.paramsMapToUri(params));
		UI.getCurrent().getNavigator().navigateTo(uriFragment.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialUI#getCurrentRequest()
	 */
	@Override
	public HttpServletRequest getCurrentRequest() {
		return (HttpServletRequest) VaadinService.getCurrentRequest();
	}

	/**
	 * Getter for viewLeavingCheckEnabled
	 * 
	 * @return the viewLeavingCheckEnabled
	 */
	@Override
	public boolean isViewLeavingCheckEnabled() {
		return viewLeavingCheckEnabled;
	}

	/**
	 * Setter for viewLeavingCheckEnabled
	 * 
	 * @param viewLeavingCheckEnabled the viewLeavingCheckEnabled to set
	 */
	@Override
	public void setViewLeavingCheckEnabled(boolean viewLeavingCheckEnabled) {
		this.viewLeavingCheckEnabled = viewLeavingCheckEnabled;
	}

}
