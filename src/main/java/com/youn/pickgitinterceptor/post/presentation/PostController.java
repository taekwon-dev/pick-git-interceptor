package com.youn.pickgitinterceptor.post.presentation;

import com.youn.pickgitinterceptor.config.auth_interceptor_register.ForOnlyLoginUser;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PostController {

    @ForOnlyLoginUser
    @PostMapping("/posts/{postId}")
    public void update() {
        System.out.println("포스트 수정 API - @ForOnlyLoginUser");
    }

    @ForOnlyLoginUser
    @DeleteMapping("/posts/{postId}")
    public void delete() {
        System.out.println("포스트 삭제 API - @ForOnlyLoginUser");
    }
}
