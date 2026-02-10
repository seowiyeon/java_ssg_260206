package ll.kor.java.ssg.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
    public static String getNowDateStr() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }
}
