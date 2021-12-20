package com.jb.targil_spring1.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDetails {
    private String email;
    private String password;
    private String userType;

}
