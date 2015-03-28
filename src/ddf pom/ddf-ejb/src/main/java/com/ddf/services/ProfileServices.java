package com.ddf.services;

import javax.ejb.Stateless;

import com.ddf.commons.exceptions.services.ServiceException;
import com.ddf.commons.vo.ProfileVO;
import com.ddf.services.local.ProfileServicesLocal;

/**
 * ProfileServices implementation
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Stateless
public class ProfileServices implements ProfileServicesLocal {

	// @EJB
	// private PersistenceServicesLocal persistenceServices;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.ddf.services.local.ProfileServicesLocal#find(long)
	 */
	@Override
	public ProfileVO find(long id) throws ServiceException {
		// Profile profile = persistenceServices.findEntity(Profile.class, id);
		// GenericVOParser<Profile, ProfileVO> voParser = new
		// GenericVOParser<>();
		// return voParser.toValueObject(profile, ProfileVO.class);
		ProfileVO profileVO = new ProfileVO(id, "Profile", "");
		return profileVO;

	}

}
