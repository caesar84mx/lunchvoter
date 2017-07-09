package com.caesar_84.lunchvoter.util;

public class DateUtil {
    public DateUtil() {}

    public static <T extends Comparable<? super T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }
}
