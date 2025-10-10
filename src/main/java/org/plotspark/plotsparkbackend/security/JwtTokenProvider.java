package org.plotspark.plotsparkbackend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.plotspark.plotsparkbackend.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    private final String accessTokenSecret;
    private final String accessTokenExpirationMs;
    private final SecretKey key;

    public JwtTokenProvider(@Value("${app.jwt.access-token-secret}") String accessTokenSecret,
                            @Value("${app.jwt.access-token-expiration-ms}") String accessTokenExpirationMs) {
        this.accessTokenSecret = accessTokenSecret;
        this.accessTokenExpirationMs = accessTokenExpirationMs;
        this.key = Keys.hmacShaKeyFor(accessTokenSecret.getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId().toString());
        claims.put("username", user.getUsername());
        claims.put("email", user.getEmail());

        return createToken(claims, user.getId().toString());
    }

    private String createToken(Map<String, Object> claims, String userId) {
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + accessTokenExpirationMs);

        return Jwts.builder()
                .claims(claims)
                .subject(userId)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claim = getAllClaimsFromToken(token);
        return claimsResolver.apply(claim);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch (SignatureException ex) {
            logger.error("Invalid JWT signature: {}", ex.getMessage());
        }
        catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token: {}", ex.getMessage());
        }
        catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token: {}", ex.getMessage());
        }
        catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token: {}", ex.getMessage());
        }
        catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty: {}", ex.getMessage());
        }

        return false;
    }
}
