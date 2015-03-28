package com.ddf.view.components.partner;

import com.ddf.view.base.DDFComponent;

/**
 * Interface for the partner data section component
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PartnerDataComponent extends
	DDFComponent<PartnerDataComponentListener> {

	/* static fields */

	@SuppressWarnings("javadoc")
	public enum ViewField {
		PARTNER_NAME,
		URL,
		LOGO_URL;
	}

	/* Methods */

	/**
	 * Sets the component in read only mode
	 * 
	 * @param readOnly
	 */
	public void setReadOnly(boolean readOnly);

	/* Getters & Setters */
}
