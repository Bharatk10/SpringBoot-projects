package com.zettamine.boot.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.zettamine.boot.security.entity.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    @Query("select s from UserEntity s where s.username = ?1")
    UserEntity findByUsername(String username);

}
