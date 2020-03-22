package tangxiaocheng.log;

public class Log {

  public static void i(String tag, String log) {
    System.out.println(tag + " ---> " + log);
    System.out.println();
  }

  public static void e(String tag, String log) {
    System.err.println(tag + " ---> " + log);
    System.out.println();
  }

  public static void d(String tag, String log) {
    System.out.println(tag + " ---> " + log);
    System.out.println();
  }

  public static void v(String tag, String log) {
    System.out.println(tag + " ---> " + log);
    System.out.println();
  }

  public static void main(String[] args) {
    String string = "0,1-2";
    System.out.println(string.substring(2));
  }
}
