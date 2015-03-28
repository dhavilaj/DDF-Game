package com.ddf.model.controlaccess;


/**
 * Interface for the access validator
 * 
 * @author David Mantilla
 * @since 1.7
 */
public interface AccessValidator {

	/**
	 * Validate if the current session has access to use case. If the user is in
	 * normal mode, then all profiles assigned to the user (in social) are used
	 * to determine if the user has access to the view.
	 * 
	 * But if the user is in supervision mode, then supervised operation's set
	 * of profiles are used to determine if the session has access or not.
	 * 
	 * @param socialViewEnum View for validating access to
	 * @return true if the session has access to the view, false otherwise
	 */
	public boolean validateAccessToView(DDFViewEnum socialViewEnum);

	/**
	 * Validate if the current session has access to use case. If the user is in
	 * normal mode, then all profiles assigned to the user (in social) are used
	 * to determine if the user has access to the view.
	 * 
	 * But if the user is in supervision mode, then supervised operation's set
	 * of profiles are used to determine if the session has access or not.
	 * 
	 * @param useCaseEnum Use case for validation access to
	 * @return true if the session has access to the use case, false otherwise
	 */
	public boolean validateAccessToUseCase(UseCaseEnum useCaseEnum);

	/**
	 * Validate if the current access has access to social, that means the
	 * "Social" product has already been added to its account record
	 * 
	 * @return true if the user does have access to Social application, false
	 *         otherwise
	 */
	public boolean validateAccessToSocial();

	/**
	 * Clear the saved data about accessing. After clearing cache, all access
	 * permissions are going to be evaluate again
	 */
	public void clear();



}
