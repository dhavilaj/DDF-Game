package com.ddf.view.views.register;

import java.util.Map;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Label;

/**
 * Register view implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@CDIView(value = DDFViewNames.REGISTER)
@ViewScoped
public class RegisterViewImpl extends DDFViewBase<RegisterViewListener>
	implements RegisterView {

	private static final long serialVersionUID = -4069178135159210766L;

	/**
	 * Constructor
	 */
	public RegisterViewImpl() {
		Label label = new Label("I'm register view");
		addComponent(label);
	}

	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
		ViewChangeEvent event) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.views.base.SocialViewBase#enterSocialView(java
	 * .util.Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
		ViewChangeEvent event) {
		// creates presenter:
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
