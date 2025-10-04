# 개발 일지 📚

## 프로젝트 개요
- **프로젝트명**: MyBoard REST API
- **기술스택**: Spring Boot 3.5.3, JPA, MySQL, Spring Security
- **목표**: 게시판 REST API 개발 및 학습

---

## 개발 진행 상황

### 2025년 9월

#### 2025-09-27 (금)
- [x] Spring Boot 프로젝트 초기 설정 완료
- [x] JPA Entity 설계 (Board, Comment, Member)
- [x] 기본 CRUD API 구현
- [x] Spring Security 설정
- [x] Swagger 문서화 설정
- [ ] JWT 인증 구현 (진행중)
- [ ] 댓글 기능 완성
- [ ] 테스트 코드 작성

**오늘의 학습**
- JPA 연관관계 매핑 복습
- Spring Security CORS 설정
- Record 클래스를 활용한 DTO 패턴

**내일 할 일**
- [ ] JWT 토큰 인증 로직 완성
- [ ] 댓글/대댓글 API 구현
- [ ] Exception Handler 보완

---

## 학습 노트 📝

### Spring Boot 3.x 주요 변경사항
- Jakarta EE 네임스페이스 사용 (`javax` → `jakarta`)
- Spring Security 6.x 설정 방식 변경
- SpringDoc OpenAPI 3.x 사용

### JPA 연관관계 팁
- `@ManyToOne`에서 LAZY 로딩 기본 사용
- 양방향 연관관계 시 무한참조 주의
- `orphanRemoval = true` 활용한 자동 삭제

### 개발 환경
- **IDE**: IntelliJ IDEA
- **Java**: 21
- **Database**: MySQL 8.0
- **Build Tool**: Maven

---

## 트러블슈팅 🔧

### 해결된 이슈
1. **CORS 설정 문제**
    - 문제: 프론트엔드에서 API 호출 시 CORS 에러
    - 해결: SecurityConfig에서 상세한 CORS 설정 추가

2. **JPA Entity 매핑 오류**
    - 문제: Comment 엔티티의 self-reference 매핑 이슈
    - 해결: `@JoinColumn` 명시적 설정

### 현재 진행중 이슈
- JWT 토큰 검증 로직 구현 필요
- 게시글 조회수 증가 로직 최적화 필요

---

## 다음 마일스톤 🎯

### 1단계 (이번 주)
- [ ] 인증/인가 완성
- [ ] 댓글 CRUD 완성
- [ ] 기본 테스트 코드 작성

### 2단계 (다음 주)
- [ ] 파일 업로드 기능
- [ ] 페이징 처리
- [ ] 검색 기능

### 3단계 (추후)
- [ ] Redis 캐싱 적용
- [ ] 성능 최적화
- [ ] Docker 컨테이너화

---

## 참고 자료 📖
- [Spring Boot 공식 문서](https://spring.io/projects/spring-boot)
- [Spring Security Reference](https://docs.spring.io/spring-security/reference/)
- [JPA 공식 문서](https://spring.io/projects/spring-data-jpa)

---

**마지막 업데이트**: 2025-09-27