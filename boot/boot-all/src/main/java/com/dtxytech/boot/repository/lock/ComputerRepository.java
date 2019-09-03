package com.dtxytech.boot.repository.lock;

import com.dtxytech.boot.entity.lock.Computer;
import com.dtxytech.boot.repository.base.MyBaseRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.repository.lock
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 11:50
 * @DESC :
 */
public interface ComputerRepository extends MyBaseRepository<Computer,Integer> {

    //普通查询模式
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<Computer> findByCpu(String cpu);
}
