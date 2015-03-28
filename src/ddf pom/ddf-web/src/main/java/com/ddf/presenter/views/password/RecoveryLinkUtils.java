package com.ddf.presenter.views.password;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.ddf.commons.codec.CodecUtils;
import com.ddf.model.controlaccess.DDFViewEnum;

/**
 * Some utilities of password recovery link building process
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class RecoveryLinkUtils {

	/* static fields */

	/* instance variables */
	/**
	 * Format to build the recovery URL time
	 */
	private static final String RECOVERY_URL_TIME_FORMAT = "ddMMyyyyHHmmss";
	/**
	 * Token suffix to build the signature
	 */
	public static final String TOKEN_KEY = "%rHBBKDw&D9dCZxQyZ>Am(mJ7$wu!c::Ny4N}vR%ySwN5@x*9K3D4Z;`rHF[Xd/!/s3h+gz8yt]{Xwxg?!5TNmn<+EVrd}4Um9m+5~jTZ_2/g;b~\"kwaekm8K}q_-D+z";

	/* constructors */

	/* Methods */

	/**
	 * Builds the URL to recovery password
	 * 
	 * @param email of the account
	 * @return The built URL
	 */
	public static String buildUrl(String email) {
		Date tokenTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(
			RECOVERY_URL_TIME_FORMAT);

		Map<String, String> params = new HashMap<String, String>();
		params.put(PassRecoveryPresenter.PARAM_NAME_TOKEN, generateToken(
			dateFormat.format(tokenTime), email));
		params.put(PassRecoveryPresenter.PARAM_NAME_TOKEN_TIME, dateFormat
			.format(tokenTime));
		try {
			params.put(PassRecoveryPresenter.PARAM_NAME_EMAIL, URLEncoder
				.encode(email, "utf-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e); // UTF-8 is not going to generates
											// this exception ever
		}
		return DDFViewEnum.PASSWORD_RECOVERY.getAbsolutePath(params);
	}

	/**
	 * Creates a signature of the time and email to make impossible to fake the
	 * URL
	 * 
	 * @param tokenTime
	 * @param email
	 * @return the generated token
	 */
	public static String generateToken(String tokenTime, String email) {
		return CodecUtils.md5(tokenTime + email + TOKEN_KEY);
	}

	/**
	 * Validates a token with now time
	 * 
	 * @param token
	 * @param tokenTime
	 * @param email
	 * @return true if the token is right
	 */
	public static boolean validateToken(String token, String tokenTime,
		String email) {
		return validateToken(token, tokenTime, email, new Date());
	}

	/**
	 * Validates a token relative to validationDate
	 * 
	 * @param token
	 * @param tokenTime
	 * @param email
	 * @param validationDate
	 * @return true if the token is right
	 */
	public static boolean validateToken(String token, String tokenTime,
		String email, Date validationDate) {
		String token2 = generateToken(tokenTime, email);

		// validates signature
		if (!token2.equals(token)) {
			return false;
		}

		// validates time elapsed
		SimpleDateFormat dateFormat = new SimpleDateFormat(
			RECOVERY_URL_TIME_FORMAT);
		Date tokenTimeDate;
		try {
			tokenTimeDate = dateFormat.parse(tokenTime);
		} catch (ParseException e) {
			return false;
		}
		if ((validationDate.getTime() - tokenTimeDate.getTime()) > (24 * 60 * 60 * 1000)) {
			return false;
		}

		return true;
	}
	/* Getters & Setters */
}
