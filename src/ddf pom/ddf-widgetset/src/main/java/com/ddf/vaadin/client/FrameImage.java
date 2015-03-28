package com.ddf.vaadin.client;

import com.google.gwt.dom.client.ImageElement;
import com.google.gwt.event.dom.client.LoadEvent;
import com.google.gwt.event.dom.client.LoadHandler;
import com.google.gwt.user.client.ui.Image;

public class FrameImage {
	private ImageElement imageElement;
	private Image image;
	private boolean loadedRequestedFlag;
	private boolean loadedFlag;
	private final String imageUrl;
	private boolean addedToRootFlag;

	public FrameImage(String imageUrl) {
		this.imageUrl = imageUrl;
		this.loadedRequestedFlag = false;
		this.loadedFlag = false;
		this.addedToRootFlag = false;
	}

	/**
	 * @return the imageElement
	 */
	public ImageElement getImageElement() {
		if (loadedFlag) {
			return imageElement;
		}
		if (loadedRequestedFlag) {
			// nothing to do, just wait
			return null;
		}
		loadImage();
		return null;
	}

	/**
	 * Loads the image
	 */
	private void loadImage() {
		image = new Image(imageUrl);

		// Adds a load handler to get the image element
		image.addLoadHandler(new LoadHandler() {

			@Override
			public void onLoad(LoadEvent event) {
				imageElement = (ImageElement) image.getElement().cast();
				loadedFlag = true;
			}
		});
	}

	public Image getImage() {
		if (image == null) {
			loadImage();
		}
		return image;
	}

	/**
	 * @return the imageUrl
	 */
	public String getImageUrl() {
		return imageUrl;
	}

	/**
	 * @return the loadedFlag
	 */
	public boolean isLoadedFlag() {
		return loadedFlag;
	}

	/**
	 * @return the addedToRootFlag
	 */
	public boolean isAddedToRootFlag() {
		return addedToRootFlag;
	}

	/**
	 * @return the loadedRequestedFlag
	 */
	public boolean isLoadedRequestedFlag() {
		return loadedRequestedFlag;
	}

	/**
	 * @param addedToRootFlag
	 *            the addedToRootFlag to set
	 */
	public void setAddedToRootFlag(boolean addedToRootFlag) {
		this.addedToRootFlag = addedToRootFlag;
	}

}
