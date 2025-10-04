package com.ms.myboard.test.board.dao;

import com.ms.myboard.test.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    //

    // 제목으로 검색 (페이징)
    Page<Board> findByBoardTitleContaining(String title, Pageable pageable);

    // 내용으로 검색 (페이징)
    Page<Board> findByBoardContentContaining(String content, Pageable pageable);

    // 작성자명으로 검색 (페이징)
    @Query("SELECT b FROM Board b JOIN b.member m WHERE m.memberName LIKE %:memberName%")
    Page<Board> findByMemberName(@Param("memberName") String memberName, Pageable pageable);

    // 전체 검색 (제목 + 내용 + 작성자명)
    @Query("SELECT b FROM Board b JOIN b.member m " +
           "WHERE b.boardTitle LIKE %:keyword% " +
           "OR b.boardContent LIKE %:keyword% " +
           "OR m.memberName LIKE %:keyword%")
    Page<Board> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);
}
