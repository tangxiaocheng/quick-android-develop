package tangxiaocheng.log;

public class StringUtil {

	public static String getFristToUpperCase(String word) {
		StringBuffer buffer =new StringBuffer();
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (i==0) {
				buffer.append(Character.toUpperCase(charArray[i]));
			}else {
				buffer.append(c);
			}
		}
		String newString = buffer.toString();
		return newString;
	}
	public static String getActivityName(String word) {
		StringBuffer buffer =new StringBuffer();
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (i==0) {
				
				buffer.append(Character.toUpperCase(charArray[i]));
			}else {
				if (c=='_') {
					int j = i+1;
					if (j<charArray.length) {
						buffer.append(Character.toUpperCase(charArray[j]));
						i++;
					}
				}else {
					buffer.append(c);
				}
			}
		}
		String newString = buffer.toString();
		return newString;
	}
	public static String getXmlName(String word) {
		StringBuffer buffer =new StringBuffer();
		
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (i==0) {
				buffer.append(Character.toLowerCase(charArray[i]));
			}else {
				if (Character.isUpperCase(c)) {
					buffer.append("_"+Character.toLowerCase(c));
				}else {
					buffer.append(c);
				}
			}
		}
		String newString = buffer.toString();
		return newString;
	}
	public static String getAdapterName(String word) {
		StringBuffer buffer =new StringBuffer();
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (i==0) {
				buffer.append(Character.toUpperCase(charArray[i]));
			}else {
				buffer.append(c);
			}
		}
		buffer.append("Adapter");
		String newString = buffer.toString();
		return newString;
	}
	public static String changeFirstToUpper(String word) {
		StringBuffer buffer =new StringBuffer();
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			char c = charArray[i];
			if (i==0) {
				buffer.append(Character.toUpperCase(charArray[i]));
			}else {
				buffer.append(c);
			}
		}
		String newString = buffer.toString();
		return newString;
	}
	public static boolean hasUpperCase(String word) {
		char[] charArray = word.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			if (Character.isUpperCase(charArray[i])) {
				return true;
			}
		}
		return false;
	}

}
