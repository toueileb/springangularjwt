package com.jwt.angular.springjwtangular.service;

import com.jwt.angular.springjwtangular.entities.AppRole;
import com.jwt.angular.springjwtangular.entities.AppUser;

public interface AccountService {
    public AppUser saveUser(AppUser user);
    public AppRole saveRole(AppRole role);
    public void addRoleToUser(String userName, String roleName);
    public AppUser findUserByUsername(String username);

}
