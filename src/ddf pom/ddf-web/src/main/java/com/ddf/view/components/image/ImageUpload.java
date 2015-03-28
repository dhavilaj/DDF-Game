package com.ddf.view.components.image;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.ddf.view.base.ComponentBase;
import com.ddf.view.i18n.Messages;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.ProgressBar;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.FailedEvent;
import com.vaadin.ui.Upload.FailedListener;
import com.vaadin.ui.Upload.FinishedEvent;
import com.vaadin.ui.Upload.FinishedListener;
import com.vaadin.ui.Upload.ProgressListener;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.StartedEvent;
import com.vaadin.ui.Upload.StartedListener;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

/**
 * This component group a file uploader and an image preview in a single
 * component with some few advantages: The initial image can be set through the
 * urlField as an external URL image. The new image loaded is not set directly
 * to the field but a listener is called to presenter do what it wants.
 * 
 * @author David Mantilla
 * @since 1.7
 */
@SuppressWarnings("serial")
public class ImageUpload extends ComponentBase<ImageUploadListener> {

	private static final long serialVersionUID = -2550551960636214760L;

	/* static fields */

	/**
	 * Implement both receiver that saves upload in a file and listener for
	 * successful upload
	 */
	class ImageUploadingListener implements Receiver, SucceededListener,
		StartedListener, ProgressListener, FailedListener, FinishedListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.vaadin.ui.Upload.Receiver#receiveUpload(java.lang.String,
		 * java.lang.String)
		 */
		public OutputStream receiveUpload(String filename, String mimeType) {
			// Create upload stream
			FileOutputStream fos = null; // Output stream to write to
			try {
				// Open the file for writing
				file = File.createTempFile(filename, ".tmp");
				originalName = filename;
				ImageUpload.this.mimeType = mimeType;
				fos = new FileOutputStream(file);
			} catch (IOException e) {
				for (ImageUploadListener l : getViewListeners()) {
					l.processImageUploadFail(e);
				}
				return null;
			}
			return fos; // Return the output stream to write to
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.ui.Upload.SucceededListener#uploadSucceeded(com.vaadin
		 * .ui.Upload.SucceededEvent)
		 */
		public void uploadSucceeded(SucceededEvent event) {
			// Show the uploaded file in the image viewer
			image.setSource(new FileResource(file));

			status.setValue("");
			upload.setButtonCaption(Messages.getString("ImageUpload.Change"));

			for (ImageUploadListener l : getViewListeners()) {
				l.processImageUpload(file, mimeType, originalName);
			}

		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.ui.Upload.StartedListener#uploadStarted(com.vaadin.ui.
		 * Upload.StartedEvent)
		 */
		@Override
		public void uploadStarted(StartedEvent event) {
			// This method gets called immediately after upload is started
			upload.setVisible(false);
			progressLayout.setVisible(true);
			pi.setValue(0f);
			// pi.setPollingInterval(500);
			status.setValue(Messages.getString("ImageUpload.Uploading.Message")
				+ "... \"" + event.getFilename() + "\"");
			oldInterval = UI.getCurrent().getPollInterval();
			UI.getCurrent().setPollInterval(500);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.vaadin.ui.Upload.ProgressListener#updateProgress(long, long)
		 */
		public void updateProgress(long readBytes, long contentLength) {
			// This method gets called several times during the update
			pi.setValue(new Float(readBytes / (float) contentLength));
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.vaadin.ui.Upload.FailedListener#uploadFailed(com.vaadin.ui.Upload
		 * .FailedEvent)
		 */
		@Override
		public void uploadFailed(FailedEvent event) {
			// This method gets called when the upload failed
			status.setValue(Messages.getString("ImageUpload.Failed.Message"));
			for (ImageUploadListener l : getViewListeners()) {
				l.processImageUploadFail(event.getReason());
			}
			UI.getCurrent().setPollInterval(oldInterval);
		}

		@Override
		public void uploadFinished(FinishedEvent event) {
			// This method gets called always when the upload finished,
			// either succeeding or failing
			progressLayout.setVisible(false);
			upload.setVisible(true);
			UI.getCurrent().setPollInterval(oldInterval);
		}
	}

	/* instance variables */
	private File file;
	private Image image;
	private TextField urlField;
	private Upload upload;
	private AbstractOrderedLayout progressLayout;
	private ProgressBar pi;
	private Label status;
	private int oldInterval;
	private String mimeType;
	private String originalName;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public ImageUpload() {
		initComponents();
	}

	/**
	 * Create all components
	 */
	private void initComponents() {
		// component container
		VerticalLayout container = new VerticalLayout();
		container.setStyleName("image-upload-wrapper");
		this.setCompositionRoot(container);

		// image with default
		image = new Image();
		image.setStyleName("image-upload-preview");
		container.addComponent(image);

		// status
		status = new Label();
		container.addComponent(status);

		// Progress bar
		pi = new ProgressBar();
		progressLayout = new HorizontalLayout();
		container.addComponent(progressLayout);

		progressLayout.setSpacing(true);
		progressLayout.setVisible(false);
		progressLayout.addComponent(pi);
		progressLayout.setComponentAlignment(pi, Alignment.MIDDLE_LEFT);

		Button cancelProcessing = new Button("Cancel");
		cancelProcessing.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				upload.interruptUpload();
			}
		});
		cancelProcessing.setStyleName("small");
		progressLayout.addComponent(cancelProcessing);

		// URL text-field (hidden)
		urlField = new TextField();
		urlField.addValueChangeListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				// update image
				file = null;
				if (event.getProperty().getValue() != null) {
					image.setSource(new ExternalResource(event.getProperty()
						.getValue().toString()));
					upload.setButtonCaption(Messages
						.getString("ImageUpload.Change"));
				} else {
					clearImage();
				}
			}
		});
		container.addComponent(urlField);
		urlField.setReadOnly(true);
		urlField.setVisible(false);

		upload = new Upload();
		upload.setImmediate(true);
		upload.setButtonCaption(Messages.getString("ImageUpload.Upload"));
		container.addComponent(upload);

		// Image listener
		ImageUploadingListener uploader = new ImageUploadingListener();
		upload.setReceiver(uploader);
		upload.addSucceededListener(uploader);
		upload.addStartedListener(uploader);
		upload.addFinishedListener(uploader);
		upload.addProgressListener(uploader);
		upload.addFailedListener(uploader);

		// Starts with image cleared
		clearImage();
	}

	/* Methods */

	/* Getters & Setters */

	/**
	 * @return The text field to bind the component
	 */
	public TextField getUrlField() {
		return urlField;
	}

	/**
	 * Clears image
	 */
	private void clearImage() {
		file = null;
		image.setSource(new ThemeResource("img/icon-image-32.png"));
		upload.setButtonCaption(Messages.getString("ImageUpload.Upload"));
		mimeType = null;
		originalName = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.AbstractComponent#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		upload.setVisible(!readOnly);
	}

	/**
	 * Set a image from file
	 * 
	 * @param imageFile
	 */
	public void setImage(File imageFile) {
		if (imageFile == null) {
			clearImage();
		} else {
			file = imageFile;
			image.setSource(new FileResource(file));
			originalName = imageFile.getName();
			upload.setButtonCaption(Messages.getString("ImageUpload.Change"));
		}
	}

	/**
	 * Set Image by URL
	 * 
	 * @param imageUrl
	 */
	public void setImage(String imageUrl) {
		if (imageUrl == null) {
			clearImage();
		} else {
			image.setSource(new ExternalResource(imageUrl));
			upload.setButtonCaption(Messages.getString("ImageUpload.Change"));
		}
	}
}
