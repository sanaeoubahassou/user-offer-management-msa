package com.example.projectpfe.pojo.req;

import com.example.projectpfe.model.UserRole;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserReq {
    @NotBlank(message = "Enter the egg")
    @Pattern(regexp = "^[J|L]\\d{7}",message = "the egg must start by J or L and contains 7 numbers")
    private String igg;
    @NotBlank(message = "Enter the first name")
    @Pattern(regexp = "^[a-zA-Z ._-]{2,}$",message = "the first name must be  characters and min 2 caracters")
    private String firstName;

    @NotBlank(message = "Enter the last name")
    @Pattern(regexp = "^[a-zA-Z ._-]{2,}$",message = "the last name must be  characters and min 2 caracters")
    private String lastName;
    @NotBlank(message = "Enter the  country")
    private  String country;
    @NotBlank(message = "Enter the  city")
    private  String city;
    private Set<UserRole> userRoles = new HashSet<>();




}
