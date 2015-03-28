package com.ddf.view.views.home;

import java.util.Map;

import com.ddf.model.controlaccess.DDFViewNames;
import com.ddf.vaadin.widgetset.homeboard.HomeBoardCanvas;
import com.ddf.view.base.DDFViewBase;
import com.ddf.view.base.DDFUI.TemplateType;
import com.vaadin.cdi.CDIView;
import com.vaadin.cdi.ViewScoped;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button;

/**
 * Home View implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@CDIView(value = DDFViewNames.HOME, supportsParameters = true)
@ViewScoped
public class HomeViewImpl extends DDFViewBase<HomeViewListener> implements
		HomeView {

	private static final long serialVersionUID = -1953120507028872885L;
	private HomeBoardCanvas myCanvas;

	/**
	 * Home View Impl
	 */
	public HomeViewImpl() {
		initComponents();
	}

	/**
	 * Initialize components
	 */
	private void initComponents() {
		Button button = new Button( "Hola Mundo" );
		this.addComponent(button);
		
		myCanvas = new HomeBoardCanvas();
		this.addComponent(myCanvas);
		myCanvas.markAsDirty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.views.base.SocialViewBase#enterSocialView(java
	 * .util.Map, com.vaadin.navigator.ViewChangeListener.ViewChangeEvent)
	 */
	@Override
	protected void enterSocialView(Map<String, String> params,
			ViewChangeEvent event) {
	}

	@Override
	protected void initSocialViewl(Map<String, String> viewParameters2,
			ViewChangeEvent event) {
		//
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.base.SocialViewBase#getTemplateType()
	 */
	@Override
	public TemplateType getTemplateType() {
		return TemplateType.MAIN;
	}

}
