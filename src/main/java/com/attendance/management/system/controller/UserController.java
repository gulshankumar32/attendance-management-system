package com.attendance.management.system.controller;


import com.attendance.management.system.bean.AttendanceBean;
import com.attendance.management.system.bean.UserBean;
import com.attendance.management.system.dto.UserDTO;
import com.attendance.management.system.services.AttendanceService;
import com.attendance.management.system.services.UserServices;
import com.attendance.management.system.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    public static String userName="";
    @Autowired
    private UserServices userServices;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private AttendanceService attendanceService;

    @ModelAttribute("user")
    public UserDTO userDTO(){
        return  new UserDTO();
    }

    @GetMapping("login")
    public String login() {
        return "login";
    }

    @GetMapping
    public String home() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username="";
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
            System.out.println("id12=="+UserController.userName);
        } else {
            username = principal.toString();
            System.out.println(UserController.userName);
        }
        if(username.equals("admin")){
            UserController.userName=username;
            return "redirect:/home";
        }else{
            UserController.userName=username;
            return "redirect:/attendance";
        }
    }

    @GetMapping("/home")
    public String home(Model model){
        List<UserDTO> userDTOS=UserTransformer.userDTOList(userServices.findAll());
        model.addAttribute("userList",userDTOS);
        return "index";
    }

    @GetMapping("/addEmployee")
    public String addEmployee(){
        return "add-employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("user") UserDTO userDTO) {
        UserBean userBean= UserTransformer.getUserBeanByDTO(userDTO);
        userBean.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userBean.setStatus(true);
        userServices.save(userBean);
        return "redirect:/addEmployee?success";
    }

    @GetMapping("deleteEmployee/{id}")
    public String deleteCasting(@PathVariable("id") long id, Model model){

        UserBean userBean = userServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));

        userBean.setStatus(false);
        userServices.deleteUser(userBean);
        return "redirect:/home";
    }

    @GetMapping("editEmployee/{id}")
    public String editCasting(@PathVariable("id") long id, Model model){

        UserBean userBean = userServices.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + id));
        UserDTO userDTO=UserTransformer.getUserDTO(userBean);

        model.addAttribute("user",userDTO);
        return "update-employee";
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("user") UserDTO userDTO) {
        UserBean userBean = userServices.findById(Long.parseLong(userDTO.getId()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid User Id:" + userDTO.getId()));
        UserBean userBean1= UserTransformer.getUserBeanByDTO(userDTO);
        userBean1.setPassword(userBean.getPassword());
        userBean.setStatus(true);
        userServices.save(userBean1);
        return "redirect:/home";
    }
}
