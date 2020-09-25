package com.cloud.eurekaclient.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Customer {

    private Integer id;

    private String name;

    private Integer age;
}
