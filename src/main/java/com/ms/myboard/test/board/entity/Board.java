package com.ms.myboard.test.board.entity;

import com.ms.myboard.test.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue
    private Long boardNo;

    private String boardTitle;

    private String boardContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member;

    private LocalDateTime createTime;

    private Integer boardCount;

    @OneToMany(mappedBy = "board", cascade =  CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    public void updateBoard(String title, String content) {
        this.boardTitle = title;
        this.boardContent = content;
    }
    public void increaseViewCount() {
        this.boardCount++;
    }
}
