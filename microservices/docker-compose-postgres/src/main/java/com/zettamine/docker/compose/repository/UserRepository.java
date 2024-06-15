package com.zettamine.docker.compose.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.docker.compose.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
