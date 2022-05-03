package com.attendance.management.system.services;

import com.attendance.management.system.bean.AttendanceBean;
import com.attendance.management.system.bean.UserBean;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface AttendanceService {
    AttendanceBean findByUserBean(UserBean userBean);
    AttendanceBean findByCurrenntDate(Date date,UserBean userBean);
    public void saveAttendance(AttendanceBean attendanceBean);
    List<AttendanceBean> attendanceBeanList();
}
