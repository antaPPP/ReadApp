package com.readapp.backend.utils;

import org.jetbrains.annotations.NotNull;

public final class CoderUtils {

    public final static String LEGAL_CHARS = "!@#$%^&*()+-_;:,.~";

    public static boolean checkPassword(@NotNull String password){
        if (password.length() < 8 || password.length() > 16) return false;
        for (char c : password.toCharArray()) {
            if ((c < 'A' || c > 'z') && !LEGAL_CHARS.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }
}
