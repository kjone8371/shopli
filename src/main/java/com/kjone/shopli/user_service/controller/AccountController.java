package com.kjone.shopli.user_service.controller;


import com.kjone.shopli.user_service.domain.request.ProfileRequest;
import com.kjone.shopli.user_service.domain.request.SignRequest;
import com.kjone.shopli.user_service.domain.response.ProfileResponse;
import com.kjone.shopli.user_service.domain.response.SignResponse;
import com.kjone.shopli.user_service.domain.role.Authority;
import com.kjone.shopli.user_service.domain.user.Profile;
import com.kjone.shopli.user_service.domain.user.User;
import com.kjone.shopli.user_service.security.cookie.CookieProvider;
import com.kjone.shopli.user_service.security.jwt.JwtProvider;
import com.kjone.shopli.user_service.service.UserService;
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

    // ---------------------------------------------------------------------------------------------------------
    // 프로필 엔드포인트

    // 프로필 생성
    @PostMapping("/{id}/profile")
    public ResponseEntity<User> createProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        try {
            Profile profile = new Profile();
            profile.setNickname(profileRequest.getNickname());
            profile.setImage(profileRequest.getImage());
            profile.setPhone(profileRequest.getPhone());
            profile.setMy_post(profileRequest.getMy_post());

            User user = userService.createProfile(id, profile);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 업데이트 프로필
    @PutMapping("/{id}/profile")
    public ResponseEntity<User> updateProfile(@PathVariable Long id, @RequestBody ProfileRequest profileRequest) {
        try {
            Profile profile = new Profile();
            profile.setNickname(profileRequest.getNickname());
            profile.setImage(profileRequest.getImage());
            profile.setPhone(profileRequest.getPhone());
            profile.setMy_post(profileRequest.getMy_post());

            User user = userService.updateProfile(id, profile);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // 잠깐의 오류가 있습니다.

    // 프로필 정보 가져오기
    @GetMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<ProfileResponse> getProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        try {
            ProfileResponse profileResponse = userService.getProfile(userId, profileId);
            if (profileResponse != null) {
                return ResponseEntity.ok(profileResponse);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 프로필 삭제
    @DeleteMapping("/{userId}/profile/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Long userId, @PathVariable Long profileId) {
        try {
            userService.deleteProfile(userId, profileId);
            return new ResponseEntity<>("프로필이 성공적으로 삭제되었습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("프로필 삭제에 실패했습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // JWT 테스트 엔드포인트
    @GetMapping("/test-jwt")
    public ResponseEntity<String> testJwt(@RequestParam String token) {
        boolean isValid = jwtProvider.validateToken(token);
        if (isValid) {
            return new ResponseEntity<>("JWT 토큰이 유효합니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("JWT 토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED);
        }
    }

}

