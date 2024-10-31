package com.vcredit.vzy.common.utils;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Objects;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/4/25
 */
public class CompareUtil {

    public static String compareObjects(Object obj1, Object obj2, String... filterField) {
        StringBuilder sb = new StringBuilder();
        try {
            Class<?> clazz = obj1.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                if (filterField != null && filterField.length > 0 && Arrays.asList(filterField).contains(field.getName())) {
                    continue;
                }
                field.setAccessible(true);
                Object val1 = field.get(obj1);
                Object val2 = field.get(obj2);
                if (!Objects.equals(val1, val2)) {
                    sb.append(field.getName()).append("：").append(val1).append(" --> ").append(val2).append("<br/>");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

}
