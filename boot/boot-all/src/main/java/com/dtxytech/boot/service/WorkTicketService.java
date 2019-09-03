package com.dtxytech.boot.service;

import com.dtxytech.boot.entity.WorkTicket;
import com.dtxytech.boot.repository.WorkTicketRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 * @NAME : bootweb com.dtxytech.boot.service
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:32
 * @DESC : 两票服务类
 */
public interface WorkTicketService {
    //1. SimpleJpa原生方法
    WorkTicket save(WorkTicket wt);

    //2. SimpleJpa原生方法
    List<WorkTicket> saveAll(Iterable<WorkTicket> wts);

    //3. SpringDataJpa规范支持的方法
    WorkTicket findById(String id);

    //4. 统计查询
    long countByContentStartingWith(String content);

    //5. 测试Top方法 查询条件不需要 加% 参数 springdata-jpa会自动加上
    List<WorkTicket> findTop3ByContentStartingWith(String content);

    //6. 测试First方法 查询条件不需要 加% 参数 springdata-jpa会自动加上
    List<WorkTicket> findFirst4ByContentStartingWith(String content);



    //7.加了这个注解的情况下，参数为空报IllegalArgumentException异常 结果为空报 EmptyResultDataAccessException异常
    WorkTicket findByContentAndMainId(String content,String mainId);

    /**
     * 8.异步查询：
     * 可以使用Spring的异步方法执行功能异步运行存储库查询。
     * 这意味着该方法在调用时立即返回，而实际的查询执行发生在已提交给Spring的任务中TaskExecutor。
     * 异步查询执行与反应式查询执行不同，不应混合使用。
     * @param mainId
     * @return 异步查询返回类型支持下面3种
     * 	        使用      java.util.concurrent.Future作为返回类型。
     *          使用Java8 java.util.concurrent.CompletableFuture作为返回类型。
     *          使用a     org.springframework.util.concurrent.ListenableFuture作为返回类型。
     */
    @Async
    Future<WorkTicket> findByMainId(String mainId);

    //9.异步查询
    @Async
    CompletableFuture<WorkTicket> findOneByContent(String content);

    //10.异步查询
    @Async
    ListenableFuture<WorkTicket> findOneByEquipKks(String equipKks);



    /**
     * 11. 标准JPQL查询  问号传参
     */
    List<WorkTicket> findByHql1(String content,String mainId );


    /**
     * 12. 标准JPQL查询  冒号传参 @Param指定
     */
    List<WorkTicket> findByHql2(String content,String mainId );

    /**
     * 13. 查询部分字段
     */
    List<Object> findByHql3(String content,String mainId );

    /**
     * 14. 查询部分字段
     */
    List<Object[]> findByHql4(String content,String mainId );


    /**
     * 15. 查询部分字段
     */
    List<WorkTicket> findByHql5(String content,String mainId );


    /**
     * 16. 查询部分字段 返回map类型
     */
    List<Map<String,Object>> findByHql16(String content, String mainId );

    /**
     * 17. 查询部分字段 返回map类型
     */
    List<Map<Integer,Object>> findByHql17(String content, String mainId );

    /**
     * 18. 查询部分字段 返回map类型
     */
    List<Map<String,Object>> findByHql18(String content, String mainId );

    /**
     * 19. 查询部分字段 返回自定义接口
     */
    List<WorkTicketRepository.WtResultIterface> findByContentContainsAndMainIdContains(String content, String mainId );



    /**
     * 20. 查询部分字段 返回自定义接口
     */
    List<WorkTicketRepository.WtResultIterface2> findByContentContains(String content);


    /**
     * 21. 查询部分字段 返回自定义接口
     */
    List<WorkTicketRepository.WtResultIterface3> findByMainIdContains(String mainId );

}
