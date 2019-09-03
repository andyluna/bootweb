package com.dtxytech.boot.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @NAME : bootweb com.dtxytech.boot.bean
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 19:20
 * @DESC : 仅仅用来演示 mouse查询部分字段 组装的问题
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MouseDTO {

    private Integer id;

    private String name;

    private String brand;
}
