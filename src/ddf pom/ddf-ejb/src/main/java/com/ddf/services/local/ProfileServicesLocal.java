package com.ddf.services.local;

import javax.ejb.Local;

import com.ddf.commons.exceptions.services.ServiceException;
import com.ddf.commons.vo.ProfileVO;

/**
 * Interface for ProfileServices
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
@Local
public interface ProfileServicesLocal {

	/**
	 * Finds a profile by its id
	 * 
	 * @param id Profile's id
	 * @return the founded profile. If no profile is found, then return null
	 * @throws ServiceException
	 */
	public ProfileVO find(long id) throws ServiceException;

}
