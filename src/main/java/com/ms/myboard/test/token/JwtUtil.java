package com.ms.myboard.test.token;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * JWT 토큰 생성 및 검증을 담당하는 유틸리티 클래스
 *
 * @author ms
 * @since 2025-09-28
 */
@Slf4j
@Component
public class JwtUtil {

    private final String secretKey;
    private final long accessTokenExpiration;
    private final long refreshTokenExpiration;

    public JwtUtil(
            @Value("${jwt.secret:mySecretKey123456789012345678901234567890}") String secret,
            @Value("${jwt.access-token-expiration:3600000}") long accessTokenExpiration,  // 1시간
            @Value("${jwt.refresh-token-expiration:604800000}") long refreshTokenExpiration // 7일
    ) {
        this.secretKey = secret;
        this.accessTokenExpiration = accessTokenExpiration;
        this.refreshTokenExpiration = refreshTokenExpiration;
    }

    /**
     * Access Token 생성
     *
     * @param memberNo 회원 번호
     * @param memberId 회원 ID
     * @return JWT Access Token
     */
    public String generateAccessToken(Long memberNo, String memberId) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + accessTokenExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(memberNo))
                .claim("memberId", memberId)
                .claim("type", "access")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Refresh Token 생성
     *
     * @param memberNo 회원 번호
     * @return JWT Refresh Token
     */
    public String generateRefreshToken(Long memberNo) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + refreshTokenExpiration);

        return Jwts.builder()
                .setSubject(String.valueOf(memberNo))
                .claim("type", "refresh")
                .setIssuedAt(now)
                .setExpiration(expiry)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 토큰에서 회원 번호 추출
     *
     * @param token JWT 토큰
     * @return 회원 번호
     */
    public Long getMemberNoFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return Long.valueOf(claims.getSubject());
        } catch (Exception e) {
            log.error("토큰에서 회원 번호 추출 실패: {}", e.getMessage());
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }

    /**
     * 토큰에서 회원 ID 추출
     *
     * @param token JWT 토큰
     * @return 회원 ID
     */
    public String getMemberIdFromToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.get("memberId", String.class);
        } catch (Exception e) {
            log.error("토큰에서 회원 ID 추출 실패: {}", e.getMessage());
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }

    /**
     * 토큰 유효성 검증
     *
     * @param token JWT 토큰
     * @return 유효성 여부
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("지원되지 않는 JWT 토큰입니다: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 잘못되었습니다: {}", e.getMessage());
        }
        return false;
    }

    /**
     * 토큰 만료 시간 확인
     *
     * @param token JWT 토큰
     * @return 만료 시간
     */
    public LocalDateTime getExpirationTime(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return claims.getExpiration()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
        } catch (Exception e) {
            log.error("토큰 만료 시간 확인 실패: {}", e.getMessage());
            throw new IllegalArgumentException("유효하지 않은 토큰입니다.");
        }
    }

    /**
     * Access Token인지 확인
     *
     * @param token JWT 토큰
     * @return Access Token 여부
     */
    public boolean isAccessToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return "access".equals(claims.get("type", String.class));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Refresh Token인지 확인
     *
     * @param token JWT 토큰
     * @return Refresh Token 여부
     */
    public boolean isRefreshToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            return "refresh".equals(claims.get("type", String.class));
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Authorization 헤더에서 토큰 추출
     *
     * @param authHeader Authorization 헤더 값
     * @return JWT 토큰 (Bearer 제거된 순수 토큰)
     */
    public String extractTokenFromHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }

    /**
     * 토큰 정보 로깅 (디버깅용)
     *
     * @param token JWT 토큰
     */
    public void logTokenInfo(String token) {
        if (!validateToken(token)) {
            log.warn("유효하지 않은 토큰입니다.");
            return;
        }

        try {
            Long memberNo = getMemberNoFromToken(token);
            LocalDateTime expiration = getExpirationTime(token);
            boolean isAccess = isAccessToken(token);

            log.info("토큰 정보 - 회원번호: {}, 만료시간: {}, 타입: {}",
                    memberNo, expiration, isAccess ? "ACCESS" : "REFRESH");
        } catch (Exception e) {
            log.error("토큰 정보 로깅 실패: {}", e.getMessage());
        }
    }
}