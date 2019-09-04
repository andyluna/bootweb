package com.dtxytech.boot.repository;

import com.dtxytech.boot.entity.WorkTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Stream;

/**
 * @NAME : bootweb com.dtxytech.boot.repository
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:28
 * @DESC : RepositoryDefinition定义一个WorkTicketRepository
 *          不过这是一个干净接口 里面没有任何现成的方法可以调用
 *          主要用来演示spring-data-jpa 规范定义的高级查询
 */
@RepositoryDefinition( domainClass = WorkTicket.class , idClass=String.class )
public interface WorkTicketRepository  {
    /**
     * 1.如果是这种模式下 即使是 最简单的 save方法也需要重新定义才能使用
     * 且原生的 方法名字 必须来自 SimpleJpaRepository 实现类
     * @param wt
     * @return
     */
    WorkTicket save(WorkTicket wt);

    /**
     * 2.同样也是  SimpleJpaRepository 已经存在的方法
     * @param wts
     * @return
     */
    List<WorkTicket> saveAll(Iterable<WorkTicket> wts);

    /**
     * 3. 规范查询的开头关键字是  find 或 get
     *      统计查询的开头关键字是 count
     *      删除查询  使用 delete 开头   springdatajpa规范共支持这4个关键字开头
     *  支持的关键字总共支持26个查询关键字：
     * 1.And	            findByLastnameAndFirstname	… where x.lastname = ?1 and x.firstname = ?2
     * 2.Or	                findByLastnameOrFirstname	… where x.lastname = ?1 or x.firstname = ?2
     * 3.Is,Equals	        findByFirstname,findByFirstnameIs,findByFirstnameEquals	… where x.firstname = ?1
     * 4.Between	        findByStartDateBetween	    … where x.startDate between ?1 and ?2
     * 5.LessThan	        findByAgeLessThan	        … where x.age < ?1
     * 6.LessThanEqual	    findByAgeLessThanEqual	    … where x.age <= ?1
     * 7.GreaterThan	    findByAgeGreaterThan	    … where x.age > ?1
     * 8.GreaterThanEqual	findByAgeGreaterThanEqual	… where x.age >= ?1
     * 9.After	            findByStartDateAfter	    … where x.startDate > ?1
     * 10.Before	        findByStartDateBefore	    … where x.startDate < ?1
     * 11.IsNull	        findByAgeIsNull	            … where x.age is null
     * 12.IsNotNull,NotNull	findByAge(Is)NotNull	    … where x.age not null
     * 13.Like	            findByFirstnameLike	        … where x.firstname like ?1
     * 14.NotLike	        findByFirstnameNotLike	    … findByFirstnameNotLike
     * 15.StartingWith	    findByFirstnameStartingWith	… where x.firstname like ?1 (parameter bound with appended %)
     * 16.EndingWith	    findByFirstnameEndingWith	… where x.firstname like ?1 (parameter bound with prepended %)
     * 17.Containing	    findByFirstnameContaining	… where x.firstname like ?1 (parameter bound wrapped in %)
     * 18.OrderBy	        findByAgeOrderByLastnameDesc … where x.age = ?1 order by x.lastname desc
     * 19.Not	            findByLastnameNot	         … where x.lastname <> ?1
     * 20.In	            findByAgeIn(Collection<Age> ages)	… where x.age in ?1
     * 21.NotIn	            findByAgeNotIn(Collection<Age> ages)… where x.age not in ?1
     * 22.TRUE	            findByActiveTrue()	                … where x.active = true
     * 23.FALSE	            findByActiveFalse()	                … where x.active = false
     * 24.IgnoreCase	    findByFirstnameIgnoreCase	        … where UPPER(x.firstame) = UPPER(?1)
     * 25.first	            findFirst4ByFirstnameIgnoreCase
     * 26.top	            findTop6ByFirstnameIgnoreCase
     * @param id
     * @return 如果返回的是单个对象 则支持以下几种
     *          1. WorkTicket 直接实体类
     *          2. java.util.Optional<WorkTicket>
     *          3. com.google.common.base.Optional<WorkTicket>
     *          4. scala.Option<WorkTicket>
     *          5. io.vavr.control.Option<WorkTicket>
     *          6. javaslang.control.Option<WorkTicket>
     */
    java.util.Optional<WorkTicket> findById(String id);


    /**
     * 4.统计总数  后面的格式与 find方法格式一样
     * @param content
     * @return 查询总数默认返回是long类型
     *      如果需要也可以写成 int short （数据量过大时会报错）
     *      Number
     */
    long countByContentStartingWith(String content);

    /**
     * 5.查询最顶端3条数据
     * @param content
     * @return 返回类型如果是集合
     *   接收类型  默认是 List<T>
     *       也支持  Set ArrayList LinkedList
     *       Collection Stream Slice
     */
    List<WorkTicket> findTop3ByContentStartingWith(String content);

    /**
     * 6.返回类型 如果是 Stream 和 Slice
     * 则对数据的使用操作必须在一个事务里面完成
     * @param content
     * @return
     */
    Stream<WorkTicket> findFirst4ByContentStartingWith(String content);

    /**
     * 7. 查询参数或返回值 空值处理
     *   Spring 提供了3个注解专门针对空值参数，作用如下：
     *   @NonNullApi : 在包级别上使用，以声明参数和返回值的默认行为是不接受或生成null值。
     *   @NonNull  : 用于不能是参数或返回值null （参数不需要，返回值@NonNullApi适用）。
     *   @Nullable : 用于可以的参数或返回值null。 (注释在方法或者参数上)
     */
    @NonNull//加了这个注解的情况下，参数为空报IllegalArgumentException异常 结果为空报 EmptyResultDataAccessException异常
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

    /**
     * 9.异步查询
     * @param content
     * @return
     */
    @Async
    CompletableFuture<WorkTicket> findOneByContent(String content);

    /**
     * 10.异步查询
     * @param equipKks
     * @return
     */
    @Async
    ListenableFuture<WorkTicket> findOneByEquipKks(String equipKks);

    /**
     * 11.标准JPQL查询
     * 如果是问号传参 则必须跟上数字 ?1  ?2
     * @param content
     * @param mainId
     * @return
     */
    @Query("from WorkTicket where content like ?1 " +
            " and mainId like ?2 order by id asc ")
    List<WorkTicket> findByHql1(String content,String mainId );

    /**
     * 12.标准JPQL查询
     * 冒号传参  如果 content与参数名字 一一对应且没有重复的时候可以这么写
     * @param content
     * @param mainId
     * @return
     */
    @Query("from WorkTicket where content like :content " +
            " and mainId like :mainId order by id asc ")
    List<WorkTicket> findByHql2(@Param("content") String content,
                                @Param("mainId") String mainId );
    /**
     * 13.查询 返回部分字段 List<Object> 实际上是一个List<Object[]>数组
     * @param content
     * @param mainId
     * @return
     */
    @Query("select id,content,mainId from WorkTicket " +
            " where content like :content and mainId like :mainId order by id asc")
    List<Object> findByHql3(@Param("content") String content,
                            @Param("mainId") String mainId );


    /**
     * 14.查询 返回部分字段 List<Object[]> 与 13方法一样
     * @param content
     * @param mainId
     * @return
     */
    @Query("select id,content,mainId from WorkTicket " +
            "where content like :content and mainId like :mainId order by id asc")
    List<Object[]> findByHql4(@Param("content") String content,
                              @Param("mainId") String mainId );

    /**
     * 15.查询 返回部分字段 如果返回的字段与 实体类中的一个构造方法一一对应时
     * 可以使用这个构造函数作为返回类型
     * @param content
     * @param mainId
     * @return
     */
    @Query("select new com.dtxytech.boot.entity.WorkTicket(id,content,mainId)" +
            " from WorkTicket where content like :content " +
            " and mainId like :mainId order by id asc")
    List<WorkTicket> findByHql5(@Param("content") String content, @Param("mainId") String mainId );

    /**
     * 16.查询部分字段  返回Map  不加任何修饰 这个map是
     * org.springframework.data.jpa.repository.query.AbstractJpaQuery.
     * TupleConverter.TupleBackedMap
     * 类似于数组的MAP ，map中所有key都为空 ，只能通过map.entrySet()来遍历取值  这样取值也不方便
     * @param content
     * @param mainId
     * @return
     */
    @Query("select  id,content,mainId from WorkTicket where content like :content and mainId like :mainId order by id asc")
    List<Map<String,Object>> findByHql16(@Param("content") String content, @Param("mainId") String mainId );


    /**
     * 17.查询部分字段  返回Map   用new map的方式
     * @param content
     * @param mainId
     * @return
     */
    @Query("select  new map(id,content,mainId) from WorkTicket " +
            "where content like :content and mainId like :mainId order by id asc")
    List<Map<Integer,Object>> findByHql17(@Param("content") String content, @Param("mainId") String mainId );


    /**
     * 18.查询部分字段  返回Map  用new map并且 加上as
     * @param content
     * @param mainId
     * @return
     */
    @Query("select  new map(id as id ,content as content,mainId as manId) from  " +
            "WorkTicket where content like :content and mainId like :mainId order by id asc")
    List<Map<String,Object>> findByHql18(@Param("content") String content, @Param("mainId") String mainId );



    /**
     * 19.查询部分字段  自定义接口 接收自定义返回值
     * @param content
     * @param mainId
     * @return
     */
    List<WtResultIterface> findByContentContainsAndMainIdContains(String content,String mainId );

    interface WtResultIterface{
        String getId();
        String getContent();
        String getMainId();
    }



    /**
     * 20.查询部分字段  自定义接口 接收自定义返回值
     * @param content
     * @return
     */
    List<WtResultIterface2> findByContentContains(String content );

    interface WtResultIterface2{
        String getId();
        @Value("#{target.content + ' ' + target.mainId}")
        String getContent();
        default String getMainId(){
            return getId().concat(" 这是链接符号 ")
                    .concat(getContent());
        }
    }

    /**
     * 21.查询部分字段  自定义接口 接收自定义返回值
     * @param mainId
     * @return
     */
    List<WtResultIterface3> findByMainIdContains(String mainId );

    interface WtResultIterface3{
        String getId();

        @Value("#{@myBean.getMyContent(target)}")
        String getContent();

        String getMainId();

    }


}
