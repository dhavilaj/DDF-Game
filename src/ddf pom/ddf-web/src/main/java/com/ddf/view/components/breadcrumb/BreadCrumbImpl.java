package com.ddf.view.components.breadcrumb;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ddf.model.controlaccess.DDFViewEnum;
import com.ddf.view.base.ComponentBase;
import com.ddf.view.base.DDFUI;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.BaseTheme;

/**
 * This is a custom component to control bread-crumb navigation
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class BreadCrumbImpl extends ComponentBase<BreadCrumbListener> implements
	BreadCrumb {

	/* static fields */
	private static final long serialVersionUID = -6675899613352597022L;

	class BreadCrumbVO {
		private DDFViewEnum viewEnum;
		private Map<String, String> params;
	}

	/* instance variables */
	private List<BreadCrumbVO> breadCrumbVOs;
	private HorizontalLayout breadCrumbWrapper;

	/* constructors */

	/**
	 * Constructor
	 */
	public BreadCrumbImpl() {
		this.breadCrumbVOs = new ArrayList<>();

		initComponents();
	}

	/**
	 * Creates all components
	 */
	private void initComponents() {
		breadCrumbWrapper = new HorizontalLayout();
		breadCrumbWrapper.setStyleName("breadcrumb-wrapper");
		setCompositionRoot(breadCrumbWrapper);
	}

	/* Methods */

	/* Getters & Setters */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.components.breadcrumb.BreadCrumb#addBreadCrumb
	 * (java.lang.String, com.ddf.model.controlaccess.SocialViewEnum,
	 * java.util.Map)
	 */
	@Override
	public void addBreadCrumb(String label, DDFViewEnum view,
		Map<String, String> params, boolean clickeable) {
		Component point = createBreadCrumb(label, view, params, clickeable);
		breadCrumbWrapper.addComponent(point);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.components.breadcrumb.BreadCrumb#addBreadCrumb
	 * (com.vaadin.ui.Component)
	 */
	@Override
	public void addBreadCrumb(Component component) {
		breadCrumbWrapper.addComponent(component);

	}

	/**
	 * Creates the breadcrumb
	 * 
	 * @param label
	 * @param socialViewEnum
	 * @param params
	 * @param clickeable
	 * @return
	 */
	@SuppressWarnings("serial")
	private Component createBreadCrumb(String label,
		DDFViewEnum socialViewEnum, Map<String, String> params,
		boolean clickeable) {

		// Creates the value object to remember where to navigate to
		BreadCrumbVO vo = new BreadCrumbVO();
		vo.params = params;
		vo.viewEnum = socialViewEnum;
		breadCrumbVOs.add(vo);

		// creates components
		HorizontalLayout pointWrapper = new HorizontalLayout();
		Button button = new Button();
		button.setCaption(label);
		button.setStyleName(BaseTheme.BUTTON_LINK);
		pointWrapper.addComponent(button);

		final int index = breadCrumbVOs.size() - 1;
		if (clickeable) {
			button.addClickListener(new Button.ClickListener() {
				@Override
				public void buttonClick(ClickEvent event) {
					// creates event object
					BreadCrumbClickEvent breadCrumbClickEvent = new BreadCrumbClickEvent(
						event.getButton().getCaption(), index, breadCrumbVOs
							.get(index).params,
						breadCrumbVOs.get(index).viewEnum);

					// call all listeners
					for (BreadCrumbListener l : getViewListeners()) {
						if (!l
							.processBeforeBreadCrumbNavigate(breadCrumbClickEvent)) {
							return;
						}
					}

					// navigates to the requested view
					((DDFUI) UI.getCurrent()).navigateTo(breadCrumbVOs
						.get(index).viewEnum, breadCrumbVOs.get(index).params);
				}
			});
		}

		Label separator = new Label();
		separator.setCaption(" > ");
		pointWrapper.addComponent(separator);

		return pointWrapper;

	}

}
