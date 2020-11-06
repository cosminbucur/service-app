package com.fm.security;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JwtTokenProviderTest {

    @Test
    void validateToken() {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();

        boolean actual = jwtTokenProvider.validateToken("1234");

        assertThat(actual).isTrue();
    }
}