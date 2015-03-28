package com.ddf.view.views.forbidden;

import java.io.File;
import java.util.Map;

import javax.inject.Inject;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.model.session.SocialSession;
import com.ddf.presenter.views.forbidden.ForbiddenPresenter;
import com.ddf.view.base.DDFUI.TemplateType;
import com.ddf.view.base.DDFViewBase;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;

/**
 * View for forbidden
 * 
 * @author davidmantilla
 *
 */
@CDIView(value = DDFViewNames.FORBIDDEN)
@ViewScoped
public class ForbiddenViewImpl extends DDFViewBase<ForbiddenViewListener>
	implements ForbiddenView {

	private static final long serialVersionUID = 6597626282172834924L;
	@Inject
	private SocialSession socialSession;

	/**
	 * Constructor
	 */
	public ForbiddenViewImpl() {
		initComponents();
	}

	/**
	 * Initialize all components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {
		// Find the application directory
		String basepath = VaadinService.getCurrent().getBaseDirectory()
			.getAbsolutePath();
		// Image as a file resource
		FileResource resource = new FileResource(new File(basepath
			+ "/resources/images/forbidden-icon-128.png"));
		Image image = new Image("", resource);
		addComponent(image);

		// TODO: i18n
		Label label = new Label();
		label.setCaption("This place is Forbidden");
		addComponent(label);

		Button button = new Button();
		button.setCaption("Take me to an Allowed Place");
		button.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (ForbiddenViewListener l : getViewListeners()) {
					l.processAllowedPlaceClick();
				}
			}
		});
		addComponent(button);
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

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.base.SocialViewBase#initSocialViewl(java.util
	 * .Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
		ViewChangeEvent event) {
		// creates presenter
		new ForbiddenPresenter(this, socialSession);
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
