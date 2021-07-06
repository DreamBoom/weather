package yfkj.weatherstation.utils;


 
public class StringUtil {
	/**
	 * @return string
	 */
	public String[] bytesToHexString(byte[] bArray) {
		StringBuilder sb = new StringBuilder(bArray.length);
		for (byte aBArray : bArray) {
			String sTemp = Integer.toHexString(0xFF & aBArray);
			if (sTemp.length() < 2) {
				sb.append(0);
			}
			sb.append(sTemp.toUpperCase()).append(",");
		}
		return sb.toString().split(",");
	}

	public static byte[] toBytes(String str) {
		if(str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for(int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}
  }