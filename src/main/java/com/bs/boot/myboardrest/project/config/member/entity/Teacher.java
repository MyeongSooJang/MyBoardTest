package com.bs.boot.myboardrest.project.config.member.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Getter
// JPA는 기본 생성자로 생성 | 개발자의 무분별한 생성을 막기 위해
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
// Soft Delete
@SQLDelete(sql = "UPDATE TEACHER SET deleted_at = NOW() WHERE member_no = ?")
@SQLRestriction("deleted_at IS NULL")
@Table(name = "TEACHER")

public class Teacher extends Member {

}
