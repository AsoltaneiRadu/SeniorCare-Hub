package org.example.healthapp.security;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private static final String SECRET_KEY = "AdevarataMeaCheieSecretaFoarteLungaPentruProiectulDeTeleasistentaMedicala12345CheieHS256Sigura";
    private static final long EXPIRATION_TIME_MILLIS = 24 * 60 * 60 * 1000;
    public String generateToken(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email-ul nu poate fi null sau gol");
        }
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + EXPIRATION_TIME_MILLIS);
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    public String extractEmail(String token) {
        return extractAllClaims(token).getSubject();
    }
    public boolean isTokenValid(String token) {
        try {
            Date expirationDate = extractAllClaims(token).getExpiration();
            return expirationDate != null && expirationDate.after(new Date());
        } catch (JwtException | IllegalArgumentException ex) {
            return false;
        }
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }
}