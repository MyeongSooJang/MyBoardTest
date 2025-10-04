package com.ms.myboard.test.board.controller;

import com.ms.myboard.test.board.dto.CommentRequest;
import com.ms.myboard.test.board.dto.CommentResponse;
import com.ms.myboard.test.board.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comment", description = "댓글 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "댓글 작성", description = "게시글에 댓글을 작성합니다.")
    @PostMapping
    public ResponseEntity<Void> createComment(
            @RequestBody CommentRequest request,
            @RequestParam Long memberNo) {
        commentService.createComment(request, memberNo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "대댓글 작성", description = "댓글에 대댓글을 작성합니다.")
    @PostMapping("/reply")
    public ResponseEntity<Void> createReply(
            @RequestBody CommentRequest request,
            @RequestParam Long memberNo,
            @RequestParam Long parentCommentNo) {
        commentService.createReply(request, memberNo, parentCommentNo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "게시글의 댓글 목록 조회", description = "특정 게시글의 모든 댓글을 조회합니다.")
    @GetMapping("/board/{boardNo}")
    public ResponseEntity<List<CommentResponse>> getCommentsByBoard(
            @PathVariable Long boardNo) {
        return ResponseEntity.ok(commentService.getCommentsByBoard(boardNo));
    }

    @Operation(summary = "댓글 수정", description = "작성한 댓글을 수정합니다.")
    @PutMapping("/{commentNo}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentNo,
            @RequestBody CommentRequest request) {
        commentService.updateComment(commentNo, request);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "댓글 삭제", description = "작성한 댓글을 삭제합니다.")
    @DeleteMapping("/{commentNo}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long commentNo) {
        commentService.deleteComment(commentNo);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "댓글 개수 조회", description = "게시글의 총 댓글 개수를 조회합니다.")
    @GetMapping("/count/{boardNo}")
    public ResponseEntity<Long> getCommentCount(
            @PathVariable Long boardNo) {
        return ResponseEntity.ok(commentService.getCommentCount(boardNo));
    }
}