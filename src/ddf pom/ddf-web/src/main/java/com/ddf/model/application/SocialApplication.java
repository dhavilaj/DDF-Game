package com.ddf.model.application;

import java.util.List;

import com.ddf.commons.vo.CountryVO;
import com.ddf.commons.vo.LanguageVO;
import com.ddf.commons.vo.TimeZoneVO;

/**
 * Interface for the social application bean
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface SocialApplication {
	/* static fields */

	/* Methods */

	/* Getters & Setters */

	/**
	 * Return the list of countries
	 * 
	 * @return list of countries
	 */
	public List<CountryVO> getCountries();

	/**
	 * Return the list of time zones
	 * 
	 * @return the list of time zones
	 */
	public List<TimeZoneVO> getTimeZones();

	/**
	 * Return the list of languages
	 * 
	 * @return the list of languages
	 */
	public List<LanguageVO> getLanguages();

}
