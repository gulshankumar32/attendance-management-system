package com.attendance.management.system.controller;

import com.attendance.management.system.bean.AttendanceBean;
import com.attendance.management.system.bean.UserBean;
import com.attendance.management.system.config.FormatData;
import com.attendance.management.system.dto.AttendanceDTO;
import com.attendance.management.system.dto.UserDTO;
import com.attendance.management.system.services.AttendanceService;
import com.attendance.management.system.services.UserServices;
import com.attendance.management.system.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class AttendanceController {
    @Autowired
    private UserServices userServices;

    @Autowired
    private AttendanceService attendanceService;


    @ModelAttribute("userDTO")
    public UserDTO userDTO(){
        return  new UserDTO();
    }

    @ModelAttribute("attendance")
    public AttendanceDTO attendanceDTO(){
        return  new AttendanceDTO();
    }
    @GetMapping("/attendance")
    public String attendance(Model model){
        String userName=UserController.userName;
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        Date from= null;
        try {
            from = simpleDateFormat.parse(date);
        }catch (Exception ex){
            System.out.println(ex);
        }

        UserBean userBean=userServices.findByFirstName(userName);
        AttendanceBean attendanceBean=attendanceService.findByCurrenntDate(from,userBean);

        UserDTO userDTO= UserTransformer.getUserDTO(userBean);

        System.out.println("attendance==="+attendanceBean);
        boolean bool=false;
        boolean bool1=false;
        if(attendanceBean!=null){
            if(attendanceBean.getCheckOut()!=null){
                bool1=true;
            }else{
                bool=false;
            }
        }else{
            bool=true;
        }
        model.addAttribute("userDTO",userDTO);
        model.addAttribute("attendanceBool",bool);
        model.addAttribute("attendanceBool1",bool1);
        return "EmployeeAttendance";
    }

    @PostMapping("/saveAttendance")
    public String checkInOrCheckOutToday(@ModelAttribute("userDTO") UserDTO userDTO){
        if(userDTO!=null){
            UserBean userBean = userServices.findById(Long.parseLong(userDTO.getId()))
                    .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:"));
            AttendanceBean attendanceBean=new AttendanceBean();
            Date date=new Date();
            String pattern = "yyyy-MM-dd";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date1 = simpleDateFormat.format(new Date());
            Date from= null;
            try {
                from = simpleDateFormat.parse(date1);
            }catch (Exception ex){
                System.out.println(ex);
            }
            String time=FormatData.currentTime();
            String[] timeSplit=time.split(":");
            AttendanceBean attendanceBean1=attendanceService.findByCurrenntDate(from,userBean);
            System.out.println("attendance==="+attendanceBean1);
            if(attendanceBean1!=null){
                System.out.println("i am in if");
                attendanceBean.setId(attendanceBean1.getId());
                attendanceBean.setCreatedDate(attendanceBean1.getCreatedDate());
                attendanceBean.setUserBean(attendanceBean1.getUserBean());
                attendanceBean.setCheckInLate(attendanceBean1.isCheckInLate());
                attendanceBean.setCheckIn(attendanceBean1.getCheckIn());

                String[] countHours=attendanceBean1.getCheckIn().split(":");
                Integer hours=Integer.parseInt(countHours[0])-Integer.parseInt(timeSplit[0]);
                attendanceBean.setHours(hours);
                if(Integer.parseInt(timeSplit[0])<3){
                    attendanceBean.setCheckOutEarly(true);
                    attendanceBean.setCheckOut(time);
                }else{
                    attendanceBean.setCheckOutEarly(false);
                    attendanceBean.setCheckOut(time);
                }
            }else{
                System.out.println("i am in else");
                if(Integer.parseInt(timeSplit[0])>8){
                    attendanceBean.setCreatedDate(FormatData.removeTime(date));
                    attendanceBean.setUserBean(userBean);
                    attendanceBean.setCheckInLate(true);
                    attendanceBean.setCheckIn(time.toString());
                }else{
                    attendanceBean.setCreatedDate(FormatData.removeTime(date));
                    attendanceBean.setUserBean(userBean);
                    attendanceBean.setCheckInLate(false);
                    attendanceBean.setCheckIn(time.toString());
                }
            }
            attendanceService.saveAttendance(attendanceBean);
        }
        return "redirect:/attendance";
    }

    @GetMapping("/checkAttendance")
    public String getAllEmployeeAttendance(Model model){
        List<AttendanceBean> attendanceBeanList=attendanceService.attendanceBeanList();

        model.addAttribute("attendanceList",attendanceBeanList);

        return "check-attendance";
    }
}
