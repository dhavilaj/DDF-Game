package com.ddf.presenter.componets;

import java.util.HashMap;
import java.util.Map;

import com.ddf.model.controlaccess.DDFViewEnum;

/**
 * Value object for representing a bread-crumb point
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class BreadCrumbVO {

	/* static fields */

	/* instance variables */
	private DDFViewEnum socialViewEnum;
	private String label;
	private Map<String, String> params = new HashMap<String, String>();
	private boolean clickeable;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public BreadCrumbVO() {
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * Constructor
	 * 
	 * @param socialViewEnum
	 * @param label
	 * @param params
	 * @param clickeable
	 */
	public BreadCrumbVO(DDFViewEnum socialViewEnum, String label,
		Map<String, String> params, boolean clickeable) {
		super();
		this.socialViewEnum = socialViewEnum;
		this.label = label;
		this.params = params;
		this.clickeable = clickeable;
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
	 * Setter for params
	 * 
	 * @param params the params to set
	 */
	public void setParams(Map<String, String> params) {
		this.params = params;
	}

	/**
	 * Getter for readOnly
	 * 
	 * @return the readOnly
	 */
	public boolean isClickeable() {
		return clickeable;
	}

	/**
	 * Setter for readOnly
	 * 
	 * @param readOnly the readOnly to set
	 */
	public void setClickeable(boolean readOnly) {
		this.clickeable = readOnly;
	}

	/**
	 * Getter for label
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Setter for label
	 * 
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Getter for socialViewEnum
	 * 
	 * @return the socialViewEnum
	 */
	public DDFViewEnum getSocialViewEnum() {
		return socialViewEnum;
	}

	/**
	 * Setter for socialViewEnum
	 * 
	 * @param socialViewEnum the socialViewEnum to set
	 */
	public void setSocialViewEnum(DDFViewEnum socialViewEnum) {
		this.socialViewEnum = socialViewEnum;
	}

}
