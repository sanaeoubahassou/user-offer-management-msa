package com.example.projectpfe.pojo.req;

import com.example.projectpfe.model.UserRole;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserUpdateReq {
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
