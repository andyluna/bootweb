package com.dtxytech.boot.entity.embbed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:18
 * @DESC : 城市邮政编码
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CityZipCode {

    private String zipCode;

    private String zipName;

}
