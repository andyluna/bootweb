package com.dtxytech.boot.event;

import com.dtxytech.boot.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @name  : com.dtxytech.boot.event.EmployeeSaveEvent
 * @desc  :  EmployeeSaveEvent保存时间
 * @author: xiangdan
 * @time  : 2019/8/30 10:57
 */
@Slf4j
@Getter
@AllArgsConstructor
public class EmployeeSaveEvent {

    private Employee employee;

    public void doSomeThing(){
        log.debug("事件监听生效 EmployeeSaveEvent：获取employee,{}",employee);
    }



}
