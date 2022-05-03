package com.attendance.management.system.servicesImpl;

import com.attendance.management.system.bean.AttendanceBean;
import com.attendance.management.system.bean.UserBean;
import com.attendance.management.system.repository.AttendanceRepository;
import com.attendance.management.system.services.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    AttendanceRepository attendanceRepository;

    @Override
    public AttendanceBean findByUserBean(UserBean userBean) {
        return attendanceRepository.getByUserBean(userBean);
    }

    @Override
    public AttendanceBean findByCurrenntDate(Date date,UserBean userBean) {
        return attendanceRepository.getByCreatedDateAndUserBean(date,userBean);
    }

    @Override
    public void saveAttendance(AttendanceBean attendanceBean) {
        attendanceRepository.save(attendanceBean);
    }

    @Override
    public List<AttendanceBean> attendanceBeanList() {
        return attendanceRepository.findAll();
    }
}
