package com.ddf.vaadin.client;

/**
 * Frame of animation
 * 
 * @author davidmantilla
 *
 */
public class Frame {

	private final int stepDuration;
	private final FrameImage frameImage;
	private int offSetX;
	private int offSetY;

	/**
	 * Constructor
	 * 
	 * @param imageUrl
	 * @param stepDuration
	 */
	public Frame(String imageUrl, int stepDuration) {
		super();
		this.stepDuration = stepDuration;
		this.frameImage = new FrameImage(imageUrl);
	}

	/**
	 * Constructor
	 * 
	 * @param imageUrl
	 * @param stepDuration
	 */
	public Frame(FrameImage frameImage, int stepDuration) {
		super();
		this.stepDuration = stepDuration;
		this.frameImage = frameImage;
	}

	/**
	 * @return the stepDuration
	 */
	public int getStepDuration() {
		return stepDuration;
	}

	/**
	 * @return the offSetX
	 */
	public int getOffSetX() {
		return offSetX;
	}

	/**
	 * @param offSetX
	 *            the offSetX to set
	 */
	public void setOffSetX(int offSetX) {
		this.offSetX = offSetX;
	}

	/**
	 * @return the offSetY
	 */
	public int getOffSetY() {
		return offSetY;
	}

	/**
	 * @param offSetY
	 *            the offSetY to set
	 */
	public void setOffSetY(int offSetY) {
		this.offSetY = offSetY;
	}

	/**
	 * @return the frameImage
	 */
	public FrameImage getFrameImage() {
		return frameImage;
	}
}
