package com.ddf.model.application;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.ApplicationScoped;

import com.ddf.commons.vo.CountryVO;
import com.ddf.commons.vo.LanguageVO;
import com.ddf.commons.vo.TimeZoneVO;

/**
 * This class represents all the application information
 * 
 * @author David Mantilla
 * @since 1.7
 */
@ManagedBean(value = "socialApplication")
@ApplicationScoped
public class SocialApplicationImpl implements SocialApplication {

	// @Inject
	// private GeneralServicesLocal generalServices;
	private List<LanguageVO> languages;
	private List<TimeZoneVO> timeZones;
	private List<CountryVO> countries;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.model.application.SocialApplication#getCountries()
	 */
	@Override
	public List<CountryVO> getCountries() {
		// if (countries == null) {
		// countries = generalServices.findCountries();
		// }
		return countries;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.model.application.SocialApplication#getTimeZones()
	 */
	@Override
	public List<TimeZoneVO> getTimeZones() {
		// if (timeZones == null) {
		// timeZones = generalServices.findTimeZones();
		// }
		return timeZones;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ddf.model.application.SocialApplication#getLanguages()
	 */
	@Override
	public List<LanguageVO> getLanguages() {
		// if (languages == null) {
		// languages = generalServices.findLanguages();
		// }
		return languages;
	}

}
