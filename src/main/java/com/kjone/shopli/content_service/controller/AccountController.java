package com.kjone.shopli.content_service.controller;


import com.kjone.shopli.content_service.domain.request.SignRequest;
import com.kjone.shopli.content_service.domain.response.SignResponse;
import com.kjone.shopli.content_service.domain.role.Authority;
import com.kjone.shopli.content_service.domain.user.User;
import com.kjone.shopli.content_service.security.cookie.CookieProvider;
import com.kjone.shopli.content_service.security.jwt.JwtProvider;
import com.kjone.shopli.content_service.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class AccountController {
    private final JwtProvider jwtProvider;
    private final CookieProvider cookieProvider;
    private final UserService userService;


    // Post 엔드포인트
    @PostMapping("/signin")
    public ResponseEntity<SignResponse> signIn(@RequestParam String email, @RequestParam String password, HttpServletResponse response) throws Exception {
        SignRequest signRequest = new SignRequest();
        signRequest.setEmail(email);
        signRequest.setPassword(password);

        SignResponse signResponse = userService.signIn(signRequest);
        Set<Authority> roles = signResponse.getRoles();

        //JWT 토큰 생성
        String token = jwtProvider.createToken(signRequest.getEmail(), roles);

        //쿠키에 JWT 토큰을 설정
        cookieProvider.createCookie(response, token, 3600); // 1시간

        return new ResponseEntity<>(signResponse, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignRequest signRequest) throws Exception {

        try {
            boolean result = userService.signUp(signRequest);
            if (result) {
                return new ResponseEntity<>("회원가입이 완료되었습니다.", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("회원가입에 실패했습니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/signout")
    public ResponseEntity<Void> signOut(@RequestParam String email, HttpServletResponse response) {
        System.out.println("Logging out user with email: " + email);
        cookieProvider.clearCookie(response);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteUser(@RequestParam String email) {
        try {
            userService.deleteUserByEmail(email);
            return new ResponseEntity<>("사용자가 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/{id}/profile")
    public ResponseEntity<User> createProfile(@PathVariable Long id, @RequestBody SignRequest signRequest) {
        try {
            User user = userService.createProfile(id, signRequest);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody SignRequest signRequest) {
        try {
            User user = userService.updateProfile(id, signRequest);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
