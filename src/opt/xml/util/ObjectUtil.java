package opt.xml.util;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ObjectUtil {

  public static boolean listHasData(List<?> list) {
    if (list != null && list.size() > 0) {
      return true;
    }
    return false;
  }

  public static boolean mapHasData(Map<?, ?> map) {
    if (map != null && map.size() > 0) {
      return true;
    }
    return false;
  }

  public static boolean collectionHasData(Collection<?> collection) {
    if (collection != null && collection.size() > 0) {
      return true;
    }
    return false;
  }
}
