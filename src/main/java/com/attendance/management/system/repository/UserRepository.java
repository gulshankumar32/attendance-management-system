package com.attendance.management.system.repository;

import com.attendance.management.system.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserBean,Long> {
    UserBean findByFirstName(String userName);
    UserBean findByFirstNameAndStatus(String userName, Boolean status);
    List<UserBean> findByStatus(Boolean status);
}
