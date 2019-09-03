package com.dtxytech.boot.entity.proc;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.proc
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/2 九月 10:36
 * @DESC : 职工表实体类  主要用来测试存储过程
 */
@Entity
@Table(name="T_PROC_STAFF")
@Data
@NamedStoredProcedureQueries(value = {
        @NamedStoredProcedureQuery(name = "Staff.proc1", procedureName = "proc_1_inout", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN , name = "arg", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "res", type = Integer.class) }),

        @NamedStoredProcedureQuery(name = "Staff.proc4", procedureName = "proc_4_agetotal", parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN , name = "minAge", type = Integer.class),
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "total", type = Integer.class) })
})


public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //主键
    private String name; //职工姓名
    private Integer age; //年龄
    private String position;//职位
    private Date createDate;//创建时间

}
