package com.ddf.view.components.partner;

import java.io.File;

import com.ddf.view.base.DDFViewListener;

/**
 * Listener for the partnerDataComponent
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface PartnerDataComponentListener extends DDFViewListener {
	/* static fields */

	/* Methods */

	/**
	 * Process the event of an uploaded file
	 * 
	 * @param file
	 * @param mimeType
	 * @param originalName
	 */
	public void processLogoUpload(File file, String mimeType,
		String originalName);

	/**
	 * Process the event of any data change
	 * 
	 * @param field
	 * @param value
	 */
	public void processDataChange(PartnerDataComponent.ViewField field,
		Object value);

	/* Getters & Setters */
}
