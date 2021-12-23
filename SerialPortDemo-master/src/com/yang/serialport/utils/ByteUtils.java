package com.yang.serialport.utils;

import java.nio.ByteBuffer;
import java.util.Locale;

/**
 * Byteת������
 * 
 * @author yangle
 */
public class ByteUtils {

	/**
	 * ʮ�������ַ���תbyte[]
	 * 
	 * @param hex
	 *            ʮ�������ַ���
	 * @return byte[]
	 */
	public static byte[] hexStr2Byte(String hex) {
		if (hex == null) {
			return new byte[] {};
		}

		// ����λ��0
		if (hex.length() % 2 != 0) {
			hex = "0" + hex;
		}

		int length = hex.length();
		ByteBuffer buffer = ByteBuffer.allocate(length / 2);
		for (int i = 0; i < length; i++) {
			String hexStr = hex.charAt(i) + "";
			i++;
			hexStr += hex.charAt(i);
			byte b = (byte) Integer.parseInt(hexStr, 16);
			buffer.put(b);
		}
		return buffer.array();
	}

	/**
	 * byte[]תʮ�������ַ���
	 * 
	 * @param array
	 *            byte[]
	 * @return ʮ�������ַ���
	 */
	public static String byteArrayToHexString(byte[] array) {
		if (array == null) {
			return "";
		}
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < array.length; i++) {
			buffer.append(byteToHex(array[i]));
		}
		return buffer.toString();
	}

	/**
	 * byteתʮ�������ַ�
	 * 
	 * @param b
	 *            byte
	 * @return ʮ�������ַ�
	 */
	public static String byteToHex(byte b) {
		String hex = Integer.toHexString(b & 0xFF);
		if (hex.length() == 1) {
			hex = '0' + hex;
		}
		return hex.toUpperCase(Locale.getDefault());
	}
}