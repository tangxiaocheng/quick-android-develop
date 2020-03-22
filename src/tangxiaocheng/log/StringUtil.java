package tangxiaocheng.log;

public class StringUtil {

  public static String getFirstToUpperCase(String word) {
    StringBuilder buffer = new StringBuilder();
    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (i == 0) {
        buffer.append(Character.toUpperCase(charArray[i]));
      } else {
        buffer.append(c);
      }
    }
    return buffer.toString();
  }

  public static String getActivityName(String word) {
    StringBuilder buffer = new StringBuilder();
    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (i == 0) {

        buffer.append(Character.toUpperCase(charArray[i]));
      } else {
        if (c == '_') {
          int j = i + 1;
          if (j < charArray.length) {
            buffer.append(Character.toUpperCase(charArray[j]));
            i++;
          }
        } else {
          buffer.append(c);
        }
      }
    }
    return buffer.toString();
  }

  public static String getXmlName(String word) {
    StringBuilder buffer = new StringBuilder();

    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (i == 0) {
        buffer.append(Character.toLowerCase(charArray[i]));
      } else {
        if (Character.isUpperCase(c)) {
          buffer.append("_" + Character.toLowerCase(c));
        } else {
          buffer.append(c);
        }
      }
    }
    return buffer.toString();
  }

  public static String getAdapterName(String word) {
    StringBuilder buffer = new StringBuilder();
    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (i == 0) {
        buffer.append(Character.toUpperCase(charArray[i]));
      } else {
        buffer.append(c);
      }
    }
    buffer.append("Adapter");
    return buffer.toString();
  }

  public static String changeFirstToUpper(String word) {
    StringBuilder buffer = new StringBuilder();
    char[] charArray = word.toCharArray();
    for (int i = 0; i < charArray.length; i++) {
      char c = charArray[i];
      if (i == 0) {
        buffer.append(Character.toUpperCase(charArray[i]));
      } else {
        buffer.append(c);
      }
    }
    return buffer.toString();
  }

  public static boolean hasUpperCase(String word) {
    char[] charArray = word.toCharArray();
    for (char c : charArray) {
      if (Character.isUpperCase(c)) {
        return true;
      }
    }
    return false;
  }
}
