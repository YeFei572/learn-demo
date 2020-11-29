package com.dikar.redisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 2892248514883451461L;
    private Long id;
    private int age;
    private String realName;

}
