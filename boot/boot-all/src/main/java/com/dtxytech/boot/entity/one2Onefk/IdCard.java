package com.dtxytech.boot.entity.one2Onefk;

import lombok.Data;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.one2Onefk
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:24
 * @DESC : 身份证
 */
@Entity
@Table(name="T_FK_ID_CARD")
@Data
@ToString(exclude = {"person"})
public class IdCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="PERSON_ID")
    private Person person;
}
