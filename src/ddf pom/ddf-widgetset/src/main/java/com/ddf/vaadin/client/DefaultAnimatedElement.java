package com.ddf.vaadin.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Default animated element
 * 
 * @author David Mantilla
 *
 */
public class DefaultAnimatedElement implements AnimatedElement {

	private int step;
	private List<Frame> frames;
	private int x;
	private int y;
	private int currentFrameIndex;
	private int totalSteps;
	private int currentFrameLife;
	private List<AnimatedElement> children;
	private boolean visible;

	/**
	 * Default constructor
	 */
	public DefaultAnimatedElement() {
		initialize();
	}

	/**
	 * Constructor with frames
	 * 
	 * @param frames
	 */
	public DefaultAnimatedElement(Frame... frames) {
		initialize();
		for (Frame f : frames) {
			addFrame(f);
		}
	}

	/**
	 * Initialize varaibles
	 */
	private void initialize() {
		this.frames = new ArrayList<>();
		this.step = 0;
		this.currentFrameLife = 0;
		this.x = 0;
		this.y = 0;
		this.children = new ArrayList<>();
		this.visible = true;
	}

	/**
	 * Adds a frame
	 * 
	 * @param frame
	 */
	public void addFrame(Frame frame) {
		frames.add(frame);
		recalcMaxSteps();
		restart();
	}

	private void changeCurrentFrame(int frameIndex) {
		currentFrameIndex = frameIndex;
		currentFrameLife = 0;
	}

	/**
	 * Recalculate max steps
	 */
	private void recalcMaxSteps() {
		totalSteps = 0;
		for (Frame f : frames) {
			totalSteps = totalSteps + f.getStepDuration();
		}
	}

	/**
	 * Adds a frame
	 * 
	 * @param imageUrl
	 * @param frameDuration
	 */
	public void addFrame(String imageUrl, int stepDuration) {
		frames.add(new Frame(imageUrl, stepDuration));
		recalcMaxSteps();
		changeCurrentFrame(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.widgetset.animation.AnimatedElement#goNext()
	 */
	@Override
	public void goNext() {
		step++;
		currentFrameLife++;
		if (step >= totalSteps) {
			restart();
		} else if (currentFrameLife > frames.get(currentFrameIndex)
				.getStepDuration()) {
			if (currentFrameIndex + 1 >= frames.size()) {
				restart();
			} else {
				changeCurrentFrame(++currentFrameIndex);
			}
		}
		for (AnimatedElement a : children) {
			a.goNext();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.widgetset.animation.AnimatedElement#restart()
	 */
	@Override
	public void restart() {
		step = 0;
		changeCurrentFrame(0);

		for (AnimatedElement a : children) {
			a.restart();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.widgetset.animation.AnimatedElement#getX()
	 */
	@Override
	public int getX() {
		return x;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.widgetset.animation.AnimatedElement#getY()
	 */
	@Override
	public int getY() {
		return y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.widgetset.animation.AnimatedElement#getCurrentFrame()
	 */
	@Override
	public Frame getCurrentFrame() {
		return frames.get(currentFrameIndex);
	}

	/**
	 * Clear all frames
	 */
	public void clearFrames() {
		frames.clear();
		step = 0;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.vaadin.client.AnimatedElement#draw(com.google.gwt.user.client
	 * .ui.RootPanel, com.google.gwt.canvas.dom.client.Context2d)
	 */
	@Override
	public void draw(RootPanel rootPanel, Context2d context2d) {
		// TODO Auto-generated method stub
		if (!isVisible()) {
			return;
		}
		
		Frame frame = getCurrentFrame();
		if (!frame.getFrameImage().isAddedToRootFlag()) {
			// add image to root to make it to be loaded
			Image image = frame.getFrameImage().getImage();
			image.setVisible(false);
			rootPanel.add(image);
			frame.getFrameImage().setAddedToRootFlag(true);
		}
		if (frame.getFrameImage().isLoadedFlag()) {
			context2d.drawImage(frame.getFrameImage().getImageElement(),
					getX() + frame.getOffSetX(), getY() + frame.getOffSetY());
		}

		// draw children
		for (AnimatedElement c : children) {
			c.draw(rootPanel, context2d);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.client.AnimatedElement#getChildren()
	 */
	@Override
	public List<AnimatedElement> getChildren() {
		return children;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.vaadin.client.AnimatedElement#isVisible()
	 */
	@Override
	public boolean isVisible() {
		return visible;
	}

	/**
	 * Setter for @param visible
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
