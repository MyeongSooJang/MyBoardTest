package com.ms.myboard.test.board.controller;

import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "게시판", description = "게시판 관련 API")

public interface BoardControllerSwagger {
    @Operation(summary = "게시글 조회", description = "게시글 번호로 게시글을 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "조회 성공"),
            @ApiResponse(responseCode = "404", description = "게시글을 찾을 수 없음")
    })
    ResponseEntity<BoardResponse> findBoard(@PathVariable Long boardNo);

    @Operation(summary = "게시글 작성", description = "새로운 게시글을 작성합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "작성 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청")
    })
    ResponseEntity<Void> saveBoard(@RequestBody BoardRequest boardRequest, Long memberNo);

    @Operation(summary = "게시글 검색", description = "검색 조건에 따라 게시글을 검색합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "검색 성공"),
            @ApiResponse(responseCode = "400", description = "잘못된 검색 조건")
    })
    ResponseEntity<Page<BoardResponse>> searchBoards(
            @Parameter(description = "검색 타입 (TITLE: 제목, CONTENT: 내용, WRITER: 작성자, ALL: 전체)", example = "ALL")
            @RequestParam(defaultValue = "ALL") String searchType,

            @Parameter(description = "검색 키워드", example = "스프링")
            @RequestParam String keyword,

            @Parameter(description = "페이징 정보 (page: 페이지 번호, size: 페이지 크기, sort: 정렬 기준)")
            Pageable pageable
    );
}
