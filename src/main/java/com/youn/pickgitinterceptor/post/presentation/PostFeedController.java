package com.youn.pickgitinterceptor.post.presentation;

import com.youn.pickgitinterceptor.config.auth_interceptor_register.ForLoginAndGuestUser;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostFeedController {

    @ForLoginAndGuestUser
    @GetMapping("/posts/{postId}")
    public void fetchOne() {
        System.out.println("포스트 상세 조회 API - @ForLoginAndGuestUser");
    }
}
