package com.jwt.angular.springjwtangular;

import com.jwt.angular.springjwtangular.dao.TaskRepository;
import com.jwt.angular.springjwtangular.entities.AppRole;
import com.jwt.angular.springjwtangular.entities.AppUser;
import com.jwt.angular.springjwtangular.entities.Task;
import com.jwt.angular.springjwtangular.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class SpringjwtangularApplication implements CommandLineRunner {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private AccountService accountService;


    public static void main(String[] args) {
        SpringApplication.run(SpringjwtangularApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        taskRepository.save(new Task(null, "task1"));
        taskRepository.save(new Task(null, "task2"));
        taskRepository.save(new Task(null, "task3"));
        taskRepository.save(new Task(null, "task4"));
        taskRepository.save(new Task(null, "task5"));
        taskRepository.findAll()
                .forEach(t -> {
                    System.out.println(t.toString());
                });

        accountService.saveUser(new AppUser(null, "admin", "1234", null));
        accountService.saveUser(new AppUser(null, "user", "1234", null));
        accountService.saveRole(new AppRole(null, "ADMIN"));
        accountService.saveRole(new AppRole(null, "USER"));
        accountService.addRoleToUser("admin", "ADMIN");
        accountService.addRoleToUser("admin", "USER");
        accountService.addRoleToUser("user", "USER");


    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
