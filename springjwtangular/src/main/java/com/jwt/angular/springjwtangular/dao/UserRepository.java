package com.jwt.angular.springjwtangular.dao;

import com.jwt.angular.springjwtangular.entities.AppRole;
import com.jwt.angular.springjwtangular.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
