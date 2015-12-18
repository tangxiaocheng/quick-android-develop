package com.tangdengcheng.www.util;

public class TextUtils {

	/**
	 * @param name
	 *            the string be judged
	 * @return true if the sting is null or space ,otherwise false
	 */
	public static boolean isEmpty(String name) {
		if (null == name ||"".equals(name.trim())) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String string) {
		return null!=string&&!"".equals(string);
	}

}
