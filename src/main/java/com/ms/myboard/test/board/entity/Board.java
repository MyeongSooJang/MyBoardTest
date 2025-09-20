package com.ms.myboard.test.board.entity;

import com.ms.myboard.test.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Board {
    @Id
    private Long boardNo;

    private String boardTitle;

    private String boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberName")
    private Member member;

    private LocalDateTime createTime;

    private Integer boardCount;

    @OneToMany(mappedBy = "board", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
