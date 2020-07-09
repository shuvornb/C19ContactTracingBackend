package com.isensor.contacttracingbackend.util;

import com.isensor.contacttracingbackend.db.entity.C19User;
import com.isensor.contacttracingbackend.exception.UnauthorizedException;
import com.isensor.contacttracingbackend.service.RegisterService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTUtils {
    private Logger log = LoggerFactory.getLogger(RegisterService.class);
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

    public void validateToken(String token) {
        if(token == null) {
            log.info("Token unavailable. Unauthorized access");
            throw new UnauthorizedException("Token unavailable. Unauthorized access");
        }
        if(token.isEmpty()) {
            log.info("Token empty. Unauthorized access");
            throw new UnauthorizedException("Token empty. Unauthorized access");
        }
        log.info("Token: {}", token);
        try {
            Jwts.parser().setSigningKey(secret).parse(token);
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error("Not a valid JWT");
            throw new UnauthorizedException("Not a valid JWT");
        }
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(C19User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userDetails.id);
        claims.put("phoneNumber", userDetails.phoneNumber);
        claims.put("email", userDetails.email);
        claims.put("name", userDetails.getFullName());
        return doGenerateToken(claims, userDetails.getFullName());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }
}
