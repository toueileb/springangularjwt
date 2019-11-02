package com.jwt.angular.springjwtangular.dao;

import com.jwt.angular.springjwtangular.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
