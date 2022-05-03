package com.attendance.management.system.services;


import com.attendance.management.system.bean.UserBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserServices extends UserDetailsService {
    UserBean findByFirstName(String userName);
    UserBean save(UserBean userBean);
    List<UserBean> findAll();
    void deleteUser(UserBean userBean);
    Optional<UserBean> findById(Long id);
}
