package com.ddf.model.controlaccess;

import java.util.HashMap;

/**
 * Enumeration for all the social's uses cases
 * 
 * @author David Mantilla
 *
 */
/**
 * @author David Mantilla
 *
 * @since 1.7
 */
@SuppressWarnings("javadoc")
public enum UseCaseEnum {

	/*
	 * Enumeration items can be created with the following query from the social
	 * use_case table :
	 */
	// select string_agg( regexp_replace( upper(u.use_case_name ), '[\s\/]',
	// '_', 'g') || '(' || u.id || ')',',') || ';' AS enumStr from use_case u
	// where app_id = 1

	LOGIN(1),
	REGISTER(2),
	HOME(3),
	SEE_EDIT_MY_ACCOUNT(4),
	SEE_MY_COMPANY(5),
	EDIT_MY_COMPANY(6),
	RECOVERY_MY_PASSWORD(7),
	SEE_ABOUT_SECTION(8),
	SEE_MOBIERA_SECTION(9),
	SEE_PRIVACY_SECTION(10),
	SEE_TERMS_SECTION(11),
	SEE_SOCIAL_HOME(12),
	SEE_APPS(13),
	SEE_USERS(14),
	EDIT_USERS(15),
	CREATE_USERS(16);

	private static HashMap<Long, UseCaseEnum> findMap;
	private long id;

	/**
	 * @param name
	 * @param ordinal
	 */
	private UseCaseEnum(long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	static {
		findMap = new HashMap<Long, UseCaseEnum>();
		for (UseCaseEnum e : UseCaseEnum.values()) {
			findMap.put(e.id, e);
		}
	}

	/**
	 * Finds a use-case enum
	 * 
	 * @param id
	 * @return
	 */
	public static UseCaseEnum findById(Long id) {
		UseCaseEnum e = findMap.get(id);
		if (e == null) {
			throw new IllegalArgumentException(id + "");
		}
		return e;
	}

}
