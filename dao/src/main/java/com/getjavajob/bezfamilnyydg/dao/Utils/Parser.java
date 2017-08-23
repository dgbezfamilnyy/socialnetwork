package com.getjavajob.bezfamilnyydg.dao.Utils;

import com.getjavajob.bezfamilnyydg.models.PersonalPhone;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class Parser {
    public static <T> String getIds(List<T> ids) {
        StringBuilder str = new StringBuilder();
        Iterator<T> itr = ids.iterator();
        while (itr.hasNext()) {
            str.append(itr.next());
            if (itr.hasNext()) {
                str.append(", ");
            }
        }

        return str.toString();
    }

    public static Date toSQLDate(LocalDate date) {
        if (date != null) {
            return Date.valueOf(date);
        } else {
            return null;
        }
    }

    public static List<String> parsePhones(List<PersonalPhone> phones) {
        List<String> result = new LinkedList<>();

        for (PersonalPhone phone : phones) {
            result.add(phone.getPhone());
        }

        return result;
    }
}
