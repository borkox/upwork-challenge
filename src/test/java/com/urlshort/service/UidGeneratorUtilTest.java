package com.urlshort.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.Test;


public class UidGeneratorUtilTest {

    @Test
    public void testGenerateRandomUid() {
        // 1000 subsequent ids should not be equal
        Set<String> ids =
                IntStream.range(0, 1000)
                        .mapToObj(i -> UidGeneratorUtil.generateUid())
                        .collect(Collectors.toSet());

        assertThat(ids).hasSize(1000);
    }
}
