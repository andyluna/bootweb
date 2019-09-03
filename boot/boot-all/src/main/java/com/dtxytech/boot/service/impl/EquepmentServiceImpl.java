package com.dtxytech.boot.service.impl;

import com.dtxytech.boot.entity.event.Equepment;
import com.dtxytech.boot.repository.event.EquepmentRepository;
import com.dtxytech.boot.service.EquepmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @NAME : bootweb com.dtxytech.boot.service.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/9/3 九月 12:55
 * @DESC : 主要用来测试event
 */
@Service
@Transactional
@Slf4j
public class EquepmentServiceImpl implements EquepmentService {
    @Autowired
    private EquepmentRepository equepmentRepository;


    @Override
    public Equepment save(Equepment e) {
        return equepmentRepository.save(e);
    }

    @Override
    public List<Equepment> saveAll(Iterable<Equepment> its) {
        return equepmentRepository.saveAll(its);
    }

    @Override
    public Equepment getById(Integer id) {
        return equepmentRepository.findById(id).orElse(null);
    }

    @Override
    public Equepment getByName(String name) {
        return equepmentRepository.findByName(name);
    }

    @Override
    public Equepment getByHql(String name) {
        return equepmentRepository.findByNameHql(name);
    }

    @Override
    public void update(Equepment e) {
        equepmentRepository.save(e);
    }

    @Override
    public int updateByHql(String name, Integer id) {
        return equepmentRepository.updateByHql(name,id);
    }

    @Override
    public void deleteById(int id) {
        equepmentRepository.deleteById(id);
    }

    @Override
    public void deleteByHql(int id) {
        equepmentRepository.deleteByHql(id);
    }










    /**
     *  接受User发出的类型为EquepmentSaveEvent的DomainEvents事件 提交之后
     *  事务提交之后会自动调用该方法
     */
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
//    public void afterCommit(EquepmentSaveEvent event){
//        log.debug("事件监听提交之后 EquepmentSaveEvent 事件监听成功：{}"+event.getEquepment());
//    }

    /**
     *  接受User发出的类型为EquepmentSaveEvent的DomainEvents 提交之前
     *   事务提交之前会自动调用该方法  配合
     */
//    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
//    public void beforeCommit(EquepmentSaveEvent event){
//        event.doSomeThing();
//        log.debug("事件监听提交之前 EquepmentSaveEvent 事件监听成功：{}"+event.getEquepment());
//    }
}
