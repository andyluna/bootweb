package com.andy.hibernate.entity.embbeded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

/**
 * @NAME : bootweb com.andy.hibernate.entity.embbeded
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 17:08
 * @DESC :
 */
@Getter
@Setter
@Embeddable
public class Address {
    private String street;
    private String detailZz;

    //下面这个注解可以不写写 因为PostCode已经标注了 Embeddable
    @Embedded
    private PostCode postCode;

}
