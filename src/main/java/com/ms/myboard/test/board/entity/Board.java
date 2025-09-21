package com.ms.myboard.test.board.entity;

import com.ms.myboard.test.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    public void updateBoard(String title, String content) {
        this.boardTitle = title;
        this.boardContent = content;
    }
}
