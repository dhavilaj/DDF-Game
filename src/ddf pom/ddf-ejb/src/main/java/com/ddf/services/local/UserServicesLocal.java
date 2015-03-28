package com.ddf.services.local;

import javax.ejb.Local;

import com.ddf.commons.exceptions.services.InactiveUserException;
import com.ddf.commons.exceptions.services.InvalidEmailException;
import com.ddf.commons.exceptions.services.InvalidIpLoginException;
import com.ddf.commons.exceptions.services.InvalidPasswordLoginException;
import com.ddf.commons.exceptions.services.MailServicesErrorException;
import com.ddf.commons.vo.AppAccessInfoVO;
import com.ddf.commons.vo.AuthInfoVO;
import com.ddf.commons.vo.CredentialVO;
import com.ddf.commons.vo.QueryResult;
import com.ddf.commons.vo.UserVO;
import com.ddf.commons.vo.criteria.UserQueryCriteria;

/**
 * Local interface for AccountServices
 * 
 * @author David Mantilla
 * @since 1.7
 */
@Local
public interface UserServicesLocal {

	/**
	 * @param credentialVO
	 * @return All authentication information
	 * @throws InvalidEmailException
	 *             When the email is not found on the database
	 * @throws InvalidPasswordLoginException
	 *             When the sent password doesn't match user's password
	 * @throws InactiveUserException
	 *             When the user is inactive
	 * @throws InvalidIpLoginException
	 *             When the IP is not allowed either Partner White List or
	 *             Account White List
	 */
	public AuthInfoVO authenticate(CredentialVO credentialVO)
			throws InvalidEmailException, InvalidPasswordLoginException,
			InactiveUserException, InvalidIpLoginException;

	/**
	 * Find accounts by criteria
	 * 
	 * @param criteria
	 * @return the query result
	 */
	public QueryResult<UserVO> findAccounts(UserQueryCriteria criteria);

	/**
	 * Return all the use-cases and operations which a user can access in a
	 * given application
	 * 
	 * @param accountId
	 * @return The loaded access information
	 * 
	 */
	public AppAccessInfoVO getAppAccessInfo(long accountId);

	/**
	 * Sends an email to allow user to recovery his password. The link is given
	 * by the application client
	 * 
	 * @param email
	 *            User's email
	 * @param recoveryLink
	 *            Link to be pasted in the email
	 * 
	 * @throws InvalidEmailException
	 *             when the email is not in database
	 * @throws InactiveUserException
	 *             when the user is inactive
	 * @throws MailServicesErrorException
	 *             when the mail sending fails
	 */
	public void sendRecoveryPasswordEmail(String email, String recoveryLink)
			throws InvalidEmailException, InactiveUserException,
			MailServicesErrorException;

	/**
	 * Update an account password
	 * 
	 * @param accountId
	 *            Id of the account to update password
	 * @param newPassword
	 *            Password to replace old password
	 * @throws InactiveUserException
	 *             When accountId is from an innactive account
	 */
	public void updatePassword(long accountId, String newPassword)
			throws InactiveUserException;

	/**
	 * Allows getting account active or inactive
	 * 
	 * @param accountId
	 *            If of the account to activate/inactivate
	 * @param newActiveFlag
	 *            new ActiveFlag
	 */
	public void changeUserStatus(long accountId, boolean newActiveFlag);
}
