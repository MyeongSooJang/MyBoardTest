package com.ms.myboard.test.board.service;

import com.ms.myboard.test.board.dao.BoardRepository;
import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import com.ms.myboard.test.board.entity.Board;
import com.ms.myboard.test.member.dao.MemberRepository;
import com.ms.myboard.test.member.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public BoardResponse findBoardByBoardNo(Long boardNo) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("해당 하는 번호의 게시글이 존재하지 않습니다."));
        return BoardResponse.fromBoard(board);
    }

    // 조작 가능성이 존재하므로, 프론트에서 memberNo를 보내지 않음

    public void saveBoard(BoardRequest boardRequest,Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElseThrow(
                ()->new EntityNotFoundException("해당하는 회원이 존재하지 않습니다."));

        Board board = Board.builder()
                .boardTitle(boardRequest.title())
                .boardContent(boardRequest.content())
                .member(member)
                .createTime(LocalDateTime.now())
                .boardCount(0)
                .build();

        boardRepository.save(board);
    }
    public void updateBoard(Long boardNo, BoardRequest boardRequest) {
        Board board = boardRepository.findById(boardNo)
                .orElseThrow(() -> new EntityNotFoundException("해당하는 게시글이 존재하지 않습니다."));

        // Board 엔티티에 업데이트 메소드가 필요합니다
        board.updateBoard(boardRequest.title(), boardRequest.content());
    }

    public void deleteBoard(Long boardNo) {
        if (!boardRepository.existsById(boardNo)) {
            throw new EntityNotFoundException("해당하는 게시글이 존재하지 않습니다.");
        }
        boardRepository.deleteById(boardNo);
    }




}
