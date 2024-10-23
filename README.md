# 익명 질문 게시판 프로젝트

이 프로젝트는 **Spring Boot**와 **MySQL**을 사용하여 구현된 **익명 질문 게시판**입니다. 사용자는 익명으로 질문을 등록하고, 답변을 받을 수 있으며, 답변이 달린 질문은 답변 완료로 표시됩니다. 이 프로젝트는 **JPA**와 **Hibernate**를 통해 데이터베이스와 상호작용하며, REST API 방식으로 구현되었습니다.

## 주요 기능

- **익명 질문 등록**: 사용자는 익명으로 질문을 게시할 수 있습니다.
- **질문 목록 보기**: 등록된 질문의 목록을 확인할 수 있습니다.
- **질문 상세 보기**: 등록된 질문의 세부 정보를 확인할 수 있습니다.
- **질문 삭제**: 비밀번호를 입력하고 질문을 삭제할 수 있습니다.
- ~~**답변 작성**: 질문에 대해 여러 개의 답변을 작성할 수 있습니다.~~
- ~~**답변 완료 표시**: 답변이 달린 질문에는 답변 완료 배지가 표시됩니다.~~

## 기술 스택

- **Java 11**
- **Spring Boot 3.3.4**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Lombok**: 보일러플레이트 코드 제거

## 프로젝트 설정

### 1. 요구 사항
- Java 11 이상
- MySQL 서버
- **IntelliJ** IDE

### 2. 데이터베이스 설정

1. **MySQL 데이터베이스 생성**
   ```sql
   CREATE DATABASE myboard CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

2. **application.properties 설정**
   ```properties
   spring.application.name=myboard
   spring.datasource.url=jdbc:mysql://localhost:3306/myboard?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=YOUR_USERNAME
   spring.datasource.password=YOUR_PASSWORD
   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```


## ERD(엔터티 관계 다이어그램)

- Board: 게시판 정보를 저장하는 테이블
- Post: 각 게시글 정보를 저장하는 테이블
- Reply: 게시글에 달리는 답변 정보를 저장하는 테이블