package com.kjone.shopli.user_service.service;

import com.kjone.shopli.user_service.domain.request.SignRequest;
import com.kjone.shopli.user_service.domain.response.SignResponse;
import com.kjone.shopli.user_service.domain.user.Profile;
import com.kjone.shopli.user_service.domain.user.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public boolean signUp(SignRequest signRequest) throws Exception;
    public SignResponse signIn(SignRequest signRequest) throws Exception;
    public void logout(HttpServletResponse response);
    public void deleteUserByEmail(String email) throws Exception; // 사용자 삭제 메서드 정의
    public List<User> getAllUsers();
    public User createProfile(Long userId, Profile profile) throws Exception;
    public User updateProfile(Long userId, Profile profileDetails) throws Exception;
    public Profile getProfile(Long userId, Long profileId) throws Exception;
        public Optional<User> getUserById(Long id) ;
}
