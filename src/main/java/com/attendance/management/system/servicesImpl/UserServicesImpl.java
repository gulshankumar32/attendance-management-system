package com.attendance.management.system.servicesImpl;

import com.attendance.management.system.bean.UserBean;
import com.attendance.management.system.repository.UserRepository;
import com.attendance.management.system.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserBean userBean=userRepository.findByFirstNameAndStatus(s,true);
        if(userBean==null){
            throw new UsernameNotFoundException("Invalid user name or password");
        }
        return new org.springframework.security.core.userdetails.User(userBean.getFirstName(),userBean.getPassword(), Collections.emptyList());
    }

    @Override
    public UserBean findByFirstName(String userName) {
        return userRepository.findByFirstName(userName);
    }

    @Override
    public UserBean save(UserBean userBean) {
        return userRepository.save(userBean);
    }


    @Override
    public List<UserBean> findAll() {
        return userRepository.findByStatus(true);
    }

    @Override
    public void deleteUser(UserBean userBean) {
        userRepository.save(userBean);
    }

    @Override
    public Optional<UserBean> findById(Long id) {
        return userRepository.findById(id);
    }
}
