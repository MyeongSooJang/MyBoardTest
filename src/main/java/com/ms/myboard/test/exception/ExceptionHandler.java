package com.ms.myboard.test.exception;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
@ResponseBody
public class ExceptionHandler {
    /**
     * 데이터를 찾을 수 없는 경우 (404)
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(EntityNotFoundException e) {
        log.warn("Not found: {}", e.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 404);
        response.put("error", "NOT_FOUND");
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(404).body(response);
    }

    /**
     * 잘못된 요청 (400)
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleBadRequest(IllegalArgumentException e) {
        log.warn("Bad request: {}", e.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 400);
        response.put("error", "BAD_REQUEST");
        response.put("message", e.getMessage());
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(400).body(response);
    }

    /**
     * 서버 오류 (500)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleServerError(Exception e) {
        log.error("Server error: {}", e.getMessage());

        Map<String, Object> response = new HashMap<>();
        response.put("status", 500);
        response.put("error", "SERVER_ERROR");
        response.put("message", "서버에서 오류가 발생했습니다.");
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(500).body(response);
    }
}
