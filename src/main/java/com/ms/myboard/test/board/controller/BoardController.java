package com.ms.myboard.test.board.controller;

import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import com.ms.myboard.test.board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController implements BoardControllerSwagger {

    private final BoardService boardService;

    // 게시글 조회
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

    @Operation(summary = "게시글 조회수 증가")
    @PatchMapping("/{boardNo}/view")
    public ResponseEntity<Void> increaseViewCount(@PathVariable Long boardNo) {
        boardService.increaseViewCount(boardNo);
        return ResponseEntity.ok().build();
    }

    /**
     * 게시글 검색 API
     * @param searchType 검색 타입 (TITLE, CONTENT, WRITER, ALL)
     * @param keyword 검색 키워드
     * @param pageable 페이징 정보 (default: page=0, size=10, sort=createTime,DESC)
     * @return 검색된 게시글 페이지
     */
    @GetMapping("/search")
    public ResponseEntity<Page<BoardResponse>> searchBoards(
            @RequestParam(defaultValue = "ALL") String searchType,
            @RequestParam String keyword,
            @PageableDefault(size = 10, sort = "createTime", direction = Sort.Direction.DESC) Pageable pageable) {

        return ResponseEntity.ok(boardService.searchBoards(searchType, keyword, pageable));
    }

//    @DeleteMapping
//    public ResponseEntity<Void> deleteBoard(@RequestBody BoardRequest boardRequest) {
//        c
//
//        return ResponseEntity.ok().build();
//    }

}
