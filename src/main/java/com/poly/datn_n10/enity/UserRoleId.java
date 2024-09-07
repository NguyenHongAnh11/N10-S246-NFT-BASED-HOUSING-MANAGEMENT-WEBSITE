package com.poly.datn_n10.enity;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserRoleId implements Serializable {
    private Integer user;
    private Integer role;
}
