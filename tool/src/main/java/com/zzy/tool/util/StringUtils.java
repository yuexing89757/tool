package com.zzy.tool.util;


/**
 * 
 * This class delivers some simple functionality for
 * CharSequence(String,StringBuffer,StringBuilder) the class is writting. from
 * spring source
 * 
 * @author hf.fu
 * 
 */
public final class StringUtils {

	public static final String COMMA = ",";
	public static final String VERTICAL = "|";
	/**
	 * set image source separator. from www or local file.
	 */
	public static final String INTERNET_PREFIX_HTTP = "http://";
	/**
	 * set image source separator. from www or local file.
	 */
	public static final String INTERNET_PREFIX_HTTPS = "https://";

	// file attribute constat utility

	public static final String FOLDER_SEPARATOR = "/";

	public static final String WINDOWS_FOLDER_SEPARATOR = "\\";

	public static final String TOP_PATH = "..";

	public static final String CURRENT_PATH = ".";

	public static final char EXTENSION_SEPARATOR = '.';

	public static final String UNDERLINE = "_";

	/**
	 * Check that the given CharSequence is neither null nor of length 0.
	 */
	public static boolean hasLength(CharSequence str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * Check that the given String is neither <code>null</code> nor of length 0.
	 */
//	public static boolean hasLength(String str) {
//		return hasLength((CharSequence) str);
//	}

	/**
	 * Check whether the given CharSequence has actual text.
	 */
	public static boolean hasText(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String has actual text.
	 */
	public static boolean hasText(String str) {
		return hasText((CharSequence) str);
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 */
	public static boolean containsWhitespace(CharSequence str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether the given String contains any whitespace characters.
	 */
	public static boolean containsWhitespace(String str) {
		return containsWhitespace((CharSequence) str);
	}

	/**
	 * Trim all whitespace from the given String: leading, trailing, and
	 * inbetween characters.
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return buf.toString();
	}

	/**
	 * Trim leading whitespace from the given String.
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * Trim trailing whitespace from the given String.
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0
				&& Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * Trim all occurences of the supplied leading character from the given
	 * String.
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && buf.charAt(0) == leadingCharacter) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * Trim all occurences of the supplied trailing character from the given
	 * String.
	 */
	public static String trimTrailingCharacter(String str,
			char trailingCharacter) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0
				&& buf.charAt(buf.length() - 1) == trailingCharacter) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * Test if the given String starts with the specified prefix, ignoring
	 * upper/lower case.
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * Test if the given String ends with the specified suffix, ignoring
	 * upper/lower case.
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length())
				.toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * Test whether the given string matches the given substring at the given
	 * index.
	 */
	public static boolean substringMatch(CharSequence str, int index,
			CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Count the occurrences of the substring in string s
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0
				|| sub.length() == 0) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 
	 * from array to delimited String.
	 */
	public static String arrayByDelimitedString(Object[] arr, String delim) {
		if (ObjectUtils.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * Check whether the given CharSequence contains any whitespace characters.
	 */
//	public static String[] stringByDelimitedArrays(String str,String delim) {
//		if (!hasLength(str)) {
//			return null;
//		}
//		
//		String[] arrays = str.split(delim);
//		int strLen = str.length();
//		for (int i = 0; i < strLen; i++) {
//			if (Character.isWhitespace(str.charAt(i))) {
//				continue;
//			}else{
//				
//			}
//		}
//		return false;
//	}
	/**
	 * 
	 * ArrayModelObjectIdToString (IDArray convert to string(Separated by
	 * commas)
	 * 
	 * TODO(Here describes this method to be suitable the condition - to be
	 * possible to elect)
	 * 
	 * @param <ID>
	 * @param objs
	 * @return
	 * 
	 *         String
	 */
//	public static <ID extends Serializable> String ArrayModelObjectIdToString(
//			ModelObject<ID>[] objs) {
//		if (objs != null && objs.length > 0) {
//			StringBuilder sb = new StringBuilder();
//			for (ModelObject<ID> obj : objs) {
//				sb.append(obj.getId());
//				sb.append(StringUtils.COMMA);
//			}
//			String str = sb.substring(0, sb.length() - 1);
//			return str;
//		} else {
//			return null;
//		}
//	}

	/**
	 * 
	 * StringToLongArray(such as string "1,2,3,4" convert to LongArray")
	 * 
	 * TODO(Here describes this method to be suitable the condition - to be
	 * possible to elect)
	 * 
	 * @param str
	 * @return
	 * 
	 *         Long[]
	 */
	public static Long[] StringToLongArray(String str) {
		if (str != null && str.length() > 0) {
			String[] targetIdsArray = str.split(StringUtils.COMMA);
			int arrayLength = targetIdsArray.length;
			Long[] targetIdsLongArray = new Long[arrayLength];
			for (int i = 0; i < arrayLength; i++) {
				targetIdsLongArray[i] = Long.parseLong(targetIdsArray[i]);
			}
			return targetIdsLongArray;
		} else {
			return null;
		}
	}
}
