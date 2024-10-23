package com.example.myboard.post.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    Optional<PostEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, String status);

    List<PostEntity> findAllByStatusOrderByIdDesc(String status);

    List<PostEntity> findAllByBoardIdAndStatusOrderByIdDesc(Long boardId, String status);
}
