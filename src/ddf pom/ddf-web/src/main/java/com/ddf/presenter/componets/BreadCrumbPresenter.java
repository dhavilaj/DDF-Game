package com.ddf.presenter.componets;

import java.util.List;

import com.ddf.view.components.breadcrumb.BreadCrumb;
import com.ddf.view.components.breadcrumb.BreadCrumbClickEvent;
import com.ddf.view.components.breadcrumb.BreadCrumbListener;

/**
 * Implemented presenter for breadcrumb
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class BreadCrumbPresenter implements BreadCrumbListener {

	/* static fields */

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 * 
	 * @param component
	 * @param breadCrumbList
	 */
	public BreadCrumbPresenter(BreadCrumb component,
			List<BreadCrumbVO> breadCrumbList) {

		component.addViewListener(this);

		for (BreadCrumbVO breadCrumbVO : breadCrumbList) {
			component.addBreadCrumb(breadCrumbVO.getLabel(),
					breadCrumbVO.getSocialViewEnum(), breadCrumbVO.getParams(),
					breadCrumbVO.isClickeable());
		}
	}

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.breadcrumb.BreadCrumbListener#
	 * processBreadCrumbClick
	 * (com.ddf.view.components.breadcrumb.BreadCrumbClickEvent)
	 */
	@Override
	public boolean processBeforeBreadCrumbNavigate(
			BreadCrumbClickEvent crumbClickEvent) {
		return true;
	}

	/* Getters & Setters */
}
