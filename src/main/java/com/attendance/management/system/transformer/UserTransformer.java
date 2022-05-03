package com.attendance.management.system.transformer;

import com.attendance.management.system.bean.UserBean;
import com.attendance.management.system.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;

public class UserTransformer {
    public static UserBean getUserBeanByDTO(UserDTO userDTO){
        UserBean userBean=new UserBean();
        if(userDTO.getId()!=null && !userDTO.getId().equals("")){
            userBean.setId(Long.parseLong(userDTO.getId()));
        }
        if(userDTO.getFirstName()!=null && !userDTO.getFirstName().equals("")){
            userBean.setFirstName(userDTO.getFirstName());
        }
        if(userDTO.getLastName()!=null && !userDTO.getLastName().equals("")){
            userBean.setLastName(userDTO.getLastName());
        }
        if(userDTO.getEmail()!=null && !userDTO.getEmail().equals("")){
            userBean.setEmail(userDTO.getEmail());
        }
        if(userDTO.getEmployeeNo()!=null && !userDTO.getEmployeeNo().equals("")){
            userBean.setEmployeeNo(userDTO.getEmployeeNo());
        }
        if(userDTO.getPassword()!=null && !userDTO.getPassword().equals("")){
            userBean.setPassword(userDTO.getPassword());
        }

        return userBean;
    }

    public static UserDTO getUserDTO(UserBean userBean){
        UserDTO userDTO=new UserDTO();
        if(userBean.getId()!=null && !userBean.getId().equals("")){
            userDTO.setId(userBean.getId().toString());
        }
        if(userBean.getFirstName()!=null && !userBean.getFirstName().equals("")){
            userDTO.setFirstName(userBean.getFirstName());
        }
        if(userBean.getLastName()!=null && !userBean.getLastName().equals("")){
            userDTO.setLastName(userBean.getLastName());
        }
        if(userBean.getEmail()!=null && !userBean.getEmail().equals("")){
            userDTO.setEmail(userBean.getEmail());
        }
        if(userBean.getEmployeeNo()!=null && !userBean.getEmployeeNo().equals("")){
            userDTO.setEmployeeNo(userBean.getEmployeeNo());
        }
        if(userBean.getPassword()!=null && !userBean.getPassword().equals("")){
            userDTO.setPassword(userBean.getPassword());
        }

        return userDTO;
    }

    public static List<UserDTO> userDTOList(List<UserBean> userBeans){
        List<UserDTO> userDTOS=new ArrayList<>();
        for(UserBean userBean:userBeans){
            userDTOS.add(UserTransformer.getUserDTO(userBean));
        }
        return userDTOS;
    }
}
