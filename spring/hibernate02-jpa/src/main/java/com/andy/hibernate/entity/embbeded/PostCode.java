package com.andy.hibernate.entity.embbeded;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

/**
 * @NAME : bootweb com.andy.hibernate.entity.embbeded
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 17:09
 * @DESC :
 */
@Getter
@Setter
@Embeddable
public class PostCode {
    private String zipCode;
    private String zipName;
}
