package com.ddf.view.components.operation;

import java.io.File;
import java.util.Collection;

import com.ddf.view.base.ComboBoxItemConverter;
import com.ddf.view.base.ComponentBase;
import com.ddf.view.components.image.ImageUpload;
import com.ddf.view.components.image.ImageUploadListener;
import com.ddf.view.converters.StringConverter;
import com.ddf.view.i18n.Messages;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.themes.BaseTheme;

/**
 * @author David Mantilla
 * @since 1.7
 */
public class OperationDataComponentImpl extends
	ComponentBase<OperationDataComponentListener> implements
	OperationDataComponent {

	/* static fields */
	private static final long serialVersionUID = 2801692616320079794L;

	/* instance variables */
	private TextField companyNameField;
	private TextField addressLine1Field;
	private TextField addressLine2Field;
	private TextField cityField;
	private TextField stateField;
	private ComboBox countryComboBox;
	private ComboBox timeZoneComboBox;
	private ComboBox billingContactComboBox;
	private ComboBox mngContactComboBox;
	private ComboBox itContactComboBox;
	private ImageUpload logoUpload;

	private FormLayout operationForm;

	private Button manageUsersLink;

	/* constructors */

	/**
	 * Constructor
	 */
	public OperationDataComponentImpl() {
		initComponents();
	}

	/**
	 * Initialize components
	 */
	@SuppressWarnings("serial")
	private void initComponents() {
		operationForm = new FormLayout();
		setCompositionRoot(operationForm);

		// Company name field
		companyNameField = new TextField(Messages
			.getString("OperationDataComponent.CompanyName"));
		registerField(ViewField.COMPANY_NAME.name(), companyNameField);
		operationForm.addComponent(companyNameField);
		companyNameField.setConverter(new StringConverter());
		companyNameField
			.addValueChangeListener(createValueChangeListener(ViewField.COMPANY_NAME));

		// Address line 1 field
		addressLine1Field = new TextField(Messages
			.getString("OperationDataComponent.AddressLine1"));
		registerField(ViewField.ADDRESS_LINE1.name(), addressLine1Field);
		operationForm.addComponent(addressLine1Field);
		addressLine1Field.setConverter(new StringConverter());
		addressLine1Field
			.addValueChangeListener(createValueChangeListener(ViewField.ADDRESS_LINE1));

		// Address line 2 field
		addressLine2Field = new TextField(Messages
			.getString("OperationDataComponent.AddressLine2"));
		registerField(ViewField.ADDRESS_LINE2.name(), addressLine2Field);
		operationForm.addComponent(addressLine2Field);
		addressLine2Field.setConverter(new StringConverter());
		addressLine2Field
			.addValueChangeListener(createValueChangeListener(ViewField.ADDRESS_LINE2));

		// City field
		cityField = new TextField(Messages
			.getString("OperationDataComponent.City"));
		registerField(ViewField.CITY.name(), cityField);
		operationForm.addComponent(cityField);
		cityField.setConverter(new StringConverter());
		cityField
			.addValueChangeListener(createValueChangeListener(ViewField.CITY));

		// State field
		stateField = new TextField(Messages
			.getString("OperationDataComponent.State"));
		registerField(ViewField.STATE.name(), stateField);
		operationForm.addComponent(stateField);
		stateField.setConverter(new StringConverter());
		stateField
			.addValueChangeListener(createValueChangeListener(ViewField.STATE));

		// country
		countryComboBox = new ComboBox(Messages
			.getString("OperationDataComponent.Country"));
		registerField(ViewField.COUNTRY.name(), countryComboBox);
		operationForm.addComponent(countryComboBox);
		countryComboBox
			.addValueChangeListener(createValueChangeListener(ViewField.COUNTRY));

		// time zone
		timeZoneComboBox = new ComboBox(Messages
			.getString("OperationDataComponent.TimeZone"));
		registerField(ViewField.TIME_ZONE.name(), timeZoneComboBox);
		operationForm.addComponent(timeZoneComboBox);
		timeZoneComboBox
			.addValueChangeListener(createValueChangeListener(ViewField.TIME_ZONE));

		// Billing contact
		billingContactComboBox = new ComboBox(Messages
			.getString("OperationDataComponent.BillingContact"));
		registerField(ViewField.BILLING_CONTACT.name(), billingContactComboBox);
		operationForm.addComponent(billingContactComboBox);
		billingContactComboBox
			.addValueChangeListener(createValueChangeListener(ViewField.BILLING_CONTACT));

		// IT contact
		itContactComboBox = new ComboBox(Messages
			.getString("OperationDataComponent.ITContact"));
		registerField(ViewField.IT_ACCOUNT.name(), itContactComboBox);
		operationForm.addComponent(itContactComboBox);
		itContactComboBox
			.addValueChangeListener(createValueChangeListener(ViewField.IT_ACCOUNT));

		// Management contact
		mngContactComboBox = new ComboBox(Messages
			.getString("OperationDataComponent.MngContact"));
		registerField(ViewField.MANAGMENT_CONTACT.name(), mngContactComboBox);
		operationForm.addComponent(mngContactComboBox);
		mngContactComboBox
			.addValueChangeListener(createValueChangeListener(ViewField.MANAGMENT_CONTACT));

		// logo
		logoUpload = new ImageUpload();
		registerField(ViewField.LOGO_URL.name(), logoUpload.getUrlField());
		operationForm.addComponent(logoUpload);
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
				for (OperationDataComponentListener l : getViewListeners()) {
					l.processLogoUpload(file, mimeType, originalName);
					l.processDataChange(ViewField.LOGO_URL, file);
				}
			}
		});

		// manage users link
		manageUsersLink = new Button(Messages
			.getString("OperationDataComponent.ManageUsers"));
		manageUsersLink.setStyleName(BaseTheme.BUTTON_LINK);
		manageUsersLink.addClickListener(new Button.ClickListener() {

			@Override
			public void buttonClick(ClickEvent event) {
				for (OperationDataComponentListener l : getViewListeners()) {
					l.processManageUsersClick();
				}
			}
		});
		operationForm.addComponent(manageUsersLink);
	}

	/* Methods */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setCountryMenuList(java.util.Collection)
	 */
	@Override
	public <Y> void setCountryMenuList(Collection<Y> countryList,
		ComboBoxItemConverter<Y> converter) {
		setComboBoxItems(countryComboBox, countryList, converter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setTimeZoneMenuList(java.util.Collection)
	 */
	@Override
	public <Y> void setTimeZoneMenuList(Collection<Y> timeZoneList,
		ComboBoxItemConverter<Y> converter) {
		setComboBoxItems(timeZoneComboBox, timeZoneList, converter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setBillingContactMenuList(java.util.Collection)
	 */
	@Override
	public <Y> void setBillingContactMenuList(Collection<Y> billingContactList,
		ComboBoxItemConverter<Y> converter) {
		setComboBoxItems(billingContactComboBox, billingContactList, converter);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setMngContactListMenuList(java.util.Collection)
	 */
	@Override
	public <Y> void setMngContactListMenuList(Collection<Y> mngContactList,
		ComboBoxItemConverter<Y> converter) {
		setComboBoxItems(mngContactComboBox, mngContactList, converter);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setITConcatMenuList(java.util.Collection)
	 */
	@Override
	public <Y> void setITConcatMenuList(Collection<Y> itContactList,
		ComboBoxItemConverter<Y> converter) {
		setComboBoxItems(itContactComboBox, itContactList, converter);
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.view.components.operation.OperationDataComponent#
	 * setManageUsersLinkVisible(boolean)
	 */
	@Override
	public void setManageUsersLinkVisible(boolean visible) {
		manageUsersLink.setVisible(visible);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.vaadin.ui.AbstractComponent#setReadOnly(boolean)
	 */
	@Override
	public void setReadOnly(boolean readOnly) {
		companyNameField.setReadOnly(readOnly);
		addressLine1Field.setReadOnly(readOnly);
		addressLine2Field.setReadOnly(readOnly);
		cityField.setReadOnly(readOnly);
		stateField.setReadOnly(readOnly);
		countryComboBox.setReadOnly(readOnly);
		timeZoneComboBox.setReadOnly(readOnly);
		billingContactComboBox.setReadOnly(readOnly);
		mngContactComboBox.setReadOnly(readOnly);
		itContactComboBox.setReadOnly(readOnly);
		logoUpload.setReadOnly(readOnly);
	}

	/* methods */

	/**
	 * Validates all fields
	 * 
	 * @param showMessages true if want to show error messages
	 * @return true if all fields are valid
	 */
	public boolean validateAllFields(boolean showMessages) {
		return validateFields(showMessages, ViewField.COMPANY_NAME.name(),
			ViewField.COMPANY_NAME.name(), ViewField.ADDRESS_LINE1.name(),
			ViewField.ADDRESS_LINE2.name(), ViewField.CITY.name(),
			ViewField.STATE.name(), ViewField.COUNTRY.name(),
			ViewField.TIME_ZONE.name(), ViewField.BILLING_CONTACT.name(),
			ViewField.IT_ACCOUNT.name(), ViewField.MANAGMENT_CONTACT.name());
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
				for (OperationDataComponentListener l : getViewListeners()) {
					l.processDataChange(field, event.getProperty().getValue());
				}
			}
		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.components.operation.OperationDataComponent#setLogo
	 * (java.lang.String)
	 */
	@Override
	public void setLogo(String url) {
		logoUpload.setImage(url);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.view.components.operation.OperationDataComponent#setLogo
	 * (java.io.File)
	 */
	@Override
	public void setLogo(File file) {
		logoUpload.setImage(file);
	}

	/* Getters & Setters */
}
