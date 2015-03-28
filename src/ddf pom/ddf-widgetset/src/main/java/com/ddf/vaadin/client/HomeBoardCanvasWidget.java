/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ddf.vaadin.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.RootPanel;

public class HomeBoardCanvasWidget extends Composite {

	/* static variables */
	private static final int CANVAS_HEIGHT = 583;
	private static final int CANVAS_WIDTH = 800;
	public static final String CLASSNAME = "mycomponent";
	private static final int FRAMERATE = 30;

	/* instance variables */
	private Canvas canvas;
	private List<HomeBoardListener> homeBoardListeners;

	/* state attributes */
	// private EnumMap<ScrumMasterStatus, Integer> notifcationsCountMap;
	private int agilityBarPosition;
	private int healthBarPosition;
	private int proyectBarPosition;
	// @SuppressWarnings("unused")
	// private ScrumMaster scrumMaster;
	private List<AnimatedElement> animatedElements;
	private AnimatedElement background;

	/* constructors */

	/**
	 * Home Board Canvas Widget
	 */
	public HomeBoardCanvasWidget() {

		// initialize variables
		homeBoardListeners = new ArrayList<>();
		animatedElements = new ArrayList<>();
		// notifcationsCountMap = new EnumMap<>(ScrumMasterStatus.class);

		// create widget
		initWidget();

		// initialize the game timer
		initGameTimer();
	}

	/* methods */

	/**
	 * Initialize the widget
	 */
	private void initWidget() {
		canvas = Canvas.createIfSupported();

		// Initialize canvas widget size
		initWidget(canvas);
		canvas.setWidth(CANVAS_WIDTH + "px");
		canvas.setCoordinateSpaceWidth(CANVAS_WIDTH);
		canvas.setHeight(CANVAS_HEIGHT + "px");
		canvas.setCoordinateSpaceHeight(CANVAS_HEIGHT);

		// events
		canvas.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				// TODO: logic to get the position of the click and
				// recognize the clicked element
				for (HomeBoardListener l : getHomeBoardListeners()) {
					l.sectionClick(HomeBoardListener.Section.BURN_DOWN);
				}
			}
		});

		// create animated elements

		// background
		background = new DefaultAnimatedElement(new Frame(
				ImageUrls.HOME_BOARD_BACKGROUND_1, 1000));
		animatedElements.add(background);

		// burn down
		createBurnDown();
		// scrum master
		createScrumMaster();
		// computer
		createComputer();
		// calendar
		createCalendar();
		// canvan
		createCanvan();
		// mobile
		createMobile();
		// papers
		createPapers();
		// phone
		createPhone();
	}

	/**
	 * Create canvan element
	 */
	private void createCanvan() {
		FrameImage step1Img = new FrameImage(ImageUrls.CANVAN_IMAGE_1);
		FrameImage step2Img = new FrameImage(ImageUrls.CANVAN_IMAGE_2);
		FrameImage step3Img = new FrameImage(ImageUrls.CANVAN_IMAGE_3);
		FrameImage step4Img = new FrameImage(ImageUrls.CANVAN_IMAGE_4);
		FrameImage step5Img = new FrameImage(ImageUrls.CANVAN_IMAGE_5);
		FrameImage step6Img = new FrameImage(ImageUrls.CANVAN_IMAGE_6);
		DefaultAnimatedElement canvan = new DefaultAnimatedElement();
		canvan.addFrame(new Frame(step1Img, 4));
		canvan.addFrame(new Frame(step2Img, 4));
		canvan.addFrame(new Frame(step3Img, 4));
		canvan.addFrame(new Frame(step4Img, 4));
		canvan.addFrame(new Frame(step5Img, 4));
		canvan.addFrame(new Frame(step6Img, 4));
		canvan.addFrame(new Frame(step1Img, 30));
		canvan.setX(514);
		canvan.setY(102);
		background.getChildren().add(canvan);
	}

	/**
	 * Create papers
	 */
	private void createPapers() {
		FrameImage step1Img = new FrameImage(ImageUrls.PAPERS_IMAGE_1);
		FrameImage step2Img = new FrameImage(ImageUrls.PAPERS_IMAGE_2);
		FrameImage step3Img = new FrameImage(ImageUrls.PAPERS_IMAGE_3);
		FrameImage step4Img = new FrameImage(ImageUrls.PAPERS_IMAGE_4);
		FrameImage step5Img = new FrameImage(ImageUrls.PAPERS_IMAGE_5);
		DefaultAnimatedElement canvan = new DefaultAnimatedElement();
		canvan.addFrame(new Frame(step1Img, 4));
		canvan.addFrame(new Frame(step2Img, 4));
		canvan.addFrame(new Frame(step3Img, 4));
		canvan.addFrame(new Frame(step4Img, 4));
		canvan.addFrame(new Frame(step5Img, 20));
		canvan.addFrame(new Frame(step4Img, 4));
		canvan.addFrame(new Frame(step3Img, 4));
		canvan.addFrame(new Frame(step2Img, 4));
		canvan.addFrame(new Frame(step1Img, 30));
		canvan.setX(367);
		canvan.setY(480);
		background.getChildren().add(canvan);
	}

	private void createPhone() {
		DefaultAnimatedElement phone = new DefaultAnimatedElement(

		new Frame(ImageUrls.PHONE_IMAGE_1, 30), new Frame(
				ImageUrls.PHONE_IMAGE_2, 3), new Frame(ImageUrls.PHONE_IMAGE_3,
				3), new Frame(ImageUrls.PHONE_IMAGE_4, 3), new Frame(
				ImageUrls.PHONE_IMAGE_5, 3), new Frame(ImageUrls.PHONE_IMAGE_6,
				3), new Frame(ImageUrls.PHONE_IMAGE_7, 3));
		background.getChildren().add(phone);
		phone.setX(592);
		phone.setY(441);

	}

	/**
	 * Create scrum master element
	 */
	private void createScrumMaster() {
		ScrumMaster scrumMaster = new ScrumMaster();
		scrumMaster.setX(300);
		scrumMaster.setY(98);
		background.getChildren().add(scrumMaster);
	}

	private void createComputer() {
		AnimatedElement labTop = new DefaultAnimatedElement(new Frame(
				ImageUrls.LAB_TOP_STEP_1, 40), new Frame(
				ImageUrls.LAB_TOP_STEP_2, 40));
		labTop.setX(240);
		labTop.setY(400);
		background.getChildren().add(labTop);
	}

	private void createCalendar() {
		AnimatedElement calendar = new DefaultAnimatedElement(new Frame(
				ImageUrls.CALENDAR_STEP_1, 40));
		calendar.setX(127);
		calendar.setY(429);
		background.getChildren().add(calendar);
	}

	private void createBurnDown() {
		FrameImage step1Img = new FrameImage(ImageUrls.BURN_DOWN_STEP_1);
		FrameImage step2Img = new FrameImage(ImageUrls.BURN_DOWN_STEP_2);
		FrameImage step3Img = new FrameImage(ImageUrls.BURN_DOWN_STEP_3);

		DefaultAnimatedElement burnDown = new DefaultAnimatedElement();
		Frame frame = new Frame(step2Img, 40);
		burnDown.addFrame(frame);

		for (int i = 0; i < 10; i++) {
			frame = new Frame(step1Img, 2);
			burnDown.addFrame(frame);
			frame = new Frame(step2Img, 2);
			burnDown.addFrame(frame);
			frame = new Frame(step3Img, 2);
			burnDown.addFrame(frame);
			frame = new Frame(step2Img, 2);
			burnDown.addFrame(frame);
		}

		burnDown.setX(0);
		burnDown.setY(73);
		background.getChildren().add(burnDown);
	}

	/**
	 * Create mobile
	 */
	private void createMobile() {
		FrameImage step1Img = new FrameImage(ImageUrls.MOBILE_STEP_1);
		FrameImage step2Img = new FrameImage(ImageUrls.MOBILE_STEP_2);
		FrameImage step3Img = new FrameImage(ImageUrls.MOBILE_STEP_3);

		DefaultAnimatedElement mobile = new DefaultAnimatedElement();
		Frame frame = new Frame(step1Img, 40);
		mobile.addFrame(frame);

		for (int i = 0; i < 10; i++) {
			frame = new Frame(step2Img, 1);
			mobile.addFrame(frame);
			frame = new Frame(step1Img, 1);
			mobile.addFrame(frame);
			frame = new Frame(step3Img, 1);
			mobile.addFrame(frame);
			frame = new Frame(step1Img, 1);
			mobile.addFrame(frame);
		}

		mobile.setX(499);
		mobile.setY(431);
		background.getChildren().add(mobile);
	}

	/**
	 * Initialize the timer
	 */
	private void initGameTimer() {
		Timer timer = new Timer() {
			@Override
			public void run() {
				doUpdateCanvas();
			}
		};

		timer.scheduleRepeating(1000 / FRAMERATE);
	}

	/**
	 * Updates the canvas
	 */
	private void doUpdateCanvas() {
		// clear canvas
		canvas.getContext2d().clearRect(0, 0, canvas.getCoordinateSpaceWidth(),
				canvas.getCoordinateSpaceHeight());

		// paint all animated elements
		for (AnimatedElement element : animatedElements) {
			element.draw(RootPanel.get(), canvas.getContext2d());
			element.goNext();
		}
		
		// draw progress bar
		canvas.getContext2d().setFillStyle("#ffcc00" );
		canvas.getContext2d().fillRect(31, 35, 237, 31);
		canvas.getContext2d().setFillStyle("#d4e175" );
		canvas.getContext2d().fillRect(275, 35, 237, 31);
		canvas.getContext2d().setFillStyle("#8f4665" );
		canvas.getContext2d().fillRect(526, 35, 237, 31);
	}

	/* getters y setters */

	/**
	 * @return
	 */
	public List<HomeBoardListener> getHomeBoardListeners() {
		return homeBoardListeners;
	}

	/**
	 * @return the scrumMasterStatus
	 */
	// public ScrumMasterStatus getScrumMasterStatus() {
	// //return scrumMasterStatus;
	// return ScrumMasterStatus.ANGRY;
	// }

	/**
	 * @param scrumMasterStatus
	 *            the scrumMasterStatus to set
	 */
	// public void setScrumMasterStatus(ScrumMasterStatus scrumMasterStatus) {
	// // this.scrumMasterStatus = scrumMasterStatus;
	// }

	/**
	 * @return the agilityBarPosition
	 */
	public int getAgilityBarPosition() {
		return agilityBarPosition;
	}

	/**
	 * @param agilityBarPosition
	 *            the agilityBarPosition to set
	 */
	public void setAgilityBarPosition(int agilityBarPosition) {
		this.agilityBarPosition = agilityBarPosition;
	}

	/**
	 * @return the healthBarPosition
	 */
	public int getHealthBarPosition() {
		return healthBarPosition;
	}

	/**
	 * @param healthBarPosition
	 *            the healthBarPosition to set
	 */
	public void setHealthBarPosition(int healthBarPosition) {
		this.healthBarPosition = healthBarPosition;
	}

	/**
	 * @return the proyectBarPosition
	 */
	public int getProyectBarPosition() {
		return proyectBarPosition;
	}

	/**
	 * @param proyectBarPosition
	 *            the proyectBarPosition to set
	 */
	public void setProyectBarPosition(int proyectBarPosition) {
		this.proyectBarPosition = proyectBarPosition;
	}

}
