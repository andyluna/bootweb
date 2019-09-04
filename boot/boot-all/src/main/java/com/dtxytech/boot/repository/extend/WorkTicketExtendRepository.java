package com.dtxytech.boot.repository.extend;

import com.dtxytech.boot.entity.WorkTicket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:28
 * @DESC : RepositoryDefinition定义一个WorkTicketRepository
 *          不过这是一个干净接口 里面没有任何现成的方法可以调用
 *          主要用来掩饰spring-data-jpa 规范定义的高级查询
 */
public interface WorkTicketExtendRepository extends
        JpaRepository<WorkTicket,String>,
        MyWorkTicketExtendsRepository {


}
