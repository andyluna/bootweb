package com.dtxytech.boot.entity.embbed;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.embbed
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/31 八月 16:22
 * @DESC : 城市象征 并加上 可嵌入的注解
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CitySymbol {

    private String symbolArchite;//象征性建筑

    private String symbolFood;//象征性没事

}
