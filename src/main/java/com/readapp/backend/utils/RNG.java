package com.readapp.backend.utils;

import java.util.Random;

public class RNG {
    public static String generateVerificationCode(int bits){
        StringBuilder builder = new StringBuilder("");
        for(int i = 0; i < bits; i++) {
            builder.append((int)(Math.random()*10));
        }
        return builder.toString();
    }

    public static long nextLong(Random rng, long n) {
        // error checking and 2^x checking removed for simplicity.
        long bits, val;
        do {
            bits = (rng.nextLong() << 1) >>> 1;
            val = bits % n;
        } while (bits-val+(n-1) < 0L);
        return val;
    }
}
