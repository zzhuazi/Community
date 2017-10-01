package com.dgut.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @ClassName: MD5Util
 * @Description: md5加密工具类
 * @author 闲明苑
 * @date 2017年9月23日 下午5:14:04
 */
public class MD5Util {

	/**
	 * @Title: encrypt
	 * @Description: 根据string获取加密后的字符串
	 * @return String
	 * @throws
	 */
	public static String encrypt(String string) {
		return new Md5Hash(string).toString();
	}

}
