package com.fm.security;

import com.fm.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

/**
 * 1. verify the access token's signature
 * 2. extract identity and authorization claims from Access token
 * and use them to create UserContext
 * 3. if Access token is malformed, expired or not signed, throw
 * Authentication exception
 */
@Component
public class JwtTokenProvider {

    /**
     * THIS IS NOT A SECURE PRACTICE! For simplicity, we are storing a static key here. Ideally, in a
     * microservices environment, this key would be kept on a config-server.
     */
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;

    @Value("${security.jwt.token.expire-length:3600000}")   // default 1h
    private long validityInMilliseconds;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    // learn from https://github.com/jwtk/jjwt
    public String createToken(String username, List<SimpleGrantedAuthority> authorities) {
        // set username on claims
        Claims claims = Jwts.claims().setSubject(username);

        // set authorities on claims
        claims.put("auth", authorities);

        Key signingKey = getSecretKey();

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(signingKey)
            .compact();
    }

    private Key getSecretKey() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
        // convert base64 encoded secret key to byte[]
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        // create key using secret and algorithm
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        return signingKey;
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(secretKey)
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parse(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException("Expired or invalid JWT token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
