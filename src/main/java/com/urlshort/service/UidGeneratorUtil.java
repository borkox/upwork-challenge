package com.urlshort.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UidGeneratorUtil {

    private static final char[] UID_SYMBOLS =
            "01234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final Random RANDOM;
    private static final int RANDOM_SIZE = 7;

    static {
        RANDOM = new Random();
        RANDOM.setSeed(System.currentTimeMillis());
    }

    public static String generateUid() {
        StringBuilder builder = new StringBuilder();
        RANDOM.ints(RANDOM_SIZE)
                .map(Math::abs)
                .mapToObj(i -> UID_SYMBOLS[i % UID_SYMBOLS.length])
                .forEach(builder::append);
        return builder.toString();
    }
}
