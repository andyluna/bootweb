package com.dtxytech.boot.service.impl;

import com.dtxytech.boot.entity.WorkTicket;
import com.dtxytech.boot.repository.WorkTicketRepository;
import com.dtxytech.boot.service.WorkTicketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @NAME : bootweb com.dtxytech.boot.service.impl
 * @auth : andy/xiangdan@dtxysoft.com
 * @TIME : 2019/8/30 八月 21:32
 * @DESC :
 */
@Service
@Transactional
@Slf4j
public class WorkTicketServiceImpl implements WorkTicketService {
    @Autowired
    private WorkTicketRepository wtRepository;


    @Override
    public WorkTicket save(WorkTicket wt) {
        return wtRepository.save(wt);
    }

    @Override
    public List<WorkTicket> saveAll(Iterable<WorkTicket> wts){
        return wtRepository.saveAll(wts);
    }
    @Override
    public WorkTicket findById(String id) {
        return wtRepository.findById(id).orElse(null);
    }

    //4. 统计查询
    @Override
    public long countByContentStartingWith(String content){
        return wtRepository.countByContentStartingWith(content);
    }

    @Override
    public List<WorkTicket> findTop3ByContentStartingWith(String content){
        return wtRepository.findTop3ByContentStartingWith(content);
    }

    //特别注意  如果 wtRepository 返回Stream 则所有对Stream的操作必须在一个事务里面做完  不然会报错
    @Override
    public List<WorkTicket> findFirst4ByContentStartingWith(String content){
        Stream<WorkTicket> stream = wtRepository.findFirst4ByContentStartingWith(content);
        List<WorkTicket> collect = stream.collect(Collectors.toList());
        return collect;
    }

    @Override
    public WorkTicket findByContentAndMainId(String content, String mainId) {
        return wtRepository.findByContentAndMainId(content,mainId);
    }

    @Override
    public Future<WorkTicket> findByMainId(String mainId) {
        return wtRepository.findByMainId(mainId);
    }

    @Override
    public CompletableFuture<WorkTicket> findOneByContent(String content) {
        return wtRepository.findOneByContent(content);
    }

    @Override
    public ListenableFuture<WorkTicket> findOneByEquipKks(String equipKks) {
        return wtRepository.findOneByEquipKks(equipKks);
    }

    @Override
    public     List<WorkTicket> findByHql1(String content,String mainId ){
        List<WorkTicket> list = wtRepository.findByHql1(content, mainId);
        return list;
    }


    @Override
    public     List<WorkTicket> findByHql2(String content,String mainId ){
        List<WorkTicket> list = wtRepository.findByHql2(content, mainId);
        return list;
    }

    @Override
    public List<Object> findByHql3(String content, String mainId) {
        return wtRepository.findByHql3(content,mainId);
    }

    @Override
    public List<Object[]> findByHql4(String content, String mainId) {
        return wtRepository.findByHql4(content,mainId);
    }

    @Override
    public List<WorkTicket> findByHql5(String content, String mainId) {
        return wtRepository.findByHql5(content,mainId);
    }

    @Override
    public List<Map<String,Object>> findByHql16(String content, String mainId) {
        return wtRepository.findByHql16(content,mainId);
    }
    @Override
    public List<Map<Integer,Object>> findByHql17(String content, String mainId) {
        return wtRepository.findByHql17(content,mainId);
    }
    @Override
    public List<Map<String,Object>> findByHql18(String content, String mainId) {
        return wtRepository.findByHql18(content,mainId);
    }

    @Override
    public List<WorkTicketRepository.WtResultIterface> findByContentContainsAndMainIdContains(String content, String mainId) {
        return wtRepository.findByContentContainsAndMainIdContains(content,mainId);
    }

    @Override
    public List<WorkTicketRepository.WtResultIterface2> findByContentContains(String content) {
        return wtRepository.findByContentContains(content);
    }

    @Override
    public List<WorkTicketRepository.WtResultIterface3> findByMainIdContains(String mainId) {
        return wtRepository.findByMainIdContains(mainId);
    }
}
