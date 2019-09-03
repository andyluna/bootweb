package com.andy.hibernate.annotation.onetoonefk.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @NAME : bootweb com.andy.hibernate.annotation.onetoonefk.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/29 八月 10:27
 * @DESC :
 */
@Data
@Entity
@Table(name="T_ANNO_CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String number;

    @OneToOne
    @JoinColumn(name="person_id")
    private Person person;
}
