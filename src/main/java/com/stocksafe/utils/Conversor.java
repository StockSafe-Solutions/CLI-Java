package com.stocksafe.utils;

public class Conversor {

    public static Double converteMb(Integer bytes) {
        return bytes / Math.pow(1024, 2);
    }

    public static Double converteMb(Long bytes) {
        return bytes / Math.pow(1024, 2);
    }

    public static Double converteGb(Integer bytes) {
        return bytes / Math.pow(1024, 3);
    }

    public static Double converteGb(Long bytes) {
        return bytes / Math.pow(1024, 3);
    }
}
