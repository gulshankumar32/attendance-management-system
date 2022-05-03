package com.attendance.management.system.repository;

import com.attendance.management.system.bean.AttendanceBean;
import com.attendance.management.system.bean.UserBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceBean,Long> {
    AttendanceBean getByCreatedDateAndUserBean(Date date, UserBean userBean);
    AttendanceBean getByUserBean(UserBean userBean);
}
