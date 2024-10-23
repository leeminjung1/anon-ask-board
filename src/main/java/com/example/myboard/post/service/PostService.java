package com.example.myboard.post.service;

import com.example.myboard.post.db.PostEntity;
import com.example.myboard.post.db.PostRepository;
import com.example.myboard.post.model.PostRequest;
import com.example.myboard.post.model.PostResponse;
import com.example.myboard.post.model.PostViewRequest;
import com.example.myboard.post.model.PostViewResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponse create(PostRequest postRequest) {
        PostEntity post = PostEntity.builder()
                .boardId(1L)
                .content(postRequest.getContent())
                .email(postRequest.getEmail())
                .password(postRequest.getPassword())
                .userName(postRequest.getUserName())
                .title(postRequest.getTitle())
                .status("REGISTERED")
                .postedAt(LocalDateTime.now())
                .build();


        PostEntity saved = postRepository.save(post);
        return PostResponse.builder()
                .status(saved.getStatus())
                .id(saved.getId())
                .build();
    }

    @Transactional(readOnly = true)
    public List<PostViewResponse> allByBoardId(Long boardId) {
        return postRepository.findAllByBoardIdAndStatusOrderByIdDesc(boardId, "REGISTERED")
            .stream()
            .map(post -> PostViewResponse
                .builder()
                .userName(post.getUserName())
                .id(post.getId())
                .title(post.getTitle())
                .email(post.getEmail())
                .postedAt(post.getPostedAt())
                .content(post.getContent())
                .build())
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PostViewResponse view(@Valid PostViewRequest postRequest) {
        PostEntity post = postRepository.findFirstByIdAndStatusOrderByIdDesc(postRequest.getPostId(), "REGISTERED")
            .map(it -> {
                if (!it.getPassword().equals(postRequest.getPassword())) {
                    //                        throw new RuntimeException("비밀번호가 일치하지 않습니다.");
                    var format = "패스워드가 일치하지 않습니다. %s vs %s";
                    throw new RuntimeException(String.format(format, it.getPassword(), postRequest.getPassword()));
                }
                return it;
            })
            .orElseThrow(() -> new RuntimeException("해당 게시글이 존재하지 않습니다. : " + postRequest.getPostId()));

        return PostViewResponse.builder()
            .id(post.getId())
            .postedAt(post.getPostedAt())
            .content(post.getContent())
            .email(post.getEmail())
            .title(post.getTitle())
            .userName(post.getUserName())
            .build();
    }

    public List<PostViewResponse> all() {
        return postRepository.findAllByStatusOrderByIdDesc("REGISTERED")
                .stream()
                .map(post -> PostViewResponse
                        .builder()
                        .userName(post.getUserName())
                        .id(post.getId())
                        .title(post.getTitle())
                        .email(post.getEmail())
                        .postedAt(post.getPostedAt())
                        .content(post.getContent())
                        .build())
                .collect(Collectors.toList());
    }

    public void delete(@Valid PostViewRequest postViewRequest) {
        PostEntity post = postRepository.findById(postViewRequest.getPostId())
                .map(it->{
                    if (!it.getPassword().equals(postViewRequest.getPassword())) {
                        throw new RuntimeException("패스워드가 일치하지 않습니다.");
                    }
                    it.setStatus("UNREGISTERED");
                    postRepository.save(it);
                    return it;
                })
                .orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다." + postViewRequest.getPostId()));
    }
}
