package com.example.myboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {

    // select * from post where id=? and status=? order by id desc limit 1
    public Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    // 모든 게시글 조회 시 삭제되지 않은 글만 가져오기
    public List<PostEntity> findAllByStatusOrderByIdDesc(String status);

    // 특정 boardId에 해당하면서 삭제되지 않은 글만 가져오는 JPQL 쿼리
    @Query("SELECT p FROM post p WHERE p.boardId = :boardId AND p.status = :status ORDER BY p.id DESC")
    public List<PostEntity> findAllByBoardIdAndStatusOrderByIdDesc(@Param("boardId") Long boardId, @Param("status") String status);
}