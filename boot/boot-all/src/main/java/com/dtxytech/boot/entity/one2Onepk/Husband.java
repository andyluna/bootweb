package com.dtxytech.boot.entity.one2Onepk;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.one2Onepk
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:27
 * @DESC : Husband
 */
@Entity
@Table(name="T_PK_HUSBAND")
public class Husband {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn//配置共享主键，否则会额外生成外键关联列
    private Wife wife;

}
