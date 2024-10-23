package com.example.myboard.post.controller;

import com.example.myboard.post.model.PostRequest;
import com.example.myboard.post.model.PostResponse;
import com.example.myboard.post.model.PostViewRequest;
import com.example.myboard.post.model.PostViewResponse;
import com.example.myboard.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping
    public PostResponse create(@Valid @RequestBody PostRequest postRequest) {
        return postService.create(postRequest);
    }

    @PostMapping("/view")
    public PostViewResponse view(@Valid @RequestBody PostViewRequest postRequest) {
        return postService.view(postRequest);
    }

    @GetMapping("/all")
    public List<PostViewResponse> getAll() {
        return postService.all();
    }

    @PostMapping("/delete")
    public void delete(@Valid @RequestBody PostViewRequest postViewRequest) {
        postService.delete(postViewRequest);
    }

    @GetMapping("/{boardId}/all")
    public List<PostViewResponse> allByBoardId(@PathVariable Long boardId) {
        return postService.allByBoardId(boardId);
    }
}
