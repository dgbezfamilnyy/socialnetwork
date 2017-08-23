package com.getjavajob.bezfamilnyydg.servlets.common.util;

public class Validator {
    public static boolean validatePhone(String phone) {
        return phone!= null && !phone.equals("");
    }
}
