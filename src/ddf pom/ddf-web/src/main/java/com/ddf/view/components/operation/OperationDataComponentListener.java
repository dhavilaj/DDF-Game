package com.ddf.view.components.operation;

import java.io.File;

import com.ddf.view.base.DDFViewListener;
import com.ddf.view.components.operation.OperationDataComponent;

/**
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface OperationDataComponentListener extends DDFViewListener {
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
	 * Process the event of clicking the manager-users link
	 */
	public void processManageUsersClick();

	/**
	 * Process the event of any data change
	 * 
	 * @param field
	 * @param value
	 */
	public void processDataChange(OperationDataComponent.ViewField field,
		Object value);

	/* Getters & Setters */
}
