package com.ms.myboard.test.board.service;

import com.ms.myboard.test.board.dao.BoardRepository;
import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import com.ms.myboard.test.board.entity.Board;
import com.ms.myboard.test.member.entity.Member;
import com.ms.myboard.test.member.dao.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardResponse findBoardByBoardNo(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 하는 번호의 게시글이 존재하지 않습니다."));
        return BoardResponse.fromBoard(board);
    }

    // 조작 가능성이 존재하므로, 프론트에서 memberNo를 보내지 않음
    @Transactional
    public void saveBoard(BoardRequest boardRequest,Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElseThrow(
                ()->new EntityNotFoundException("해당하는 회원이 존재하지 않습니다."));




    }

    /**
     * 검색 타입에 따른 게시글 검색
     * @param searchType TITLE(제목), CONTENT(내용), WRITER(작성자), ALL(전체)
     * @param keyword 검색 키워드
     * @param pageable 페이징 정보
     * @return 검색된 게시글 Page
     */
    public Page<BoardResponse> searchBoards(String searchType, String keyword, Pageable pageable) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("검색 키워드를 입력해주세요.");
        }

        Page<Board> boards = switch (searchType.toUpperCase()) {
            case "TITLE" -> boardRepository.findByBoardTitleContaining(keyword, pageable);
            case "CONTENT" -> boardRepository.findByBoardContentContaining(keyword, pageable);
            case "WRITER" -> boardRepository.findByMemberName(keyword, pageable);
            case "ALL" -> boardRepository.searchByKeyword(keyword, pageable);
            default -> throw new IllegalArgumentException("잘못된 검색 타입입니다. (TITLE, CONTENT, WRITER, ALL 중 선택)");
        };

        return boards.map(BoardResponse::fromBoard);
    }

}
