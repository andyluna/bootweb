package com.dtxytech.boot.repository.proc.impl;

import com.dtxytech.boot.repository.proc.PorxExecuteExtends;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.CallableStatement;
import java.sql.ResultSet;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.proc.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 10:32
 * @DESC :
 */
@Component
@Slf4j
public class PorxExecuteExtendsImpl implements PorxExecuteExtends {

    @PersistenceContext
    EntityManager entityManager;

    public Session getSession(){
        return entityManager.unwrap(Session.class);
    }



    @Override
    public String executeProcSayHello2() {
        Query query = entityManager.createNativeQuery("{call proc_2_sayhello()}");
        Object result = query.getSingleResult();
        return result.toString();
    }

    @SuppressWarnings({"unchecked"})
    @Override
    public String executeProcSayHello3() {
        org.hibernate.query.NativeQuery<String> query1 = getSession().createNativeQuery("{call proc_2_sayhello()}");
        Object result1 = query1.getSingleResult();
        return result1.toString();
    }

    @Override
    public String executeProcSayHello4() {
        final String[] res = {null};
        getSession().doWork(conn->{
            CallableStatement cs = conn.prepareCall("{call proc_2_sayhello()}");
            /**
             * 设置输入参数
             *      cs.setInt(1, 6);
             * 设置输出参数(注册输出参数)
             *      参数一： 参数位置
             *      参数二： 存储过程中的输出参数的jdbc类型    VARCHAR(20)
             *      cs.registerOutParameter(2, java.sql.Types.VARCHAR);
             */

            ResultSet rs = cs.executeQuery();
            if(rs.next()) {
                String result = rs.getString(1);
                log.debug("结果:{}",result);
                res[0] = result;
            }
            rs.close();
            cs.close();
        });
        return res[0];
    }



}
