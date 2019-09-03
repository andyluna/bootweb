package com.dtxytech.boot.repository;

import com.dtxytech.boot.entity.WorkTicket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.persistence.QueryHint;
import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:28
 * @DESC :
 */
public interface WorkTicketRepository1 extends Repository<WorkTicket,String> {

    //1.支持spel表达式
    @Query("select t from #{#entityName} t where t.content like ?1")
    List<WorkTicket> findAllByContent(String content);

    //2.支持spel表达式
    //如果项目集成了Spring security
    //@Query可以写成 "select t from #{#entityName} t where  t.content like %:content% and userId = ?#{principal.userId}"
    @Query("select t from #{#entityName} t where  t.content like %:content% ")
    List<WorkTicket> findAllByContentWithSpel(@Param("content") String content);



    /**
     * 3.支持spel表达式
     * 当使用like带有来自不安全源的值的条件时，应该清理这些值，以便它们不能包含任何通配符
     * 为此，可以使用escape(String)方法在SpEL上下文中可用。
     * 它使用第二个参数中的单个字符为第一个参数中的所有实例_和前缀添加前缀%。
     * 结合JPQL和标准SQL中提供escape的like表达式子句，可以轻松清除绑定参数。
     * @param content
     * @return
     */
    @Query("select t from #{#entityName} t where  t.content like %?#{escape([0])}% escape ?#{escapeCharacter()}")
    List<WorkTicket> findAllByContentWithSpel1(String content);


    /**
     * 4.修改查询 前面加上@Modifying注解标识
     * @param mainId
     * @param content
     * @return 修改 删除 方法 默认都返回int  代表修改或删除数据的记录数
     */
    @Modifying
    @Query("update WorkTicket u set u.mainId = ?1 where u.content = ?2")
    int updateWtMainIdByContent(String mainId, String content);



    /**
     * 5. 删除操作
     * @param content
     * @return
             */
    int deleteByContent(String content);




    /**
     * 6. 删除操作
     * 5和6区别 都删根据content删除数据两者存在巨大差异
     *  第5个方法： 无论删除条件是什么，会先根据这些条件去查询数据，然后底层再调用delete(Object)方法 根据主键去删除
     *             为什么要这么做？为了确保实际调用生命周期查询，以便持久性提供程序可以实际调用@PreRemove @PostRemove这些方法能够毁掉
     *  第6个方法： 会 直接调用一条删除语句进行删除
     * @param content
     * @return
     */
    @Modifying
    @Query("delete from WorkTicket where content =?1")
    int deleteByContent2(String content);


    /**
     * 7.应用查询提示
     *  要将JPA查询提示应用于存储库界面中声明的查询，可以使用@QueryHints注释。
     *  它需要一组JPA @QueryHint注释和一个布尔标志来潜在地禁用应用于应用分页时触发的附加计数查询的提示，
     * @param content
     * @param pageable
     * @return
     */
    @QueryHints(value = {@QueryHint(name =org.hibernate.jpa.QueryHints.HINT_COMMENT, value = "a query for pageable一个普通的分页查询查询")},
            forCounting = false)
    @Query("select c from WorkTicket c where c.content like %:content%  ")
    Page<WorkTicket> findByContentStartingWith(@Param("content") String content, Pageable pageable);


    /**
     * 8.自定义分页查询
     */
    @Query(value = "SELECT * FROM T_WORK_TICKET WHERE CONTENT like ?1",
            countQuery = "SELECT count(*) FROM T_WORK_TICKET WHERE CONTENT like ?1",
            nativeQuery = true)
    Page<WorkTicket>  findByContentPage(String content, Pageable pageable);





}
