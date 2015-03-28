package com.ddf.helpers;

import com.ddf.commons.vo.UserVO;
import com.ddf.commons.vo.helpers.GenericVOEntityConverter;
import com.ddf.model.User;

/**
 * This class register all conversion that BeanUtils will use to copy properties
 * between value object and entities and vice-versa
 * 
 * @author David Mantilla
 * @since 1.7
 */
public class ParsersContext {

	/* static fields */
	static {
		// account - account VO converter
		new GenericVOEntityConverter<User, UserVO, UserVOParser>(
				UserVOParser.class, User.class, UserVO.class);
	}

	@SuppressWarnings("javadoc")
	public static final UserVOParser USER_VO_PARSER = new UserVOParser();

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
