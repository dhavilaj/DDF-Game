package com.ddf.vaadin.client;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ScrumMaster extends DefaultAnimatedElement {

	public enum ScrumMasterStatus {
		CALM(ImageUrls.CALM_IMAGE_STEP_1 + ",70", ImageUrls.CALM_IMAGE_STEP_2
				+ ",10"), WORRY(ImageUrls.WORRY_IMAGE_STEP_1 + ",40",
				ImageUrls.WORRY_IMAGE_STEP_3 + ",8",
				ImageUrls.WORRY_IMAGE_STEP_4 + ",8",
				ImageUrls.WORRY_IMAGE_STEP_2 + ",8"), ANGRY, DEATH;
		private final String[] framesUrls;
		private final int[] frameDuration;

		private ScrumMasterStatus(String... frames) {
			this.framesUrls = new String[frames.length];
			this.frameDuration = new int[frames.length];

			int i = 0;
			for (String s : frames) {
				String[] split = s.split(",");
				framesUrls[i] = split[0];
				frameDuration[i] = Integer.valueOf(split[1]);
				i++;
			}
		}
	}

	private ScrumMasterStatus scrumMasterStatus;
	private Map<ScrumMasterStatus, List<Frame>> statusFrames;

	/* constructors */

	/**
	 * Default constructor
	 */
	public ScrumMaster() {
		createFrames();
		setScrumMasterStatus(ScrumMasterStatus.WORRY);
	}

	/**
	 * Create frames map
	 */
	private void createFrames() {
		statusFrames = new EnumMap<>(ScrumMasterStatus.class);
		for (ScrumMasterStatus status : ScrumMasterStatus.values()) {
			List<Frame> frames = new ArrayList<Frame>();
			for (int i = 0; i < status.framesUrls.length; i++) {
				frames.add(new Frame(status.framesUrls[i],
						status.frameDuration[i]));
			}
			statusFrames.put(status, frames);
		}
	}

	/* methods */

	/* getters y setters */

	/**
	 * Set ScrumMaster status
	 * 
	 * @param scrumMasterStatus
	 */
	public void setScrumMasterStatus(ScrumMasterStatus scrumMasterStatus) {
		if (this.scrumMasterStatus != scrumMasterStatus) {
			this.scrumMasterStatus = scrumMasterStatus;
			clearFrames();
			for (Frame f : statusFrames.get(scrumMasterStatus)) {
				addFrame(f);
			}
		}
	}

}
