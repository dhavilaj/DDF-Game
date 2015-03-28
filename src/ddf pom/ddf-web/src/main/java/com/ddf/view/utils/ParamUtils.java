package com.ddf.view.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author David Mantilla
 *
 * @since 1.7
 */
public class ParamUtils {

	/* static fields */

	/**
	 * Convert a URI parameters fragment to a map
	 * 
	 * @param uriParams
	 * @return the created map
	 */
	public static Map<String, String> uriParamsToMap(String uriParams) {
		String[] values = uriParams.split("/");
		Map<String, String> maps = new HashMap<>();
		for (int i = 0; i < (values.length / 2); i++) {
			maps.put(values[i * 2], values[i * 2 + 1]);
		}
		return maps;
	}

	/**
	 * Converts a parameters map into a fragment URI
	 * 
	 * @param params
	 * 
	 * @return the parameters as String URI fragment
	 */
	public static String paramsMapToUri(Map<String, String> params) {
		StringBuilder uriFragment = new StringBuilder();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				uriFragment.append("/").append(entry.getKey()).append("/")
					.append(entry.getValue());
			}
		}
		return uriFragment.toString();
	}

	/* instance variables */

	/* constructors */

	/* Methods */

	/* Getters & Setters */
}
