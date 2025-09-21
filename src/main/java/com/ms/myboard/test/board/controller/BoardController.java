package com.ms.myboard.test.board.controller;

import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import com.ms.myboard.test.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{boardNo}")
    public ResponseEntity<BoardResponse> findBoard(@PathVariable Long boardNo) {
        return ResponseEntity.ok(boardService.findBoardByBoardNo(boardNo));
    }

    @PostMapping
    public ResponseEntity<Void> saveBoard(@RequestBody BoardRequest boardRequest,
                                          Long memberNo) {
        boardService.saveBoard(boardRequest,memberNo);
        return ResponseEntity.ok().build();
    }

    // 게시글 수정
    @PutMapping("/{boardNo}")
    public ResponseEntity<Void> updateBoard(@PathVariable Long boardNo,
                                            @RequestBody BoardRequest boardRequest) {
        boardService.updateBoard(boardNo, boardRequest);
        return ResponseEntity.ok().build();
    }

    // 게시글 삭제
    @DeleteMapping("/{boardNo}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardNo) {
        boardService.deleteBoard(boardNo);
        return ResponseEntity.ok().build();
    }

}
