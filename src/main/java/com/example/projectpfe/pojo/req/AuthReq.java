package com.example.projectpfe.pojo.req;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
public class AuthReq {
    @NotBlank(message = "Enter the egg")
    @Pattern(regexp = "^[J|L]\\d{7}",message = "the egg must start by J or L and contains 7 numbers")
    private String igg;

    @NotBlank(message = "Enter the password")
    private String password;


}
