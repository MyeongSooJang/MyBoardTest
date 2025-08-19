package com.bs.boot.myboardrest.member.member.entity;

import com.airoom.airoom.common.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@MappedSuperclass
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
// @EntityListeners(AuditingEntityListener.class)
// 언제, 누구에 의해 만들어지고 수정되었는지 JPA가 알아서 관리 하도록

public class Member  extends  BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberNo;
    @Column(unique = true, nullable = false, length = 8)
    private String memberId;
    @Column(nullable = false)
    private String memberPw;
    private String memberName;
    private Integer memberAge;
    private String memberSchool;
    private String memberEmail;
    @Enumerated(EnumType.STRING)
    private Gender memberGender;
    @Enumerated(EnumType.STRING)
    private Grade memberGrade;
    private Integer memberClass;
    private Integer memberClassNo;
    private String memberType;
}
