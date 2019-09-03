package com.dtxytech.boot.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:19
 * @DESC : 模拟两票表
 */
@Entity
@Table(name = "T_WORK_TICKET")
@Data
public class WorkTicket {
    @Id
    @GeneratedValue(generator = "mygen")
    @GenericGenerator(name="mygen",strategy = "uuid")
    private  String id;
    private  String modelCode;
    private  String mainId;
    private  String status;
    private  String content;
    private  String equipKks;
    private  Date createDate;

    public WorkTicket() {
    }
    //定义构造函数
    public WorkTicket(String id, String content, String mainId) {
        this.mainId = id;
        this.content = content;
        this.mainId = mainId;
    }
}
