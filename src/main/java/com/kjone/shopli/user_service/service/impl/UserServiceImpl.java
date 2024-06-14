package com.kjone.shopli.user_service.service.impl;

import com.kjone.shopli.content_service.domain.entity.CartItem;
import com.kjone.shopli.user_service.domain.request.SignRequest;
import com.kjone.shopli.user_service.domain.response.ProfileResponse;
import com.kjone.shopli.user_service.domain.response.SignResponse;
import com.kjone.shopli.user_service.domain.role.Authority;
import com.kjone.shopli.user_service.domain.user.Profile;
import com.kjone.shopli.user_service.domain.user.User;
import com.kjone.shopli.user_service.repository.ProfileRepository;
import com.kjone.shopli.user_service.repository.UserRepository;
import com.kjone.shopli.user_service.security.cookie.CookieProvider;
import com.kjone.shopli.user_service.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final CookieProvider cookieProvider;

    @Override
    @Transactional
    public boolean signUp(SignRequest request) throws Exception{

        try {
            // 이메일 중복 확인
            Optional<User> existingUser = userRepository.findByEmail(request.getEmail());
            if (existingUser.isPresent()) {
                throw new Exception("이미 사용 중인 이메일입니다.");
            }
            User user = User.builder()
                    .email(request.getEmail())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .username(request.getUsername())
                    .age(request.getAge())
                    .image(request.getImage())
                    .roles(Collections.singleton(Authority.USER)) // roles 필드를 설정합니다.
                    .build();
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }
        return true;
    }



    // 로그인 메서드
    @Override
    public SignResponse signIn(SignRequest signRequest) throws Exception {
        User user = userRepository.findByEmail(signRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (passwordEncoder.matches(signRequest.getPassword(), user.getPassword())) {
            return new SignResponse(user);
        } else {
            throw new RuntimeException("Invalid credentials");
        }
    }

    @Override
    public void logout(HttpServletResponse response) {
        Cookie access = cookieProvider.of(cookieProvider.removeAccessTokenCookie());
        Cookie refresh = cookieProvider.of(cookieProvider.removeRefreshTokenCookie());

        response.addCookie(access);
        response.addCookie(refresh);
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));
        userRepository.delete(user);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll().stream()
                .map(user -> new User(
                        user.getId(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getUsername(),
                        user.getAge(),
                        user.getCreateTime(),
                        user.getUpdateTime(),
                        user.getRoles()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public User createProfile(Long userId, Profile profile) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        profile.setUser(user);
        profile.setCreateTime(LocalDateTime.now());
        profileRepository.save(profile);

        user.getProfiles().add(profile);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User updateProfile(Long userId, Profile profileDetails) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        Profile profile = profileRepository.findById(profileDetails.getId())
                .orElseThrow(() -> new Exception("프로필을 찾을 수 없습니다."));

        profile.setNickname(profileDetails.getNickname());
        profile.setImage(profileDetails.getImage());
        profile.setPhone(profileDetails.getPhone());
        profile.setUpdateTime(LocalDateTime.now());
        profileRepository.save(profile);

        return user;
    }

    @Override
    public void deleteProfile(Long userId, Long profileId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        Profile profileToDelete = user.getProfiles().stream()
                .filter(profile -> profile.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new Exception("프로필을 찾을 수 없습니다."));

        user.getProfiles().remove(profileToDelete);
        userRepository.save(user);
    }
    @Override
    public ProfileResponse getProfile(Long userId, Long profileId) throws Exception {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        Profile profile = user.getProfiles().stream()
                .filter(p -> p.getId().equals(profileId))
                .findFirst()
                .orElseThrow(() -> new Exception("프로필을 찾을 수 없습니다."));

        List<CartItem> cartItems = user.getCartItems();
        return ProfileResponse.from(profile, cartItems);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
