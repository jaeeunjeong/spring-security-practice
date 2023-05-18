package com.HIGHERX.controller.user;

import com.HIGHERX.controller.request.user.UserJoinRequest;
import com.HIGHERX.controller.response.Response;
import com.HIGHERX.controller.response.user.UserResponse;
import com.HIGHERX.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Void> join(@RequestBody UserJoinRequest req) {
        userService.createUser(req);
        return Response.success();
    }

    @GetMapping
    public Response<UserResponse> readUser(Authentication authentication) {
        return Response.success(userService.readUser(authentication.getName()));
    }

    @DeleteMapping
    public Response<Void> deleteUser(Authentication authentication) {
        userService.deleteUser(authentication.getName());
        return Response.success();
    }

}
