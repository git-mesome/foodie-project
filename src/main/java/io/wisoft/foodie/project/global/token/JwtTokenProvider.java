package io.wisoft.foodie.project.global.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Random;

@Component
public class JwtTokenProvider implements InitializingBean {

    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private final String secretKey;

    public JwtTokenProvider(@Value("${jwt.access-token.expire-length}") final long accessTokenValidityInMilliseconds,
                            @Value("${jwt.refresh-token.expire-length}") final long refreshTokenValidityInMilliseconds,
                            @Value("${jwt.token.secret-key}") final String secretKey) {
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
        this.secretKey = secretKey;
    }

    private Key key;

    @Override
    public void afterPropertiesSet() {
        final byte[] keyBytes = secretKey.getBytes();
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String createAccessToken(final String payload) {
        return createToken(payload, accessTokenValidityInMilliseconds);
    }

    public String createRefreshToken() {
        final byte[] array = new byte[7];
        new Random().nextBytes(array);

        final String generatedString = new String(array, StandardCharsets.UTF_8);

        return createToken(generatedString, refreshTokenValidityInMilliseconds);
    }

    public String createToken(final String payload, final long expiredLength) {

        final Claims claims = Jwts.claims().setSubject(payload);

        final Date now = new Date();
        final Date validity = new Date(now.getTime() + expiredLength);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public Long getPayload(final String token) {
        try {
            return Long.valueOf(Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());

        } catch (ExpiredJwtException e) {
            return Long.valueOf(e.getClaims().getSubject());

        } catch (JwtException e) {
            throw new RuntimeException("유효하지 않은 토큰입니다.");
        }
    }

    public boolean validateToken(final String token) {
        try {
            final Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes())
                    .build()
                    .parseClaimsJws(token);

            return !claims.getBody().getExpiration().before(new Date());

        } catch (JwtException | IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
    }

}
