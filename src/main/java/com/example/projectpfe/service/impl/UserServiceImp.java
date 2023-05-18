package com.example.projectpfe.service.impl;
import com.example.projectpfe.dto.UserDto;
import com.example.projectpfe.exception.ExceptionApi;
import com.example.projectpfe.exception.PayLoadExceptionItem;
import com.example.projectpfe.mapper.DataMapper;
import com.example.projectpfe.model.User;
import com.example.projectpfe.pojo.emuns.RoleEnum;
import com.example.projectpfe.pojo.req.UserReq;
import com.example.projectpfe.pojo.req.UserUpdateReq;
import com.example.projectpfe.repository.AuthRepository;
import com.example.projectpfe.repository.UserRepository;
import com.example.projectpfe.repository.UserRoleRepository;
import com.example.projectpfe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserServiceImp implements UserService {


    @Autowired
    UserRepository userRepository;


    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    DataMapper dataMapper;

    @Autowired
    AuthRepository authRepository;

    @Override
    public UserDto createUser(UserReq userReq) {

        userRepository.findByIggAndAndDeletedIsFalse(userReq.getIgg().trim())
                .ifPresent(
                        e ->{
                            throw  new ExceptionApi(PayLoadExceptionItem.Igg_Already_Used);
                        }
                );

        User user = new User();
        user.setIgg(userReq.getIgg());
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setCountry(userReq.getCountry());
        user.setCity(userReq.getCity());
        userRepository.save(user);
        user.setUserRoles(userReq.getUserRoles());
        userRepository.save(user);
        return  dataMapper.user2dto(user);


         }

    @Override
    public UserDto getUserbyIgg(String igg) {

        return  dataMapper.user2dto(userRepository
                .findByIggAndAndDeletedIsFalse(igg).orElseThrow(
                        () ->{
                            throw new ExceptionApi(PayLoadExceptionItem.User_Not_Found);
                        }
                )
        );
    }

    @Override
    public UserDto updateUser(UserUpdateReq userReq, String igg) {
        User user =userRepository.getUserByIgg(igg);
        user.setFirstName(userReq.getFirstName());
        user.setLastName(userReq.getLastName());
        user.setCountry(userReq.getCountry());
        user.setCity(userReq.getCity());
        userRepository.save(user);
        user.setUserRoles(userReq.getUserRoles());
        userRepository.save(user);
        return  dataMapper.user2dto(user);
    }
    @Override
    public Page<UserDto> allUsers(int size, int page) {
        Page pageUsers =userRepository.allUers(PageRequest.of(page, size));

        List<UserDto> listUsers = dataMapper.userList2dto(pageUsers.getContent());
        return new PageImpl<>(listUsers,pageUsers.getPageable(),pageUsers.getTotalElements());
        //return dataMapper.userSet2dto(users);

    }

    @Override
    public void deleteUser(String igg) {
        userRepository
                .findByIggAndAndDeletedIsFalse(igg)
                .ifPresent(
                        h -> {
                            h.setDeleted(true);
                            userRepository.save(h);
                        }
                );
    }

    @Override
    public Set<UserDto> filter(String igg, String firstName, String lastName,String city, RoleEnum roleEnum) {
        Set<User> users = userRepository.findAllByDeletedIsFalseOrderByLastName();

        if(igg != null){
            users = users.stream()
                    .filter(p ->p.getIgg().contains(igg))
                    .collect(Collectors.toSet());
        }

        if(firstName != null){
            users = users.stream()
                    .filter(p ->p.getFirstName().contains(firstName))
                    .collect(Collectors.toSet());
        }
        if(lastName != null){
            users = users.stream()
                    .filter(p ->p.getLastName().contains(lastName))
                    .collect(Collectors.toSet());
        }
        if(city != null){
            users = users.stream()
                    .filter(p ->p.getCity().contains(city))
                    .collect(Collectors.toSet());
        }
        if(roleEnum != null){
            users = users.stream()
                    .filter(p ->p.getUserRoles().stream().anyMatch(r ->r.getRole().equals(roleEnum)))
                    .collect(Collectors.toSet());
        }


        return dataMapper.userSet2dto(users);
    }

}