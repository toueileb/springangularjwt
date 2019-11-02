package com.jwt.angular.springjwtangular.web;

import com.jwt.angular.springjwtangular.entities.AppUser;
import com.jwt.angular.springjwtangular.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AccountRestController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody RegisterForm registerForm) {
        if(!registerForm.getPassword().equals(registerForm.getRepassword()))
            throw new RuntimeException("You must confirm your password");
        AppUser appUser = accountService.findUserByUsername(registerForm.getUsername());
        if(appUser !=null) throw new RuntimeException("This user Already exists !");
        AppUser user =new AppUser();
        user.setUsername(registerForm.getUsername());
        user.setPassword(registerForm.getPassword());
        accountService.saveUser(user);
        accountService.addRoleToUser(registerForm.getUsername(), "USER");
        return user;
    }
}
