package com.zettamine.boot.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.boot.security.entity.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo,Integer>{

	Optional<UserInfo> findByName(String username);

}
