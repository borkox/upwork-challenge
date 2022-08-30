package com.urlshort.service;

import java.util.Random;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This utility is responsible to generate unique identifiers of links. Default strategy is to generate 7 symbols from
 * enumerated characters. The chance that unique id to be repeated is really small (maybe once per couple of thousand
 * years). Even if the ID is repeated it is still not a big problem for the application.
 */
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

    /**
     * Generate a pseudo-unique identifier. Chances for repeat are minimal (once per thousand years).
     * @return fixed size UID.
     */
    public static String generateUid() {
        StringBuilder builder = new StringBuilder();
        RANDOM.ints(RANDOM_SIZE)
                .map(Math::abs)
                .mapToObj(i -> UID_SYMBOLS[i % UID_SYMBOLS.length])
                .forEach(builder::append);
        return builder.toString();
    }
}
