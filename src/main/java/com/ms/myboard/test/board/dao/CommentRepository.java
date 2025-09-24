package com.ms.myboard.test.board.dao;

import com.ms.myboard.test.board.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    // 특정 게시글의 모든 댓글 조회 (부모 댓글만)
    List<Comment> findByBoardBoardNoAndParentCommentIsNullOrderByCreatedAtAsc(Long boardNo);

    // 특정 게시글의 모든 댓글 조회 (대댓글 포함)
    List<Comment> findByBoardBoardNoOrderByCreatedAtAsc(Long boardNo);

    // 특정 부모 댓글의 대댓글들 조회
    List<Comment> findByParentCommentCommentNoOrderByCreatedAtAsc(Long parentCommentNo);

    // 특정 회원이 작성한 댓글들 조회
    List<Comment> findByMemberMemberNoOrderByCreatedAtDesc(Long memberNo);

    // 댓글 개수 조회 (게시글별)
    @Query("SELECT COUNT(c) FROM Comment c WHERE c.board.boardNo = :boardNo")
    Long countByBoardNo(@Param("boardNo") Long boardNo);
}
