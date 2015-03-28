package com.ddf.view.components.image;

import java.io.File;

import com.ddf.view.base.DDFViewListener;

/**
 * Listener for the image upload events
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface ImageUploadListener extends DDFViewListener {

	/* static fields */

	/* Methods */
	/**
	 * Process the image upload success event
	 * 
	 * @param file
	 * @param mimeType 
	 * @param originalName 
	 */
	public void processImageUpload(File file, String mimeType,
		String originalName);

	/**
	 * Process the image upload fail event
	 * 
	 * @param e
	 */
	public void processImageUploadFail(Exception e);

	/* Getters & Setters */
}
