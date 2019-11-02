package com.jwt.angular.springjwtangular.service;

import com.jwt.angular.springjwtangular.dao.RoleRepository;
import com.jwt.angular.springjwtangular.dao.UserRepository;
import com.jwt.angular.springjwtangular.entities.AppRole;
import com.jwt.angular.springjwtangular.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService{
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());

        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUsername(userName);
        user.getRoles().add(role);

    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
