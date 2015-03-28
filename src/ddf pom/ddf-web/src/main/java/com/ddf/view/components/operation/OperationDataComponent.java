package com.ddf.view.components.operation;

import java.io.File;
import java.util.Collection;

import com.ddf.view.base.ComboBoxItemConverter;
import com.ddf.view.base.DDFComponent;

/**
 * Interface for the operation data section component
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface OperationDataComponent extends
	DDFComponent<OperationDataComponentListener> {

	/* static fields */
	@SuppressWarnings("javadoc")
	public enum ViewField {
		COMPANY_NAME,
		ADDRESS_LINE1,
		ADDRESS_LINE2,
		CITY,
		STATE,
		COUNTRY,
		TIME_ZONE,
		BILLING_CONTACT,
		IT_ACCOUNT,
		MANAGMENT_CONTACT,
		LOGO_URL;
	}

	/* Methods */

	/**
	 * Set the country list in the country selection menu
	 * 
	 * @param <Y>
	 * 
	 * @param countryList
	 * @param converter
	 */
	public <Y> void setCountryMenuList(Collection<Y> countryList,
		ComboBoxItemConverter<Y> converter);

	/**
	 * Set the select item in country list
	 * 
	 * @param timeZoneList
	 * @param converter
	 * @param <Y>
	 */
	public <Y> void setTimeZoneMenuList(Collection<Y> timeZoneList,
		ComboBoxItemConverter<Y> converter);

	/**
	 * Set the select item in billing ContactList
	 * 
	 * @param <Y>
	 * 
	 * @param billingContactList
	 * @param converter
	 */
	public <Y> void setBillingContactMenuList(Collection<Y> billingContactList,
		ComboBoxItemConverter<Y> converter);

	/**
	 * Set the select item in management Contact List
	 * 
	 * @param <Y>
	 * 
	 * @param mngContactList
	 * @param converter
	 */
	public <Y> void setMngContactListMenuList(Collection<Y> mngContactList,
		ComboBoxItemConverter<Y> converter);

	/**
	 * Set the select item in country list
	 * 
	 * @param <Y>
	 * 
	 * @param itContactList
	 * @param converter
	 */
	public <Y> void setITConcatMenuList(Collection<Y> itContactList,
		ComboBoxItemConverter<Y> converter);

	/**
	 * Sets the visibility of the manage-users link
	 * 
	 * @param visible
	 */
	public void setManageUsersLinkVisible(boolean visible);

	/**
	 * Sets the form in read only mode
	 * 
	 * @param readOnly
	 */
	public void setReadOnly(boolean readOnly);

	/**
	 * Set the logo image from URL
	 * 
	 * @param url
	 */
	public void setLogo(String url);

	/**
	 * Set the logo image from file
	 * 
	 * @param file
	 */
	public void setLogo(File file);

	/* Getters & Setters */
}
