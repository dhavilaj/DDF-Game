package com.ddf.view.components.breadcrumb;

import java.util.Map;

import com.ddf.model.controlaccess.DDFViewEnum;

/**
 * @author David Mantilla
 *
 * @since 1.7
 */
public class BreadCrumbClickEvent {
	/* static fields */

	/* instance variables */
	private final String label;
	private final int index;
	private final Map<String, String> params;
	private final DDFViewEnum socialViewEnum;

	/* Methods */

	/* constructors */
	/**
	 * Constructor
	 * 
	 * @param label
	 * @param index
	 * @param params
	 * @param socialViewEnum
	 */
	public BreadCrumbClickEvent(String label, int index,
			Map<String, String> params, DDFViewEnum socialViewEnum) {
		super();
		this.label = label;
		this.index = index;
		this.params = params;
		this.socialViewEnum = socialViewEnum;
	}

	/* Getters & Setters */

	/**
	 * Getter for label
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Getter for index
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Getter for params
	 * 
	 * @return the params
	 */
	public Map<String, String> getParams() {
		return params;
	}

	/**
	 * Getter for socialViewEnum
	 * 
	 * @return the socialViewEnum
	 */
	public DDFViewEnum getSocialViewEnum() {
		return socialViewEnum;
	}
}
