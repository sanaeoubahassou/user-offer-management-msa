package com.example.projectpfe.dto;

import com.example.projectpfe.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    private String igg;
    private String firstName;

    private String lastName;
    private  String country;
    private  String city;
   private Set<UserRole> userRoles;



}
