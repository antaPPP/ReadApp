package com.readapp.backend.utils;

public class RNG {
    public static String generateVerificationCode(int bits){
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < bits; i++) {
            builder.append((int)(Math.random()*10));
        }
        return builder.toString();
    }
}
