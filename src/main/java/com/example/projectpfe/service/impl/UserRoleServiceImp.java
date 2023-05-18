package com.example.projectpfe.service.impl;

import com.example.projectpfe.dto.UserRoleDto;
import com.example.projectpfe.exception.ExceptionApi;
import com.example.projectpfe.exception.PayLoadExceptionItem;
import com.example.projectpfe.mapper.DataMapper;
import com.example.projectpfe.model.UserRole;
import com.example.projectpfe.pojo.req.UserRoleReq;
import com.example.projectpfe.repository.UserRoleRepository;
import com.example.projectpfe.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class UserRoleServiceImp implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;
    @Autowired
    DataMapper dataMapper;


    @Override
    public UserRole createUserRole(UserRoleReq userRoleReq) {
       UserRole userRole = new UserRole();
       userRole.setRole(userRoleReq.getRole());
       userRoleRepository.save(userRole);
       return userRole;
    }

    @Override
    public UserRoleDto getUserRolebyId(Long id) {
        return  dataMapper.userRole2dto(userRoleRepository
                .findByIdAndDeletedIsFalse(id).orElseThrow(
                        () ->{
                            throw new ExceptionApi(PayLoadExceptionItem.User_Role_Not_Found);
                        }
                )
        );

    }

    @Override
    public Set<UserRoleDto> allUserRolles() {
        Set<UserRole> userRoles =userRoleRepository.findByDeletedIsFalse();
        if(userRoles == null){
            return null;
        }
        return dataMapper.userRoleSet2dto(userRoles);
    }

}