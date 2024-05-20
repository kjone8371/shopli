package com.kjone.shopli.content_service.service;

import com.kjone.shopli.user_service.domain.request.SignRequest;
import com.kjone.shopli.user_service.domain.response.SignResponse;
import com.kjone.shopli.user_service.domain.user.User;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

public interface UserService {
    public boolean signUp(SignRequest signRequest) throws Exception;
    public SignResponse signIn(SignRequest signRequest) throws Exception;
    public void logout(HttpServletResponse response);
    public void deleteUserByEmail(String email) throws Exception; // 사용자 삭제 메서드 정의
    public List<User> getAllUsers();
    User createProfile(Long userId, SignRequest signRequest) throws Exception;
    User updateProfile(Long userId, SignRequest signRequest) throws Exception;
}
