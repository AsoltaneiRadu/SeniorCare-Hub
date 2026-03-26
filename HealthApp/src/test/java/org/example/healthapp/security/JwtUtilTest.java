package org.example.healthapp.security;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
class JwtUtilTest {
    private final JwtUtil jwtUtil = new JwtUtil();
    @Test
    void shouldGenerateTokenAndExtractEmail() {
        String email = "pacient@test.com";
        String token = jwtUtil.generateToken(email);
        assertNotNull(token);
        assertTrue(jwtUtil.isTokenValid(token));
        assertEquals(email, jwtUtil.extractEmail(token));
    }
    @Test
    void shouldReturnFalseForInvalidToken() {
        assertFalse(jwtUtil.isTokenValid("token-invalid"));
    }
}