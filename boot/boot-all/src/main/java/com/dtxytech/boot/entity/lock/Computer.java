package com.dtxytech.boot.entity.lock;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.lock
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 11:46
 * @DESC :
 */
@Entity
@Table(name="T_LOCK_COMPUTER")
@Getter
@Setter
@ToString
public class Computer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String cpu;
    private String memory;//内存
    private String brand;//品牌
    private Date createDate;//创建时间
    public Computer(){}
    public Computer(String cpu, String memory, String brand, Date createDate) {
        this.cpu = cpu;
        this.memory = memory;
        this.brand = brand;
        this.createDate = createDate;
    }
}
