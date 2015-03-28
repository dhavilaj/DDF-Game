package com.ddf.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.ddf.commons.codec.CodecUtils;
import com.ddf.commons.exceptions.services.InactiveUserException;
import com.ddf.commons.exceptions.services.InvalidEmailException;
import com.ddf.commons.exceptions.services.InvalidIpLoginException;
import com.ddf.commons.exceptions.services.InvalidPasswordLoginException;
import com.ddf.commons.exceptions.services.MailServicesErrorException;
import com.ddf.commons.exceptions.services.ValidationException;
import com.ddf.commons.validation.StandardPropertyValidator;
import com.ddf.commons.vo.AppAccessInfoVO;
import com.ddf.commons.vo.AuthInfoVO;
import com.ddf.commons.vo.CredentialVO;
import com.ddf.commons.vo.QueryResult;
import com.ddf.commons.vo.UseCaseVO;
import com.ddf.commons.vo.UserVO;
import com.ddf.commons.vo.criteria.UserQueryCriteria;
import com.ddf.helpers.ParsersContext;
import com.ddf.helpers.UseCaseVOParser;
import com.ddf.model.UseCase;
import com.ddf.model.User;
import com.ddf.services.base.ServicesBase;
import com.ddf.services.local.MailServicesLocal;
import com.ddf.services.local.MailServicesLocal.BuildInMailType;
import com.ddf.services.local.PersistenceServicesLocal;
import com.ddf.services.local.UserServicesLocal;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices extends ServicesBase implements UserServicesLocal {

	/* static */
	private static final String PASSWORD_TOKEN = "32KPjjB%^%LACQ4V7h36M-6SNeLApZSgCG#4nGJ?SB8NZ$wMypuG6qST7=EYeRp?rCpESjFsCN9j-F4b$YzYK*_FGrMejRJE37WN@XW*5Hc@?TxdKq5U5ZQzfc#gx%_=";
	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(UserServices.class
			.getName());

	/* dependencies */
	@EJB
	private PersistenceServicesLocal persistenceServices;
	@EJB
	private MailServicesLocal mailServices;

	/* constructors */

	/**
	 * Default Constructor
	 */
	public UserServices() {
		super();
	}

	/**
	 * Dependency Constructor
	 * 
	 * @param persistenceServices
	 *            Database persistence Services provider
	 * @param mailServices
	 *            Mail Services provider
	 */
	public UserServices(PersistenceServicesLocal persistenceServices,
			MailServicesLocal mailServices) {
		super();
		this.persistenceServices = persistenceServices;
		this.mailServices = mailServices;
	}

	/* services */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.services.local.UserServicesLocal#authenticate(com
	 * .mobiera.social.commons.vo.CredentialVO)
	 */
	@Override
	public AuthInfoVO authenticate(CredentialVO credentialVO)
			throws InvalidEmailException, InvalidPasswordLoginException,
			InactiveUserException, InvalidIpLoginException {

		// Validates data
		validateBean(credentialVO, CredentialVO.class);

		// Find user by email
		List<User> users = persistenceServices.executeNamedQuery(
				"User.findByEmail", new Object[] { credentialVO.getEmail() },
				User.class);
		if (users.isEmpty()) {
			throw new InvalidEmailException(credentialVO.getEmail());
		}

		// check password
		String inputPassword = encryptPassword(credentialVO.getPassword());
		if (!users.get(0).getPassword().equals(inputPassword)) {
			throw new InvalidPasswordLoginException();
		}

		// check active user
		if (!users.get(0).getActiveFlag()) {
			throw new InactiveUserException();
		}

		// parses user entity

		UserVO userVO = ParsersContext.USER_VO_PARSER.toValueObject(
				users.get(0), UserVO.class);
		AuthInfoVO authInfoVO = new AuthInfoVO();
		authInfoVO.setUser(userVO);

		return authInfoVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.mobiera.social.services.local.UserServicesLocal#getApplicationAccess
	 * (long, long)
	 */
	@Override
	public AppAccessInfoVO getAppAccessInfo(long userId) {

		// finds user
		User user = persistenceServices.findEntity(User.class, userId);
		if (user == null) {
			throw new ValidationException("User with id " + userId
					+ " doesn't exist");
		}

		// // get use-cases
		List<UseCase> useCases = persistenceServices.executeNamedQuery(
				"UseCase.findByUser", new Object[] { userId }, UseCase.class);
		UseCaseVOParser useCaseVOParser = new UseCaseVOParser();
		List<UseCaseVO> useCaseVOs = useCaseVOParser.toValueObjectList(
				useCases, UseCaseVO.class);

		// return result
		AppAccessInfoVO accessInfoVO = new AppAccessInfoVO();
		accessInfoVO.getUseCases().addAll(useCaseVOs);
		return accessInfoVO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.services.local.UserServicesLocal#findUsers(com
	 * .mobiera.social.commons.vo.criteria.UserQueryCriteria)
	 */
	@Override
	public QueryResult<UserVO> findAccounts(UserQueryCriteria criteria) {
		return findByCriteria(criteria, User.class, UserVO.class,
				ParsersContext.USER_VO_PARSER, persistenceServices);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.services.local.UserServicesLocal#
	 * sendRecoveryPasswordEmail(java.lang.String, java.lang.String)
	 */
	@Override
	public void sendRecoveryPasswordEmail(String email, String recoveryLink)
			throws InvalidEmailException, InactiveUserException,
			MailServicesErrorException {

		// set values to validate
		StandardPropertyValidator bean = new StandardPropertyValidator();
		bean.setEmail(email);
		bean.setUrl(recoveryLink);

		// validate properties
		validateProperty(bean, "email", StandardPropertyValidator.class);
		validateProperty(bean, "url", StandardPropertyValidator.class);

		// validate email exists
		List<User> users = persistenceServices.executeNamedQuery(
				"User.findByEmail", new Object[] { email }, User.class);
		if (users.isEmpty()) {
			throw new InvalidEmailException();
		}
		if (!users.get(0).getActiveFlag()) {
			throw new InactiveUserException();
		}

		// sends email
		List<String> emails = new ArrayList<>();
		emails.add(users.get(0).getEmail());
		Map<String, String> params = new HashMap<>();
		params.put("PASSWORD_LINK", recoveryLink);
		mailServices.sendMail(BuildInMailType.PASSWORD_RECOVERY, new Locale(
				users.get(0).getLanguage()), emails, params);
	}

	/* private utilities */

	/**
	 * Encrypt password
	 * 
	 * @param password
	 * @return
	 */
	private String encryptPassword(String password) {
		return CodecUtils.md5(password + PASSWORD_TOKEN);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.services.local.UserServicesLocal#updatePassword
	 * (long, java.lang.String)
	 */
	@Override
	public void updatePassword(long userId, String newPassword)
			throws InactiveUserException {

		// set values to validate
		StandardPropertyValidator bean = new StandardPropertyValidator();
		bean.setPassword(newPassword);
		validateProperty(bean, "password", "newPassword",
				StandardPropertyValidator.class);

		// finds user
		User user = persistenceServices.findEntity(User.class, userId);
		if (user == null) {
			throw new ValidationException("User with id " + userId
					+ " doesn't exist");
		}
		if (!user.getActiveFlag()) {
			throw new InactiveUserException(userId + "");
		}

		// update password
		user.setPassword(encryptPassword(newPassword));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.mobiera.social.services.local.UserServicesLocal#changeUserStatus
	 * (long, boolean)
	 */
	@Override
	public void changeUserStatus(long userId, boolean newActiveFlag) {

		// user must exist
		User user = persistenceServices.findEntity(User.class, userId);
		if (user == null) {
			throw new ValidationException("User with Id " + userId
					+ ", + doesn't exist");
		}

		// change status
		user.setActiveFlag(newActiveFlag);

	}
}
