package com.dtxytech.boot.entity.event;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import java.util.Date;

/**
 * @NAME : bootweb com.dtxytech.boot.entity.event
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 14:49
 * @DESC : 单个实体类 主要用来测试 事件  @DomainEvents
 */
@Slf4j
@Entity
@Table(name="T_EVENT_EQUEPMENT")
@Data
@ToString(exclude ={"description","attachment"} )
public class Equepment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //演示如果想给数据库字段添加默认值 配置
    @Column(name="NAME",columnDefinition = "VARCHAR(200) default 'hello' ")
    private String name;

    private String kks;

    @Lob// Clob类型或者 tinytext， text，mediumtext，longtext类型程序里用String接收
    @Basic(fetch= FetchType.EAGER)//@Lob 通常与@Basic同时使用，提高访问速度。  默认EAGER
    private String description;

    @Lob// Blob类型或者类型程序里用byte接收
    @Basic(fetch = FetchType.LAZY)
    private byte[] attachment;


    private Date createDate;

    private Date updateDate;



    @PostLoad
    public void mypostLoad(){
        log.debug("mypostLoad 触发了哦");
    }
    @PostPersist
    public void mypostPersist(){
        log.debug("mypostPersist 触发了哦");
        //throw new RuntimeException("异常了啊");
    }
    @PostRemove
    public void mypostRemove(){
        log.debug("mypostRemove 触发了哦");
    }
    @PostUpdate
    public void mypostUpdate(){
        log.debug("mypostUpdate 触发了哦");
    }


    @PrePersist
    public void myprePersist(){
        log.debug("myprePersist 触发了哦----pre 这里设置一下 createDate 和updateDate");
        createDate = updateDate = new Date();
    }
    @PreRemove
    public void mypreRemove(){
        log.debug("mypreRemove 触发了哦----pre");
    }

    //一般用来设置更新时间 等
    @PreUpdate
    public void mypreUpdate(){
        log.debug("mypreUpdate 触发了哦----pre");
        updateDate = new Date();
    }























    /**
     * 该方法会在employyeRepository.save()调用时
     * 且注解了 @TransactionalEventListener 时候出发
     * @DomainEvents可以返回单个事件实例或事件集合
     * 所有事件发布后@AfterDomainEventPublication用于潜在地清理要发布的事件列表
     * @return
     */
//    @DomainEvents
//    Collection<EquepmentSaveEvent> equepmentSaveEvents() {
//        log.debug("触发事件@DomainEvents");
//        return Arrays.asList(new EquepmentSaveEvent(this));
//    }

    /**
     * @AfterDomainEventPublication 在事件发布之后触发。
     * @AfterDomainEventPublication 只在@DomainEvents存在时才起作用。
     */
//    @AfterDomainEventPublication
//    public void  event3(){
//        log.debug("domainEvent发布成功 !!!");
//    }


}
