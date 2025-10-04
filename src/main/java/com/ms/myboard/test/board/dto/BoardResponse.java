package com.ms.myboard.test.board.dto;

import com.ms.myboard.test.board.entity.Board;
import com.ms.myboard.test.board.entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public record BoardResponse(
        Long boardNo,
        String boardTitle,
        String writer,
        LocalDateTime createdAt,
        String boardContent,
        List<Comment> comments
) {
//    레코드의 필드들은 final 이므로 밑의 방식으로 할당이 불가능
//    레코드에서는 모든 필드를 초기화 하는 canonical constructor 호출해서 사용해야 한다. (자동 생성)
//    정적인 팩토리 메서드를 사용하는 것이 선호 되는 이유
    //  1. null 안전성과 validation 추가 용이 -> 확장성
    //  2. 레코드의 철학에 부합 -> 단순한 데이터 홀더로 부합

//    public BoardResponse (Board board){
//        this.boardNo = board.getBoardNo();
//        this.boardTitle = board.getBoardTitle();
//        this.writer = board.getMember().getMemberName();
//        this.createdAt = board.getCreateTime();
//        this.boardContent = board.getBoardContent();
//        this.comments = board.getComments();

    public static BoardResponse fromBoard(Board board) {
        return new BoardResponse(board.getBoardNo(),
                board.getBoardTitle(),
                board.getMember().getMemberName(),
                board.getCreateTime(),
                board.getBoardContent(),
                board.getComments());
    }
}

