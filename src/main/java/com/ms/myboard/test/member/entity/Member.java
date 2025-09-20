package com.ms.myboard.test.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private Long memberNo;
    private String memberId;
    private String memberPwd;
    private String memberName;
    private String memberEmail;
    private Integer memberAge;
}
