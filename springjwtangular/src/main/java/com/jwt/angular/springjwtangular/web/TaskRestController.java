package com.jwt.angular.springjwtangular.web;

import com.jwt.angular.springjwtangular.dao.TaskRepository;
import com.jwt.angular.springjwtangular.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TaskRestController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks")
    public List<Task> listTasks(){
        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task saveTask(@RequestBody Task task){
        return taskRepository.save(task);
    }



}
