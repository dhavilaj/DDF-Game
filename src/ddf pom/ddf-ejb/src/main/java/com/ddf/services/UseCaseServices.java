package com.ddf.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ddf.services.local.PersistenceServicesLocal;
import com.ddf.services.local.UseCaseServicesLocal;

/**
 * UseCaseServices implementation
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
@Stateless
public class UseCaseServices implements UseCaseServicesLocal {

	@EJB
	private PersistenceServicesLocal persistenceServices;

	/**
	 * Default constructor.
	 */
	public UseCaseServices() {
	}



}
