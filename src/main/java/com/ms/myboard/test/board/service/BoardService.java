package com.ms.myboard.test.board.service;

import com.ms.myboard.test.board.dao.BoardRepository;
import com.ms.myboard.test.board.dto.BoardRequest;
import com.ms.myboard.test.board.dto.BoardResponse;
import com.ms.myboard.test.board.entity.Board;
import com.ms.myboard.test.member.entity.Member;
import com.ms.myboard.test.member.dao.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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




    }

}
