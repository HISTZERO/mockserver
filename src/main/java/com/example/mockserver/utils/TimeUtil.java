package com.example.mockserver.utils;

import java.sql.Timestamp;

public class TimeUtil {
    public static Timestamp getTime() {
        return new Timestamp(System.currentTimeMillis());
    }
}
