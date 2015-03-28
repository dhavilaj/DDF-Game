package com.ddf.view.components.partner;

import java.io.File;

import com.ddf.view.base.ComponentBase;
import com.ddf.view.components.image.ImageUpload;
import com.ddf.view.components.image.ImageUploadListener;
import com.ddf.view.converters.StringConverter;
import com.ddf.view.i18n.Messages;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;

/**
 * Partner data section component implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class PartnerDataComponentImpl extends
	ComponentBase<PartnerDataComponentListener> implements PartnerDataComponent {

	/* static fields */
	private static final long serialVersionUID = -6588388091465662636L;
	private ImageUpload logoUpload;
	private TextField urlNameField;
	private TextField partnerNameField;

	/* instance variables */

	/* constructors */

	/**
	 * Constructor
	 */
	public PartnerDataComponentImpl() {
		initComponents();
	}

	/**
	 * Initialize components
	 */
	private void initComponents() {

		// partner form
		FormLayout partnerForm = new FormLayout();
		setCompositionRoot(partnerForm);

		// Partner name field
		partnerNameField = new TextField(Messages
			.getString("PartnerDataComponent.CommercialName"));
		registerField(ViewField.PARTNER_NAME.name(), partnerNameField);
		partnerNameField.setConverter(new StringConverter());
		partnerNameField
			.addValueChangeListener(createValueChangeListener(ViewField.PARTNER_NAME));
		partnerForm.addComponent(partnerNameField);

		// URL field
		urlNameField = new TextField(Messages
			.getString("PartnerDataComponent.Url"));
		registerField(ViewField.URL.name(), urlNameField);
		urlNameField.setConverter(new StringConverter());
		urlNameField
			.addValueChangeListener(createValueChangeListener(ViewField.URL));
		partnerForm.addComponent(urlNameField);

		// Logo
		logoUpload = new ImageUpload();
		registerField(ViewField.LOGO_URL.name(), logoUpload.getUrlField());
		partnerForm.addComponent(logoUpload);
		logoUpload.setWidth("200px");
		logoUpload.addViewListener(new ImageUploadListener() {

			@Override
			public void processImageUploadFail(Exception e) {
				showErrorMessage(Messages
					.getString("Common.ImageUploadFail.Message"));
			}

			@Override
			public void processImageUpload(File file, String mimeType,
				String originalName) {
				for (PartnerDataComponentListener l : getViewListeners()) {
					l.processLogoUpload(file, mimeType, originalName);
					l.processDataChange(
						PartnerDataComponent.ViewField.LOGO_URL, file);
				}
			}

		});
	}

	/**
	 * Creates a value change listener for data components
	 * 
	 * @param field Field that has changed
	 * @param url
	 * @return
	 */
	@SuppressWarnings("serial")
	private ValueChangeListener createValueChangeListener(final ViewField field) {
		return new ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				for (PartnerDataComponentListener l : getViewListeners()) {
					l.processDataChange(field, event.getProperty().getValue());
				}
			}
		};
	}

	/* Methods */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.AbstractComponent#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		urlNameField.setReadOnly(readOnly);
		logoUpload.setReadOnly(readOnly);
		partnerNameField.setReadOnly(readOnly);
	}

	/**
	 * Validate all fields
	 * 
	 * @param showMessages true if must show errors as notifications
	 * @return true if all fields are valid
	 */
	public boolean validateAllFields(boolean showMessages) {
		return validateFields(showMessages, ViewField.PARTNER_NAME.name(),
			ViewField.URL.name());
	}
	/* Getters & Setters */
}
