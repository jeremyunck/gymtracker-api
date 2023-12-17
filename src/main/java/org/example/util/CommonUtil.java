package org.example.util;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class CommonUtil {
    public static Timestamp getCurrentTimestamp() {
        return Timestamp.valueOf(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(DateTime.now().toDate()));
    }
}
