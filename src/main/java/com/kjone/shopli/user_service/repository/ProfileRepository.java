package com.kjone.shopli.user_service.repository;


import com.kjone.shopli.user_service.domain.user.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
